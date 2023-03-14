package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.staffin.databinding.ActivityAddEmployeeBinding;
import com.example.staffin.databinding.ActivityEmployeeIdBinding;

public class EmployeeIdActivity extends AppCompatActivity {
ActivityEmployeeIdBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityEmployeeIdBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}