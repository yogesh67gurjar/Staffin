package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.staffin.databinding.ActivityForgotBinding;

public class ForgotActivity extends AppCompatActivity {
    ActivityForgotBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgotBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.otpBtn.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), VerificationActivity.class));

        });

    }
}