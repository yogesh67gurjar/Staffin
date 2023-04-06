package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.staffin.databinding.ActivityPaySlipBinding;

public class PaySlipActivity extends AppCompatActivity {
    ActivityPaySlipBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaySlipBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnHome.setOnClickListener(v -> {
            finish();
        });
        binding.btnShare.setOnClickListener(v -> {
            finish();
        });
    }
}