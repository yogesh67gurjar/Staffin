package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.staffin.Adapter.PayRollAdapter;
import com.example.staffin.databinding.ActivityPayrollBinding;

public class PayrollActivity extends AppCompatActivity {

    ActivityPayrollBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPayrollBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.PayRollRv.setLayoutManager(new LinearLayoutManager(this));
        binding.PayRollRv.setAdapter(new PayRollAdapter(PayrollActivity.this));

        binding.btnHome.setOnClickListener(v -> {
            finish();
        });

        binding.btnCalendar.setOnClickListener(v -> {

            startActivity(new Intent(getApplicationContext(), CalendarSettingActivity.class));

        });


    }
}