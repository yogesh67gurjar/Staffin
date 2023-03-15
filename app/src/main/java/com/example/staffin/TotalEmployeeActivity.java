package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.staffin.Adapter.TotalEmployeeAdapter;
import com.example.staffin.databinding.ActivityTotalEmployeeBinding;

public class TotalEmployeeActivity extends AppCompatActivity {

    ActivityTotalEmployeeBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTotalEmployeeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imgBtnAddEmployee.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), AddEmployeeActivity.class));
        });

        binding.totalEmployeeRv.setLayoutManager(new LinearLayoutManager(this));
        binding.totalEmployeeRv.setAdapter(new TotalEmployeeAdapter(TotalEmployeeActivity.this));

        binding.btnBack.setOnClickListener(v -> {
            finish();
        });
    }
}