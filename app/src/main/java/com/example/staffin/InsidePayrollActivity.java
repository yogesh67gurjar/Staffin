package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.staffin.databinding.ActivityInsidePayrollBinding;

import java.util.Calendar;

public class InsidePayrollActivity extends AppCompatActivity {
    ActivityInsidePayrollBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsidePayrollBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnHome.setOnClickListener(v -> {
            finish();
        });
        binding.nextBtn.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), SalaryInfoActivity.class));
        });
        binding.dateEt.setOnClickListener(v -> {
//            final Calendar c = Calendar.getInstance();
//
//            int year = c.get(Calendar.YEAR);
//            int month = c.get(Calendar.MONTH);
//            int day = c.get(Calendar.DAY_OF_MONTH);
//
//            DatePickerDialog datePickerDialog = new DatePickerDialog(InsidePayrollActivity.this,
//                    (view, year1, monthOfYear, dayOfMonth) -> {
//                        //
//                        binding.dateEt.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year1);
//                    },
//                    year, month, day);
//            datePickerDialog.show();
        });

    }
}