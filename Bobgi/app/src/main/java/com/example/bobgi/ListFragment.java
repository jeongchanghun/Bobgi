package com.example.bobgi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.bobgi.databinding.FragmentListBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListFragment extends Fragment {
    private FragmentListBinding binding;

    private WatchViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(WatchViewModel.class);
        binding = FragmentListBinding.inflate(inflater, container, false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container.getContext());
        viewModel.addData(); //A
        ArrayList<Dto> list = viewModel.getList();//주머니에서 총알 꺼냇음  //B
        ListAdapter adapter = new ListAdapter(list);//총알을 총에 끼움 // C
        binding.recyclerview.setAdapter(adapter); //D
        adapter.notifyDataSetChanged(); //E
        binding.recyclerview.setLayoutManager(linearLayoutManager);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
