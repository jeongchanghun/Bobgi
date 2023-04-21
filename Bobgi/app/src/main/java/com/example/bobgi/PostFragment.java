package com.example.bobgi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.bobgi.databinding.ActivityPostBinding;


public class PostFragment extends Fragment {
    private ActivityPostBinding binding;
    private Fragment fragment = new FragmentDeliver();
    private Fragment fragment1 = new FragmentRestaurant();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getChildFragmentManager().beginTransaction().replace(R.id.post_container,fragment).commit();

        binding = ActivityPostBinding.inflate(inflater,container,false);

        binding.haksik.setOnClickListener(view -> {
            if(binding.haksik.isChecked()){
                binding.postContainer.setVisibility(View.VISIBLE);
                getChildFragmentManager().beginTransaction().replace(R.id.post_container,fragment).commit();
            }
        });
        binding.local.setOnClickListener(view -> {
            if(binding.local.isChecked()){
                binding.postContainer.setVisibility(View.VISIBLE);
                getChildFragmentManager().beginTransaction().replace(R.id.post_container,fragment1).commit();
            }
        });

        binding.btnRegister.setOnClickListener(view-> {
            ((MainActivity) requireActivity()).moveTo(new ListFragment());
        });

        return binding.getRoot();
    }
}
