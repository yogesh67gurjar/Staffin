package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.staffin.databinding.ActivityBankDetailsBinding;

public class BankDetailsActivity extends AppCompatActivity {
    ActivityBankDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBankDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnBack.setOnClickListener(v -> {
            onBackPressed();
        });

        binding.nextBtn.setOnClickListener(v -> {
            if (binding.holderEt.getText().toString().isEmpty()) {
                binding.holderEt.setError("Enter Holder Name");
                binding.holderEt.requestFocus();
            } else if (binding.accNoEt.getText().toString().trim().isEmpty()) {
                binding.accNoEt.setError("Enter Account Number");
                binding.accNoEt.requestFocus();
//            } else if (binding.ifscEt.getText().toString().trim().isEmpty()) {
//                binding.ifscEt.setError("Enter IFSC Code");
//                binding.ifscEt.requestFocus();
            } else if (binding.bankEt.getText().toString().trim().isEmpty()) {
                binding.bankEt.setError("Enter Bank Name");
                binding.bankEt.requestFocus();
            } else {



                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                Toast.makeText(this, "New Employee Added...", Toast.LENGTH_SHORT).show();
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