package com.example.bobgi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.myapplication.databinding.FragmentLoadingBinding;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        FragmentLoadingBinding binding = FragmentLoadingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        LoadingStart();
    }

    private void LoadingStart() {
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }, 2000);
    }
}
