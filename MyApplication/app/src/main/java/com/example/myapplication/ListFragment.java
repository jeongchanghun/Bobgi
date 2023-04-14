package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.databinding.FragmentListBinding;

import java.util.ArrayList;

public class ListFragment extends Fragment {
    private FragmentListBinding binding;
    private WatchViewModel viewModel;

    private ArrayList<Dto> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel =new ViewModelProvider(this).get(WatchViewModel.class);
        binding = FragmentListBinding.inflate(inflater, container, false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container.getContext());
        binding.recyclerview.setLayoutManager(linearLayoutManager);
        viewModel.addData();
        setViewModel();
        return binding.getRoot();
    }
    public void setViewModel(){
        ArrayList<Dto> list = viewModel.getList();//주머니에서 총알 꺼냇음  //B
        ListAdapter adapter = new ListAdapter(list);//총알을 총에 끼움 // C
        binding.recyclerview.setAdapter(adapter); //D
        adapter.notifyDataSetChanged(); //E
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
