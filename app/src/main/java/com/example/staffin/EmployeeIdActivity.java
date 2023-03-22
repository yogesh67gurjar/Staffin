package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

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
            onBackPressed();
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

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            super.onKeyDown(keyCode, event);
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Can't Go Back", Toast.LENGTH_SHORT).show();

    }
}