package com.example.mypage;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mypage.databinding.FragmentWatchLayoutBinding;
import java.util.ArrayList;
public class WatchFragment extends Fragment {

    private FragmentWatchLayoutBinding binding;
    private WatchViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(WatchViewModel.class);
        binding = FragmentWatchLayoutBinding.inflate(inflater, container, false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container.getContext());
        if(viewModel.getList().isEmpty()){
            itemIsEmpty();
            binding.databtn.setOnClickListener(v -> {
                Log.d("===currentDataAdd", "데이터 추가 버튼 클릭");
                viewModel.addData(); //A 데이터 추가
                setViewModel();
                itemNotEmpty();
            });
        }
        else{
            itemNotEmpty();
        }
        // 시청목록 데이터 유지
        setViewModel();
        //layoutManager 설정
        binding.recyclerview.setLayoutManager(linearLayoutManager);
        //버튼 클릭 리스너
        binding.backBtn.setOnClickListener(view -> {
            requireActivity().onBackPressed();
            Log.d("===currentMainFragment", "메인 화면 이동 버튼 클릭");
        });
        binding.deleteViewBtn.setOnClickListener(view -> {
            ((MainActivity) requireActivity()).moveTo(new DeleteFragment());
            Log.d("===currentdeleteView", "삭제 모드 이동 버튼 클릭");
        });
        return binding.getRoot();
    }
    public void setViewModel(){
        ArrayList<WatchDto> list = viewModel.getList();//주머니에서 총알 꺼냇음  //B
        ListAdapter adapter = new ListAdapter(list);//총알을 총에 끼움 // C
        binding.recyclerview.setAdapter(adapter); //D
        adapter.notifyDataSetChanged(); //E
    }
    public void itemIsEmpty(){
        binding.recyclerview.setVisibility(View.INVISIBLE);
        binding.destextView.setVisibility(View.VISIBLE);
        binding.databtn.setVisibility(View.VISIBLE);
        binding.deleteViewBtn.setVisibility(View.INVISIBLE);
    }
    public void itemNotEmpty(){
        binding.recyclerview.setVisibility(View.VISIBLE);
        binding.destextView.setVisibility(View.INVISIBLE);
        binding.databtn.setVisibility(View.INVISIBLE);
        binding.deleteViewBtn.setVisibility(View.VISIBLE);
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