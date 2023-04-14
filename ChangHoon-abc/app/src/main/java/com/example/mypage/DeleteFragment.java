package com.example.mypage;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mypage.databinding.CustomDialogBinding;
import com.example.mypage.databinding.FragmentDeleteBinding;

import java.util.ArrayList;

public class DeleteFragment extends Fragment{

    private FragmentDeleteBinding binding;
    private ArrayList<WatchDto> list = new ArrayList<>();
    private final ArrayList<String> items = new ArrayList<>();
    private ListAdapter1 adapter;
    private WatchViewModel viewModel;
    // list 클릭된 position 받아옴
    private final ClickCallbackListener callbackListener = position -> {
        // list의 position 클릭 됐을 때
        if(list.get(position).getChecked()) {
            //items에 contid 값 저장
            items.add(list.get(position).getContId());
            count();
        }
        // list의 position 클랙 해제 됐을 때
        else {
            //items에 position 값 삭제
            items.remove(String.valueOf(list.get(position).getContId()));
            count();
        }
        deleteButton();
        checked();
    };
    public void deleteButton(){
        if(items.size() != 0){
            setClickable(true);
            //삭제하기 dialog
            binding.deletebtn.setOnClickListener(v -> {
                makeAlertDialog();
            });
        }
        else{
            setClickable(false);
        }
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //viewModel
        viewModel = new ViewModelProvider(this).get(WatchViewModel.class);
        //binding inflate
        binding = FragmentDeleteBinding.inflate(inflater, container, false);
        list = viewModel.getList();
        //어댑터 생성 및 list adapter 넘겨주기
        adapter = new ListAdapter1(list);
        //adapter callback listener 연결
        adapter.setCallbackListener(callbackListener);

        //layout-manager 설정
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(container.getContext()));
        //adapter 설정
        binding.recyclerview.setAdapter(adapter);
        //count 초기값 설정
        count();
        //전체선택
        selectable();
        //뒤로가기 image button
        binding.backbtn.setOnClickListener(view -> {
            requireActivity().onBackPressed();
        });
        return binding.getRoot();
    }
    private void selectable(){
        binding.selectAll.setOnClickListener(v -> {
            if (items.size() == 0) {
                // 체크 된 항목이 없을 때
                selectAll();
            }
            else {
                //체크 된 항목이 있을 때
                if(items.size() == list.size()){
                    //전체 선택일 때
                    items.clear();
                    adapter.selectAll(false);
                    setClickable(false);
                }
                else {
                    //전체 선택이 아닐 때
                    selectAll();
                }
            }
            count();
        });
        binding.deletebtn.setOnClickListener(view -> {
            makeAlertDialog();
        });
        deleteButton();
    }
    private void selectAll(){
        Log.d("===current selectAll", "전체선택 체크 or 해제");
        items.clear();
        adapter.selectAll(true); //35개 false
        setClickable(true);
        for(int i=0; i<list.size(); i++){
            items.add(list.get(i).getContId());
        }
    }
    private void checked(){
        Log.d("===currentChecked", "전체 선택 기능");
        //전체 선택 중 items 클릭 해제 시 전체 선택 체크박스도 선택 해제 or 모든 items 선택 시 전체 선택도 선택
        binding.selectAll.setChecked(adapter.getItemCount() == items.size());
    }
    private void count(){
        Log.d("===currentCount", "count 값 설정");
        binding.count.setText(items.size() + "개 선택 (전체 " + list.size() + "개)");
        adapter.notifyDataSetChanged();
        // items.size 자릿수 계산
        int n = items.size();
        int count = 0;
        if(n == 0){
            count = 1;
        }
        while(n != 0){
            n = n / 10;
            ++count;
        }
        // count text 색상 및 굵기 다르게
        Spannable span = (Spannable) binding.count.getText();
        span.setSpan(new StyleSpan(Typeface.BOLD), 0, count, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new ForegroundColorSpan(Color.rgb(218, 70, 129)),0, count,  Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        span.setSpan(new ForegroundColorSpan(Color.rgb(255, 255, 255)), count, 4+count, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    // delete-btn clickable
    private void setClickable(boolean b){

        //삭제하기 버튼 활성화 / 비활성화
        if(b){
            Log.d("===setClickable", "delete-btn 활성화");
            binding.deletebtn.setClickable(true);
            binding.deletebtn.setBackgroundResource(R.color.button_color);
        }
        else{
            Log.d("===setClickable", "delete-btn 비활성화");
            binding.deletebtn.setClickable(false);
            binding.deletebtn.setBackgroundResource(R.color.button_color1);
        }
    }
    //dialog 생성 메서드
    private void makeAlertDialog(){
        Log.d("===currentmakeAlertDialog", "Dialog 생성");
        CustomDialogBinding customDialogBinding = CustomDialogBinding.inflate(LayoutInflater.from(this.getContext()));
        AlertDialog builder = new AlertDialog.Builder(this.getContext()).setView(customDialogBinding.getRoot()).create();
        //dialog background 투명하게
        builder.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //Dialog set Text
        if(items.size() == list.size()) {
            customDialogBinding.title.setText("시청 목록이 모두 삭제됩니다.\n전체 삭제를 진행하시겠습니까?");
        }
        else{
            customDialogBinding.title.setText(items.size() + "개의 콘텐츠를 삭제하시겠습니까?");
        }
        //아니요 버튼
        customDialogBinding.no.setOnClickListener(view1 -> builder.dismiss());
        //네 버튼
        customDialogBinding.yes.setOnClickListener(view1 -> {
            if(items.size() == list.size()){
                viewModel.deleteAll(); //WatchManager 값 삭제
            }
            else{//중첩 for문 개선
                for(int i=0; i<items.size(); i++){
                    items.get(i).equals(list);
                    viewModel.deleteById(items.get(i));
                }
            }
            list = viewModel.getList();
            adapter.setList(list);
            binding.selectAll.setChecked(false);
            setClickable(false);
            count();
            builder.dismiss();
            customToast("삭제가 완료되었습니다.");
            requireActivity().onBackPressed();
        });
        builder.show();
    }
    public void customToast(String message){
        Log.d("===currentCustomToast", "커스텀 토스트 생성");
        LayoutInflater inflater = LayoutInflater.from(binding.getRoot().getContext());
        View layout = inflater.inflate(R.layout.custom_toast, binding.getRoot().findViewById(R.id.toast_layout));
        TextView textView = layout.findViewById(R.id.toast_textview);
        textView.setText(message);
        Toast toast = new Toast(binding.getRoot().getContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}