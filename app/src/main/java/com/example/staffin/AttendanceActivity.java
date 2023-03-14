package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.staffin.Adapter.AttendanceAdapter;
import com.example.staffin.Adapter.TotalEmployeeAdapter;
import com.example.staffin.databinding.ActivityAttendanceBinding;

public class AttendanceActivity extends AppCompatActivity {
    ActivityAttendanceBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAttendanceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.attendanceRv.setLayoutManager(new LinearLayoutManager(this));
        binding.attendanceRv.setAdapter(new AttendanceAdapter(AttendanceActivity.this));

    }
}