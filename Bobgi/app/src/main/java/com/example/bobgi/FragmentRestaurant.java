package com.example.bobgi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bobgi.databinding.ActivityRestaurantBinding;

public class FragmentRestaurant extends Fragment {

    private ActivityRestaurantBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ActivityRestaurantBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
