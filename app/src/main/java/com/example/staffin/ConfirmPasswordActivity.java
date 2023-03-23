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

            if (binding.newPasswordEt.getText().toString().trim().isEmpty()) {
                binding.newPasswordEt.setError("Enter New Password");
                binding.newPasswordEt.requestFocus();
            } else if (binding.newPasswordEt.getText().toString().trim().length() < 4) {
                binding.newPasswordEt.setError("Enter password min 4 words");
                binding.newPasswordEt.requestFocus();
            } else if (binding.confirmPasswordEt.getText().toString().trim().isEmpty()) {
                binding.confirmPasswordEt.setError("Confirm Password");
                binding.confirmPasswordEt.requestFocus();
            } else if (!binding.confirmPasswordEt.getText().toString().trim().equals(binding.newPasswordEt.getText().toString().trim())) {
                binding.confirmPasswordEt.setError("Both passwords are not identical ");
                binding.confirmPasswordEt.requestFocus();
            } else {

                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });

    }
}