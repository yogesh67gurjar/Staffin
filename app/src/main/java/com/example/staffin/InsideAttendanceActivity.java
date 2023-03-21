package com.example.staffin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.staffin.Fragment.PresentBottomSheetFragment;
import com.example.staffin.databinding.ActivityInsideAttendanceBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class InsideAttendanceActivity extends AppCompatActivity {
    ActivityInsideAttendanceBinding binding;
    String name, status, empId, dpImg;

    private ArrayList<Date> dates;
    private Date date = new Date();
    Calendar cal = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsideAttendanceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        final ProgressDialog progressDialog = new ProgressDialog(InsideAttendanceActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        name = getIntent().getStringExtra("name");
        status = getIntent().getStringExtra("status");
        empId = getIntent().getStringExtra("empId");
        dpImg = getIntent().getStringExtra("dpImg");
        binding.nameTv.setText(name);
        binding.empId.setText("Emp. ID - " + empId);
        if (status.equalsIgnoreCase("absent")) {
            binding.indicator.setBackgroundResource(R.drawable.bg_red);
            binding.indicator.setText("Absent");
            binding.indicator.setTextColor(Color.WHITE);
        } else {
            binding.indicator.setBackgroundResource(R.drawable.bg_green);
            binding.indicator.setText("Present");
            binding.indicator.setTextColor(Color.WHITE);
        }

//        binding.Calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//
//            @Override
//            public void onSelectedDayChange(CalendarView view, int year, int month,
//                                            int dayOfMonth) {
//
//                Bundle bundle = new Bundle();
//                month += 1;
//                bundle.putString("Date", String.valueOf(dayOfMonth) + "-" + month + "-" + year);
//                PresentBottomSheetFragment presentBottomSheetFragment = new PresentBottomSheetFragment();
//                presentBottomSheetFragment.setArguments(bundle);
//                presentBottomSheetFragment.show(getSupportFragmentManager(), presentBottomSheetFragment.getTag());
//
////                binding.Calendar.setFocusedMonthDateColor(Color.BLACK);
//
//            }
//        });

 binding.Calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
     @Override
     public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
         Bundle bundle = new Bundle();

         Log.d("DATE", String.valueOf(dayOfMonth) + month + year);
         initializeCalendar(dayOfMonth,month,year);

         PresentBottomSheetFragment presentBottomSheetFragment = new PresentBottomSheetFragment();
         presentBottomSheetFragment.setArguments(bundle);
         presentBottomSheetFragment.show(getSupportFragmentManager(), presentBottomSheetFragment.getTag());
     }
 });
//        binding.Calendar.setOnDayClickListener(new OnDayClickListener() {
//            @Override
//            public void onDayClick(@NonNull EventDay eventDay) {
//                Bundle bundle = new Bundle();
////                eventDay += 1;
////                bundle.putString("Date", String.valueOf(dayOfMonth) + "-" + month + "-" + year);
//                Log.d("DATE", eventDay.getCalendar().getTime().toString());
//                Calendar cal = Calendar.getInstance();
//                cal.setTime(eventDay.getCalendar().getTime());
//                int year = cal.get(Calendar.YEAR);
//                int month = cal.get(Calendar.MONTH);
//                int day = cal.get(Calendar.DATE);
//                month+=1;
//                Log.d("DATE", String.valueOf(day) + month + year);
//                initializeCalendar(day,month,year);
//
//                PresentBottomSheetFragment presentBottomSheetFragment = new PresentBottomSheetFragment();
//                presentBottomSheetFragment.setArguments(bundle);
//                presentBottomSheetFragment.show(getSupportFragmentManager(), presentBottomSheetFragment.getTag());
//            }
//        });


        binding.btnBack.setOnClickListener(v -> {
            finish();
        });
        binding.imageButton.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), CalendarSettingActivity.class));
        });

        binding.indicator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                PresentBottomSheetFragment presentBottomSheetFragment = new PresentBottomSheetFragment();
//                presentBottomSheetFragment.show(getSupportFragmentManager(), presentBottomSheetFragment.getTag());

            }
        });
        progressDialog.dismiss();
    }

    private void initializeCalendar(int day,int month,int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        long milliTime = calendar.getTimeInMillis();

        binding.Calendar.setDate (milliTime, true, true);

    }


}