package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.staffin.databinding.ActivityConfirmPasswordBinding;

public class ConfirmPasswordActivity extends AppCompatActivity {
    ActivityConfirmPasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConfirmPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.confirmBtn.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        });

    }
}