package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.staffin.databinding.ActivityAddEmployeeBinding;
import com.example.staffin.databinding.ActivityEmployeeIdBinding;

public class EmployeeIdActivity extends AppCompatActivity {
    ActivityEmployeeIdBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEmployeeIdBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnBack.setOnClickListener(v -> {
            finish();
        });

        binding.btnNext.setOnClickListener(v -> {
            if (binding.userIdEt.getText().toString().trim().isEmpty()) {
                binding.userIdEt.setError("Enter Your Id");
                binding.userIdEt.requestFocus();
            } else if (binding.passwordEt.getText().toString().trim().isEmpty()) {
                binding.passwordEt.setError("Enter Your Password");
                binding.passwordEt.requestFocus();
            } else {
                startActivity(new Intent(getApplicationContext(), CompanyDetailsActivity.class));
            }
        });

    }
}