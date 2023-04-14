package com.example.mypage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mypage.databinding.RecyclerviewMainBinding;

import java.util.ArrayList;
import java.util.Objects;

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final ArrayList<WatchDto> list;

    public ListAdapter(ArrayList<WatchDto> list){
        this.list=list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        com.example.mypage.databinding.RecyclerviewMainBinding binding = RecyclerviewMainBinding.inflate(inflater, parent, false);

        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerviewMainBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = RecyclerviewMainBinding.bind(itemView);
        }
        public void customToast(String message){
            LayoutInflater inflater = LayoutInflater.from(itemView.getContext());
            View layout = inflater.inflate(R.layout.custom_toast, itemView.findViewById(R.id.toast_layout));
            TextView textView = layout.findViewById(R.id.toast_textview);
            textView.setText(String.valueOf(message));
            Toast toast = new Toast(itemView.getContext());
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();
        }
        public void bind(WatchDto item){
            itemView.setOnClickListener(view -> {
                if(item.getPrice() == 0){
                    customToast("무료 콘텐츠 입니다.");
                }
                else{
                    customToast(item.getPrice() + "원 입니다.");

                }
            });
            //ar, vr, live 구분
            if(Objects.equals(item.getTy1Code(), "AR")){
                binding.flag.setImageResource(R.drawable.flag_ar);
            }
            else if(Objects.equals(item.getTy1Code(), "VR")){
                binding.flag.setImageResource(R.drawable.flag_vr);
            }
            else if(Objects.equals(item.getTy1Code(), "LB")){
                binding.flag.setImageResource(R.drawable.flag_live);
            }
            else{
                binding.flag.setImageDrawable(null);
            }
            // 제목
            binding.description.setText(item.getContNm());
            //성인콘텐츠
            if(item.getIsAdultCont()){
                binding.ageflag.setImageResource(R.drawable.flag_19);
            }
            else
                binding.ageflag.setImageDrawable(null);
            //유료, 무료
            if(Objects.equals(item.getPchrgFreeCode(), "C")){
                binding.payflag.setImageResource(R.drawable.flag_pay);
            }
            else{
                binding.payflag.setImageDrawable(null);
            }
            //썸네일
            Glide.with(binding.sumnale).load(item.getPosterUrl()).into(binding.sumnale);
        }
    }
}