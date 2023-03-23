package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.staffin.Adapter.HolidayAdapter;
import com.example.staffin.databinding.ActivityCalendarSettingBinding;
import com.example.staffin.databinding.ActivityCompanyDetailsBinding;

public class CalendarSettingActivity extends AppCompatActivity {
    ActivityCalendarSettingBinding binding;
    static String from = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCalendarSettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (getIntent().hasExtra("from")) {
            from = getIntent().getStringExtra("from");
        }
        binding.btnBack.setOnClickListener(v -> {

            if (from.equalsIgnoreCase("mainactivity")) {
//                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                from = "";
                finish();
            } else {
                finish();
            }
        });
        binding.holidayRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.holidayRecyclerView.setAdapter(new HolidayAdapter(CalendarSettingActivity.this));
    }
}