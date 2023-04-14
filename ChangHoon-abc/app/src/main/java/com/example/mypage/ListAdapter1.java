package com.example.mypage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mypage.databinding.RecyclerviewDeleteBinding;

import java.util.ArrayList;
import java.util.Objects;

public class ListAdapter1 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<WatchDto> list;
    private ClickCallbackListener callbackListener;

    public ListAdapter1(ArrayList<WatchDto> list){
        this.list=list;
    }
    public void setList(ArrayList<WatchDto> list){
        this.list=list;
    }
    public void setCallbackListener(ClickCallbackListener callbackListener) {
        this.callbackListener = callbackListener;
    }
    public void selectAll(boolean b){//체크박스 전체 선택
        for(int i=0; i<list.size(); i++){
            list.get(i).setChecked(b);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        RecyclerviewDeleteBinding binding = RecyclerviewDeleteBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding.getRoot());
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((ViewHolder) holder).bind(list.get(position));
        //recyclerview check 데이터 재사용 문제 해결
        ((ViewHolder) holder).binding.checkbox.setChecked(list.get(position).getChecked());
        //view 클릭 시 체크박스도 클릭
        ((ViewHolder) holder).binding.constraint.setOnClickListener(v -> {
            setCallBack(!list.get(position).getChecked(), position);
        });
        //recyclerview check 상태 데이터 저장
        ((ViewHolder) holder).binding.checkbox.setOnClickListener(v -> {
            setCallBack(!list.get(position).getChecked(), position);
        });
    }
    public void setCallBack(boolean b, int position){
        list.get(position).setChecked(b);
        callbackListener.callback(position);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    private static class ViewHolder extends RecyclerView.ViewHolder{
        private final RecyclerviewDeleteBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = RecyclerviewDeleteBinding.bind(itemView);
        }
        public void bind(WatchDto item) {
            //제목
            binding.description.setText(item.getContNm());
            //ar, vr, live 구분
            if (Objects.equals(item.getTy1Code(), "AR")) {
                binding.flag.setImageResource(R.drawable.flag_ar);
            } else if (Objects.equals(item.getTy1Code(), "VR")) {
                binding.flag.setImageResource(R.drawable.flag_vr);
            } else if (Objects.equals(item.getTy1Code(), "LB")) {
                binding.flag.setImageResource(R.drawable.flag_live);
            } else {
                binding.flag.setImageDrawable(null);
            }
            //성인콘텐츠
            if (item.getIsAdultCont()) {
                binding.ageflag.setImageResource(R.drawable.flag_19);
            } else
                binding.ageflag.setImageDrawable(null);
            //유료, 무료
            if (Objects.equals(item.getPchrgFreeCode(), "C")) {
                binding.payflag.setImageResource(R.drawable.flag_pay);
            } else {
                binding.payflag.setImageDrawable(null);
            }
            //썸네일
            Glide.with(binding.sumnale).load(item.getPosterUrl()).into(binding.sumnale);
        }
    }
}