package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.databinding.RecyclerviewItemBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter{

    private ArrayList<Dto> arrayList;

    public ListAdapter(ArrayList<Dto> arrayList){
        this.arrayList = arrayList;
    }
    public void setArrayList(ArrayList<Dto> arrayList){
        this.arrayList = arrayList;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context =parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        RecyclerviewItemBinding binding = RecyclerviewItemBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    private static class ViewHolder extends RecyclerView.ViewHolder{

        private RecyclerviewItemBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = RecyclerviewItemBinding.bind(itemView);
            itemView.setOnClickListener(view -> {
                Toast.makeText(itemView.getContext(), "item 클릭", Toast.LENGTH_SHORT).show();
            });
        }
        public void bind(Dto item){
            binding.method.setText("학식");
            binding.member.setText("1/6");
            binding.category.setText("abc");
        }
    }
}