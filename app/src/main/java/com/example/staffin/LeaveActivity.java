package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.staffin.Adapter.LeaveAdapter;
import com.example.staffin.Adapter.MonthAdapter;
import com.example.staffin.databinding.ActivityLeaveBinding;

import java.util.ArrayList;
import java.util.List;

public class LeaveActivity extends AppCompatActivity {
    ActivityLeaveBinding binding;
    LeaveAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLeaveBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.leaveRV.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LeaveAdapter(LeaveActivity.this);
        binding.leaveRV.setAdapter(adapter);

        binding.btnHome.setOnClickListener(v -> {
            finish();
        });


    }
}