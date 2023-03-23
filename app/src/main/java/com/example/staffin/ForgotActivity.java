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
            if (binding.numberEt.getText().toString().trim().isEmpty()) {
                binding.numberEt.setError("Enter Mobile Number");
                binding.numberEt.requestFocus();
            } else if (binding.numberEt.getText().toString().trim().length() < 10) {
                binding.numberEt.setError("Enter Correct Mobile Number");
                binding.numberEt.requestFocus();
            } else {
                startActivity(new Intent(getApplicationContext(), VerificationActivity.class));
            }
        });

    }
}