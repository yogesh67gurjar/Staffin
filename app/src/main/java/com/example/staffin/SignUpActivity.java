package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.staffin.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {
    ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.nameEt.getText().toString().isEmpty()) {
                    binding.nameEt.setError("Enter Your Name");
                    binding.nameEt.requestFocus();
                } else if (binding.phoneEt.getText().toString().trim().isEmpty()) {
                    binding.phoneEt.setError("Enter Mobile Number");
                    binding.phoneEt.requestFocus();
                } else if (binding.phoneEt.getText().toString().trim().length() < 10) {
                    binding.phoneEt.setError("Enter Correct Mobile Number");
                    binding.phoneEt.requestFocus();
                } else if (binding.passwordEt.getText().toString().trim().isEmpty()) {
                    binding.passwordEt.setError("Enter Password");
                    binding.passwordEt.requestFocus();
                } else if (binding.passwordEt.getText().toString().trim().length() < 4) {
                    binding.passwordEt.setError("Enter password min 4 words");
                    binding.passwordEt.requestFocus();
                } else if (binding.confirmPasswordEt.getText().toString().trim().isEmpty()) {
                    binding.confirmPasswordEt.setError("Confirm Password");
                    binding.confirmPasswordEt.requestFocus();
                } else if (!binding.confirmPasswordEt.getText().toString().trim().equals(binding.passwordEt.getText().toString().trim())) {
                    binding.confirmPasswordEt.setError("Both passwords are not identical ");
                    binding.confirmPasswordEt.requestFocus();
                }else {
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        finish();
                    }
                }
            });
        }
    }