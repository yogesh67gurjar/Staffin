package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import com.example.staffin.databinding.ActivityVerificationBinding;

public class VerificationActivity extends AppCompatActivity {
    ActivityVerificationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MoveNumToNext();

        binding.confirmBtn.setOnClickListener(v -> {

            if (binding.otp1.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Enter Otp", Toast.LENGTH_SHORT).show();

            } else if (binding.otp2.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Enter Otp", Toast.LENGTH_SHORT).show();

            } else if (binding.otp3.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Enter Otp", Toast.LENGTH_SHORT).show();

            } else if (binding.otp4.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Enter Otp", Toast.LENGTH_SHORT).show();

            } else {

                startActivity(new Intent(getApplicationContext(), ConfirmPasswordActivity.class));
                finish();
            }
        });

    }

    private void MoveNumToNext() {

        binding.otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    binding.otp2.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    binding.otp3.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    binding.otp4.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }
}