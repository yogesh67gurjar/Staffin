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

        binding.txtAttendance.setOnClickListener(v -> {
            binding.txtAttendance.setBackgroundResource(R.drawable.bg__blue_attendance);
            binding.txtAttendance.setTextColor(getResources().getColor(R.color.white));

            binding.txtViewAttendance.setBackgroundResource(R.drawable.bg_back_btn);
            binding.txtViewAttendance.setTextColor(getResources().getColor(R.color.black));

            binding.txtLeave.setBackgroundResource(R.drawable.bg_back_btn);
            binding.txtLeave.setTextColor(getResources().getColor(R.color.black));
        });

        binding.txtViewAttendance.setOnClickListener(v -> {

            binding.txtAttendance.setBackgroundResource(R.drawable.bg_back_btn);
            binding.txtAttendance.setTextColor(getResources().getColor(R.color.black));

            binding.txtViewAttendance.setBackgroundResource(R.drawable.bg__blue_attendance);
            binding.txtViewAttendance.setTextColor(getResources().getColor(R.color.white));

            binding.txtLeave.setBackgroundResource(R.drawable.bg_back_btn);
            binding.txtLeave.setTextColor(getResources().getColor(R.color.black));

        });

        binding.txtLeave.setOnClickListener(v -> {

            binding.txtAttendance.setBackgroundResource(R.drawable.bg_back_btn);
            binding.txtAttendance.setTextColor(getResources().getColor(R.color.black));

            binding.txtViewAttendance.setBackgroundResource(R.drawable.bg_back_btn);
            binding.txtViewAttendance.setTextColor(getResources().getColor(R.color.black));

            binding.txtLeave.setBackgroundResource(R.drawable.bg__blue_attendance);
            binding.txtLeave.setTextColor(getResources().getColor(R.color.white));

        });

    }
}