package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.staffin.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.card1.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), TotalEmployeeActivity.class));
        });
        binding.card2.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), AttendanceActivity.class));
        });
        binding.card3.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), EventActivity.class));
        });
        binding.card4.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(),PayrollActivity.class));
        });
    }
}