package com.example.bobgi;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bobgi.databinding.RecyclerviewItemBinding;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Dto> arrayList;

    public ListAdapter(ArrayList<Dto> arraylist){
        this.arrayList = arraylist;
    }

    public void setArrayList(ArrayList<Dto> arrayList){
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        RecyclerviewItemBinding binding =RecyclerviewItemBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).bind(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerviewItemBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = RecyclerviewItemBinding.bind(itemView);
            itemView.setOnClickListener(view -> {
                Toast.makeText(itemView.getContext(), "itemview 클릭", Toast.LENGTH_SHORT).show();
            });
        }
        public void bind(Dto item){
            binding.title.setText("학식");
            binding.category.setText("양식");
            binding.date.setText("2000-04-30");
            binding.member.setText("1/6");
        }
    }
}
