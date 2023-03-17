package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.staffin.Adapter.EventAdapter;
import com.example.staffin.Adapter.MonthAdapter;
import com.example.staffin.databinding.ActivityEventBinding;

public class EventActivity extends AppCompatActivity {

    ActivityEventBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEventBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.EventMonthRv.setLayoutManager(new LinearLayoutManager(this));
        binding.EventMonthRv.setAdapter(new MonthAdapter(EventActivity.this));

        binding.btnCalendar.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), CalendarSettingActivity.class));
        });

        binding.btnBack.setOnClickListener(v -> {
            finish();
        });

    }
}