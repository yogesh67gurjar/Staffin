package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.staffin.Adapter.AttendanceAdapter;
import com.example.staffin.Adapter.TotalEmployeeAdapter;
import com.example.staffin.databinding.ActivityAttendanceBinding;

import java.util.ArrayList;
import java.util.List;

public class AttendanceActivity extends AppCompatActivity {
    ActivityAttendanceBinding binding;
    List<String> employeeNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAttendanceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        employeeNumber = new ArrayList<>();
        employeeNumber.add("+91 7000563592");
        employeeNumber.add("+91 7000563593");
        employeeNumber.add("+91 7000563594");
        employeeNumber.add("+91 7000563595");
        employeeNumber.add("+91 7000563596");
        employeeNumber.add("+91 7000563597");
        employeeNumber.add("+91 7000563598");
        employeeNumber.add("+91 7000563599");
        employeeNumber.add("+91 7000563590");
        employeeNumber.add("+91 7000563100");



        binding.btnBack.setOnClickListener(v -> {
            finish();
        });
        binding.btnCalendar.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), CalendarSettingActivity.class));
        });
        binding.attendanceRv.setLayoutManager(new LinearLayoutManager(this));
        binding.attendanceRv.setAdapter(new AttendanceAdapter(AttendanceActivity.this,employeeNumber));

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