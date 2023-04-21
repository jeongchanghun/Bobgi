package com.example.bobgi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bobgi.databinding.ActivityDeliverBinding;

public class FragmentDeliver extends Fragment {

    private ActivityDeliverBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ActivityDeliverBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
