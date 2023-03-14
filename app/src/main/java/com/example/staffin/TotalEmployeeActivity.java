package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.staffin.databinding.ActivityTotalEmployeeBinding;

public class TotalEmployeeActivity extends AppCompatActivity {

    ActivityTotalEmployeeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTotalEmployeeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



    }
}