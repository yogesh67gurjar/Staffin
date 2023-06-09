package com.example.staffin;

import android.Manifest;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;

import android.graphics.Rect;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.bumptech.glide.Glide;
import com.example.staffin.Fragment.PresentBottomSheetFragment;
import com.example.staffin.Interface.ApiInterface;

import com.example.staffin.Response.GetMonthlyAttendance;
import com.example.staffin.Retrofit.RetrofitServices;

import com.example.staffin.databinding.ActivityInsideAttendanceBinding;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.android.material.snackbar.Snackbar;


import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import java.util.HashMap;

import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsideAttendanceActivity extends AppCompatActivity {
    public static final int STORAGE = 125;

    ActivityInsideAttendanceBinding binding;
    String name, status, empId, dpImg;
    int Id;
    DownloadManager manager;
    private Rect textSizeRect;
    private int heightPerDay;
    String key;
    ProgressDialog progressDialog;
    ApiInterface apiInterface;
    int month, year;
    HashMap<String, Boolean> mapPresent;
    HashMap<String, Boolean> mapAbsent;
    HashMap<String, Boolean> mapPaidLeave;
    HashMap<String, Boolean> mapHalfDay;
    HashMap<String, Boolean> mapLateComing;
    HashMap<String, Boolean> mapHoliday;
    HashMap<String, Boolean> mapDoublePresent;

    HashMap<String, Boolean> mapSickLeave;
    HashMap<String, Boolean> mapUnpaidLeave;
    String zeroMonth = "";
    String zeroDate = "";
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsideAttendanceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mapPresent = new HashMap<>();
        mapAbsent = new HashMap<>();
        mapPaidLeave = new HashMap<>();
        mapHalfDay = new HashMap<>();
        mapLateComing = new HashMap<>();
        mapHoliday = new HashMap<>();
        mapDoublePresent = new HashMap<>();
        mapSickLeave = new HashMap<>();
        mapUnpaidLeave = new HashMap<>();

        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        progressDialog = new ProgressDialog(InsideAttendanceActivity.this);
        progressDialog.setMessage("Loading...");
//        progressDialog.show();
        binding.lottie.setVisibility(View.VISIBLE);


        name = getIntent().getStringExtra("name");
        status = getIntent().getStringExtra("status");
        empId = getIntent().getStringExtra("empId");
        dpImg = getIntent().getStringExtra("dpImg");
        Id = getIntent().getIntExtra("Id", 0);
        binding.nameTv.setText(name);
        binding.empId.setText("Emp. ID - " + empId);
        Glide.with(InsideAttendanceActivity.this).load(dpImg).placeholder(R.drawable.img_dp).into(binding.userImage);
        if (status.equalsIgnoreCase("absent")) {
            binding.indicator.setBackgroundResource(R.drawable.bg_red);
            binding.indicator.setText("Absent");
            binding.indicator.setTextColor(Color.WHITE);
        } else {
            binding.indicator.setBackgroundResource(R.drawable.bg_green);
            binding.indicator.setText("Present");
            binding.indicator.setTextColor(Color.WHITE);
        }


        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        month = cal.get(Calendar.MONTH);
//                int day = cal.get(Calendar.DATE);
        month += 1;
        year = cal.get(Calendar.YEAR);
        switch (month) {
            case 1:
                binding.monthTv.setText("January  " + year);
                break;
            case 2:
                binding.monthTv.setText("February  " + year);
                break;
            case 3:
                binding.monthTv.setText("March  " + year);
                break;
            case 4:
                binding.monthTv.setText("April  " + year);
                break;
            case 5:
                binding.monthTv.setText("May  " + year);
                break;
            case 6:
                binding.monthTv.setText("June  " + year);
                break;
            case 7:
                binding.monthTv.setText("July  " + year);
                break;
            case 8:
                binding.monthTv.setText("August  " + year);
                break;
            case 9:
                binding.monthTv.setText("September  " + year);
                break;
            case 10:
                binding.monthTv.setText("October  " + year);
                break;
            case 11:
                binding.monthTv.setText("November  " + year);
                break;
            case 12:
                binding.monthTv.setText("December  " + year);
                break;
        }

        Call<GetMonthlyAttendance> callGetMonthlyAttendanceByEid = apiInterface.getMonthlyAttendanceByEid(month, year, Id);
//        progressDialog.show();
        binding.lottie.setVisibility(View.VISIBLE);
        callGetMonthlyAttendanceByEid.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<GetMonthlyAttendance> call, Response<GetMonthlyAttendance> response) {
                if (response.isSuccessful()) {
//                    progressDialog.dismiss();
                    binding.lottie.setVisibility(View.GONE);
                    binding.presentCount.setText(String.valueOf(Integer.parseInt(response.body().getPresentDay().toString()) + Integer.parseInt(response.body().getDouble_present_count().toString())));
                    binding.absentCount.setText(response.body().getAbsent().toString());
                    binding.paidLeaveCount.setText(response.body().getPaidLeaveCount().toString());
                    binding.lateCount.setText(response.body().getLateComing().toString());
                    binding.halfDayCount.setText(response.body().getHalfday().toString());
                    initializeCalendar(response.body().getPresentDate(), response.body().getAbsentDate(), response.body().getPaidLeaveDate(), response.body().getLateComingDate(), response.body().getHalfdayDate(), response.body().getHolidayDate(), response.body().getDouble_present_date(), response.body().getSick_leave_date(), response.body().getUnpaid_leave_date());

                } else {
                    Log.d("kfndkfjn", response.message());
//                    progressDialog.dismiss();
                    binding.lottie.setVisibility(View.GONE);
                    Toast.makeText(InsideAttendanceActivity.this, "some error orrured", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetMonthlyAttendance> call, Throwable t) {
                Log.d("kfndkfjn", t.getMessage());
//                progressDialog.dismiss();
                binding.lottie.setVisibility(View.GONE);
                Toast.makeText(InsideAttendanceActivity.this, "some failure orrured", Toast.LENGTH_SHORT).show();
            }
        });
        //////////////////////////

        binding.compactcalendarView.setUseThreeLetterAbbreviation(true);

        binding.btnBack.setOnClickListener(v -> {
            finish();
        });


//        progressDialog.dismiss();
        binding.lottie.setVisibility(View.GONE);

        binding.compactcalendarView.shouldScrollMonth(false);

    }

    private String makeStringMonth(int month) {
        switch (month) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default:
                return "Not Available";
        }
    }

    private void initializeCalendar(List<GetMonthlyAttendance.PresentDate> presentDates, List<GetMonthlyAttendance.AbsentDate> absentDates, List<GetMonthlyAttendance.PaidLeaveDate> paidLeaveDates, List<GetMonthlyAttendance.LateComingDate> lateComingDates, List<GetMonthlyAttendance.HalfDayDate> halfDayDates, List<GetMonthlyAttendance.HolidayDate> holidayDates, List<GetMonthlyAttendance.DoublePresentDate> doublePresentDates, List<GetMonthlyAttendance.SickLeaveDate> sickLeaveDates, List<GetMonthlyAttendance.UnpaidLeaveDate> unpaidLeaveDates) {
        CompactCalendarView compactCalendarView = findViewById(R.id.compactcalendar_view);
        compactCalendarView.setLocale(TimeZone.getDefault(), Locale.ENGLISH);
        compactCalendarView.setUseThreeLetterAbbreviation(true);
//        progressDialog.show();
        binding.lottie.setVisibility(View.VISIBLE);
        long milliTime;

        for (GetMonthlyAttendance.PresentDate singleUnit : presentDates) {
            String[] dateInParts;
            if (singleUnit.getDate().contains("T")) {
                dateInParts = singleUnit.getDate().split("T")[0].split("-");
            } else {
                dateInParts = singleUnit.getDate().split("-");
            }
            Log.d("DATEOFPRESENT", singleUnit.getDate());
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.parseInt(dateInParts[0]));
            calendar.set(Calendar.MONTH, Integer.parseInt(dateInParts[1]) - 1);
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateInParts[2]));
            milliTime = calendar.getTimeInMillis();
            Event e;
            Log.d("PRESENT_IN_MAP", singleUnit.getDate());
            mapPresent.put(dateInParts[0] + "-" + dateInParts[1] + "-" + dateInParts[2], true);
            e = new Event(getResources().getColor(R.color.calGreen), milliTime, "present");
            binding.compactcalendarView.addEvent(e);
        }

        for (GetMonthlyAttendance.DoublePresentDate singleUnit : doublePresentDates) {
            String[] dateInParts;
            if (singleUnit.getDate().contains("T")) {
                dateInParts = singleUnit.getDate().split("T")[0].split("-");
            } else {
                dateInParts = singleUnit.getDate().split("-");
            }
            Log.d("DATEOFDOUBLEPRESENT", singleUnit.getDate());
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.parseInt(dateInParts[0]));
            calendar.set(Calendar.MONTH, Integer.parseInt(dateInParts[1]) - 1);
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateInParts[2]));
            milliTime = calendar.getTimeInMillis();
            Event e;
            Log.d("DOUBLE_PRESENT_IN_MAP", singleUnit.getDate());
            mapDoublePresent.put(dateInParts[0] + "-" + dateInParts[1] + "-" + dateInParts[2], true);
            e = new Event(getResources().getColor(R.color.calGreen), milliTime, "present");
            binding.compactcalendarView.addEvent(e);
        }
//        for (GetMonthlyAttendance.HolidayDate singleUnit : holidayDates) {
//            String[] dateInParts = singleUnit.getDate().split("-");
//            Calendar calendar = Calendar.getInstance();
//            calendar.set(Calendar.YEAR, Integer.parseInt(dateInParts[0]));
//            calendar.set(Calendar.MONTH, Integer.parseInt(dateInParts[1]) - 1);
//            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateInParts[2]));
//            milliTime = calendar.getTimeInMillis();
//            Event e;
//            e = new Event(getResources().getColor(R.color.green), milliTime, "present");
//            binding.compactcalendarView.addEvent(e);
//        }

        for (GetMonthlyAttendance.AbsentDate singleUnit : absentDates) {
            Log.d("DATEOFABSENT", singleUnit.getDate());
            String[] dateInParts;
            if (singleUnit.getDate().contains("T")) {
                dateInParts = singleUnit.getDate().split("T")[0].split("-");
            } else {
                dateInParts = singleUnit.getDate().split("-");
            }
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.parseInt(dateInParts[0]));
            calendar.set(Calendar.MONTH, Integer.parseInt(dateInParts[1]) - 1);
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateInParts[2]));
            milliTime = calendar.getTimeInMillis();
            Event e;
            Log.d("ABSENT_IN_MAP", singleUnit.getDate());
            mapAbsent.put(dateInParts[0] + "-" + dateInParts[1] + "-" + dateInParts[2], true);
            e = new Event(getResources().getColor(R.color.calRed), milliTime, "present");
            binding.compactcalendarView.addEvent(e);
        }

        for (GetMonthlyAttendance.PaidLeaveDate singleUnit : paidLeaveDates) {
            Log.d("DATEOFPL", singleUnit.getDate());

            String[] dateInParts;
            if (singleUnit.getDate().contains("T")) {
                dateInParts = singleUnit.getDate().split("T")[0].split("-");
            } else {
                dateInParts = singleUnit.getDate().split("-");
            }
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.parseInt(dateInParts[0]));
            calendar.set(Calendar.MONTH, Integer.parseInt(dateInParts[1]) - 1);
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateInParts[2]));
            milliTime = calendar.getTimeInMillis();
            Event e;
            Log.d("PAIDLEAVE_IN_MAP", singleUnit.getDate());
            mapPaidLeave.put(dateInParts[0] + "-" + dateInParts[1] + "-" + dateInParts[2], true);
            e = new Event(getResources().getColor(R.color.calPurple), milliTime, "present");
            binding.compactcalendarView.addEvent(e);
        }
        for (GetMonthlyAttendance.SickLeaveDate singleUnit : sickLeaveDates) {
            Log.d("DATEOFSL", singleUnit.getDate());

            String[] dateInParts;
            if (singleUnit.getDate().contains("T")) {
                dateInParts = singleUnit.getDate().split("T")[0].split("-");
            } else {
                dateInParts = singleUnit.getDate().split("-");
            }
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.parseInt(dateInParts[0]));
            calendar.set(Calendar.MONTH, Integer.parseInt(dateInParts[1]) - 1);
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateInParts[2]));
            milliTime = calendar.getTimeInMillis();
            Event e;
            Log.d("SICKLEAVE_IN_MAP", singleUnit.getDate());
            mapSickLeave.put(dateInParts[0] + "-" + dateInParts[1] + "-" + dateInParts[2], true);
            e = new Event(getResources().getColor(R.color.sickLeave), milliTime, "present");
            binding.compactcalendarView.addEvent(e);
        }
        for (GetMonthlyAttendance.UnpaidLeaveDate singleUnit : unpaidLeaveDates) {
            Log.d("DATEOFUL", singleUnit.getDate());

            String[] dateInParts;
            if (singleUnit.getDate().contains("T")) {
                dateInParts = singleUnit.getDate().split("T")[0].split("-");
            } else {
                dateInParts = singleUnit.getDate().split("-");
            }
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.parseInt(dateInParts[0]));
            calendar.set(Calendar.MONTH, Integer.parseInt(dateInParts[1]) - 1);
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateInParts[2]));
            milliTime = calendar.getTimeInMillis();
            Event e;
            Log.d("UNPAIDLEAVE_IN_MAP", singleUnit.getDate());
            mapUnpaidLeave.put(dateInParts[0] + "-" + dateInParts[1] + "-" + dateInParts[2], true);
            e = new Event(getResources().getColor(R.color.unpaidLeave), milliTime, "present");
            binding.compactcalendarView.addEvent(e);
        }

        for (GetMonthlyAttendance.LateComingDate singleUnit : lateComingDates) {
            Log.d("DATEOFLATE", singleUnit.getDate());

            String[] dateInParts;
            if (singleUnit.getDate().contains("T")) {
                dateInParts = singleUnit.getDate().split("T")[0].split("-");
            } else {
                dateInParts = singleUnit.getDate().split("-");
            }
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.parseInt(dateInParts[0]));
            calendar.set(Calendar.MONTH, Integer.parseInt(dateInParts[1]) - 1);
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateInParts[2]));
            milliTime = calendar.getTimeInMillis();
            Event e;
            Log.d("LATE_IN_MAP", singleUnit.getDate());
            mapLateComing.put(dateInParts[0] + "-" + dateInParts[1] + "-" + dateInParts[2], true);
            e = new Event(getResources().getColor(R.color.calBlue), milliTime, "present");
            binding.compactcalendarView.addEvent(e);
        }

        for (GetMonthlyAttendance.HalfDayDate singleUnit : halfDayDates) {
            Log.d("DATEOFHALF", singleUnit.getDate());

            String[] dateInParts;
            if (singleUnit.getDate().contains("T")) {
                dateInParts = singleUnit.getDate().split("T")[0].split("-");
            } else {
                dateInParts = singleUnit.getDate().split("-");
            }
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.parseInt(dateInParts[0]));
            calendar.set(Calendar.MONTH, Integer.parseInt(dateInParts[1]) - 1);
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateInParts[2]));
            milliTime = calendar.getTimeInMillis();
            Event e;
            Log.d("HALFDAY_IN_MAP", singleUnit.getDate());
            mapHalfDay.put(dateInParts[0] + "-" + dateInParts[1] + "-" + dateInParts[2], true);
            e = new Event(getResources().getColor(R.color.calOrange), milliTime, "present");
            binding.compactcalendarView.addEvent(e);
        }

        binding.btnDownload.setOnClickListener(v -> {


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

                requestRuntimePermissionFunc("manageStorage");
            } else {
                requestRuntimePermissionFunc("storage");
            }


        });


        binding.compactcalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                List<Event> events = binding.compactcalendarView.getEvents(dateClicked);

                Log.d("presentssss", mapPresent.toString());
                Log.d("presentssss", mapAbsent.toString());
                Log.d("presentssss", mapPaidLeave.toString());
                Log.d("presentssss", mapLateComing.toString());
                Log.d("presentssss", mapHalfDay.toString());
                Log.d("presentssss", mapDoublePresent.toString());


                Bundle bundle = new Bundle();
                Calendar cal = Calendar.getInstance();
                cal.setTime(dateClicked);
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DATE);
                month += 1;
                String monthName = makeStringMonth(month);

                zeroMonth = "0" + month;
                zeroDate = "0" + day;
                if (month < 10 && day < 10) {
                    Log.d("CASE", "date and month dono chote he 10 se");

                    key = year + "-" + zeroMonth + "-" + zeroDate;
                    Log.d("KEY", key);
                    if (mapPresent.containsKey(key)) {
                        bundle.putString("color", "green");
                    } else if (mapDoublePresent.containsKey(key)) {
                        bundle.putString("color", "greend");
                    } else if (mapAbsent.containsKey(key)) {
                        bundle.putString("color", "red");
                    } else if (mapHalfDay.containsKey(key)) {
                        bundle.putString("color", "orange");
                    } else if (mapPaidLeave.containsKey(key)) {
                        bundle.putString("color", "purple");
                    } else if (mapLateComing.containsKey(key)) {
                        bundle.putString("color", "blue");
                    } else if (mapUnpaidLeave.containsKey(key)) {
                        bundle.putString("color", "unpaid");
                    } else if (mapSickLeave.containsKey(key)) {
                        bundle.putString("color", "sick");
                    } else {
                        bundle.putString("color", "black");
                    }
                } else if (month >= 10 && day >= 10) {
                    Log.d("CASE", "date and month dono bde he 10 se");
                    key = year + "-" + month + "-" + day;
                    Log.d("KEY", key);
                    if (mapPresent.containsKey(key)) {
                        bundle.putString("color", "green");
                    } else if (mapDoublePresent.containsKey(key)) {
                        bundle.putString("color", "greend");
                    } else if (mapAbsent.containsKey(key)) {
                        bundle.putString("color", "red");
                    } else if (mapHalfDay.containsKey(key)) {
                        bundle.putString("color", "orange");
                    } else if (mapPaidLeave.containsKey(key)) {
                        bundle.putString("color", "purple");
                    } else if (mapLateComing.containsKey(key)) {
                        bundle.putString("color", "blue");
                    } else if (mapUnpaidLeave.containsKey(key)) {
                        bundle.putString("color", "unpaid");
                    } else if (mapSickLeave.containsKey(key)) {
                        bundle.putString("color", "sick");
                    } else {
                        bundle.putString("color", "black");
                    }
                } else if (month < 10 && day >= 10) {
                    Log.d("CASE", "month chota he 10 se");
                    key = year + "-" + zeroMonth + "-" + day;
                    Log.d("KEY", key);
                    if (mapPresent.containsKey(key)) {
                        bundle.putString("color", "green");
                    } else if (mapDoublePresent.containsKey(key)) {
                        bundle.putString("color", "greend");
                    } else if (mapAbsent.containsKey(key)) {
                        bundle.putString("color", "red");
                    } else if (mapHalfDay.containsKey(key)) {
                        bundle.putString("color", "orange");
                    } else if (mapPaidLeave.containsKey(key)) {
                        bundle.putString("color", "purple");
                    } else if (mapUnpaidLeave.containsKey(key)) {
                        bundle.putString("color", "unpaid");
                    } else if (mapSickLeave.containsKey(key)) {
                        bundle.putString("color", "sick");
                    } else if (mapLateComing.containsKey(key)) {
                        bundle.putString("color", "blue");
                    } else {
                        bundle.putString("color", "black");
                    }
                } else if (month >= 10 && day < 10) {
                    Log.d("CASE", "month chota he 10 se");
                    key = year + "-" + month + "-" + zeroDate;
                    Log.d("KEY", key);
                    if (mapPresent.containsKey(key)) {
                        bundle.putString("color", "green");
                    } else if (mapDoublePresent.containsKey(key)) {
                        bundle.putString("color", "greend");
                    } else if (mapAbsent.containsKey(key)) {
                        bundle.putString("color", "red");
                    } else if (mapHalfDay.containsKey(key)) {
                        bundle.putString("color", "orange");
                    } else if (mapUnpaidLeave.containsKey(key)) {
                        bundle.putString("color", "unpaid");
                    } else if (mapSickLeave.containsKey(key)) {
                        bundle.putString("color", "sick");
                    } else if (mapPaidLeave.containsKey(key)) {
                        bundle.putString("color", "purple");
                    } else if (mapLateComing.containsKey(key)) {
                        bundle.putString("color", "blue");
                    } else {
                        bundle.putString("color", "black");
                    }
                }
                bundle.putInt("monthNumber", month);
                bundle.putString("Date", day + "-" + monthName + "-" + year);
                bundle.putInt("tareekh", day);
                bundle.putString("mahina", monthName);
                bundle.putInt("saal", year);
                bundle.putInt("Id", Id);


                PresentBottomSheetFragment presentBottomSheetFragment = new PresentBottomSheetFragment();
                presentBottomSheetFragment.setArguments(bundle);
                presentBottomSheetFragment.show(getSupportFragmentManager(), presentBottomSheetFragment.getTag());

                Log.d("CLICKED", "Day was clicked: " + dateClicked + " with events " + events);
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(firstDayOfNewMonth);
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
//                int day = cal.get(Calendar.DATE);
                month += 1;
                Log.d("CLICKED", "Month was scrolled to: " + month);
                switch (month) {
                    case 1:
                        binding.monthTv.setText("January  " + year);
                        break;
                    case 2:
                        binding.monthTv.setText("February  " + year);
                        break;
                    case 3:
                        binding.monthTv.setText("March  " + year);
                        break;
                    case 4:
                        binding.monthTv.setText("April  " + year);
                        break;
                    case 5:
                        binding.monthTv.setText("May  " + year);
                        break;
                    case 6:
                        binding.monthTv.setText("June  " + year);
                        break;
                    case 7:
                        binding.monthTv.setText("July  " + year);
                        break;
                    case 8:
                        binding.monthTv.setText("August  " + year);
                        break;
                    case 9:
                        binding.monthTv.setText("September  " + year);
                        break;
                    case 10:
                        binding.monthTv.setText("October  " + year);
                        break;
                    case 11:
                        binding.monthTv.setText("November  " + year);
                        break;
                    case 12:
                        binding.monthTv.setText("December  " + year);
                        break;
                }
            }
        });
//        progressDialog.dismiss();
        binding.lottie.setVisibility(View.GONE);
    }

    private void requestRuntimePermissionFunc(String str) {
        if (str.equals("storage")) {
            if (ContextCompat.checkSelfPermission(InsideAttendanceActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                generatePdf();
//                Toast.makeText(this, "storage permission already granted", Toast.LENGTH_SHORT).show();
            } else if (ActivityCompat.shouldShowRequestPermissionRationale(InsideAttendanceActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(InsideAttendanceActivity.this);
                builder.setMessage("this permission is required for this and this")
                        .setTitle("storage required")
                        .setCancelable(false)
                        .setPositiveButton("accept", (dialog, which) -> ActivityCompat.requestPermissions(InsideAttendanceActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE))
                        .setNegativeButton("reject", (dialog, which) -> dialog.dismiss())
                        .show();
            } else {
                ActivityCompat.requestPermissions(InsideAttendanceActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE);
            }
        } else if (str.equals("manageStorage")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                if (Environment.isExternalStorageManager()) {
                    // Permission is granted
                    generatePdf();
//                    Toast.makeText(this, "manage storage permission already granted", Toast.LENGTH_SHORT).show();
                    Log.d("dgdgsdfgsdfgs", "yes yes yes yes ");
                } else {
                    // Permission is not granted, request it
                    Log.d("dgdgsdfgsdfgs", "no no no no ");

                    Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                    intent.setData(uri);
                    startActivity(intent);
                }
            }

        }
    }

    private void generatePdf() {

//            // Create a PDDocument instance
//            PDDocument document = new PDDocument();
//
//// Create a PDPage instance
//            PDPage page = new PDPage();
//
//// Add the page to the document
//            document.addPage(page);
//
//// Create a PDPageContentStream instance for the page
//            PDPageContentStream contentStream = null;
//            try {
//                contentStream = new PDPageContentStream(document, page);
//
//
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//
//// Create a PDImageXObject instance from the screenshot Bitmap
//            PDImageXObject imageXObject = LosslessFactory.createFromImage(document, bitmap);
//
//            try {
//                // Get the width and height of the image
//                float imageWidth = imageXObject.getWidth();
//                float imageHeight = imageXObject.getHeight();
//
//                // Set the position and size of the image on the page
//                float x = 0;
//                float y = page.getMediaBox().getHeight() - imageHeight;
//                float width = page.getMediaBox().getWidth();
//                float height = imageHeight;
//
//                // Draw the image on the page
//                contentStream.drawImage(imageXObject, x, y, width, height);
//
//                // Close the content stream
//                contentStream.close();
//
//                // Save the document to a file
//                File file = new File(Environment.getExternalStorageDirectory() + "/screenshot.pdf");
//                document.save(file);
//
//                Toast.makeText(this, "Screenshot saved to " + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
//            } catch (IOException e) {
//                e.printStackTrace();
//                Toast.makeText(this, "Error saving screenshot", Toast.LENGTH_SHORT).show();
//            } finally {
//                // Close the document
//                document.close();
//
//                // Clear the Bitmap
//                bitmap.recycle();
//            }


//            try {
//                PdfDocument pdfDocument = new PdfDocument(new PdfWriter("output.pdf"));
//                Document document = new Document(pdfDocument);
//
//                // Create a table with 3 columns
//                Table table = new Table(3);
//
//                // Add table headers
//                table.addHeaderCell("Header 1");
//                table.addHeaderCell("Header 2");
//                table.addHeaderCell("Header 3");
//
//                // Add table rows
//                table.addCell("Row 1, Cell 1");
//                table.addCell("Row 1, Cell 2");
//                table.addCell("Row 1, Cell 3");
//
//                table.addCell("Row 2, Cell 1");
//                table.addCell("Row 2, Cell 2");
//                table.addCell("Row 2, Cell 3");
//
//                // Add the table to the document
//                document.add(table);
//
//                // Close the document
//                document.close();
//
//                // Download the PDF file
//                File pdfFile = new File("output.pdf");
//                // Modify the file path according to your requirements
//
//                // Trigger the file download
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                Uri uri = FileProvider.getUriForFile(this, getPackageName() + ".provider", pdfFile);
//                intent.setDataAndType(uri, "application/pdf");
//                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                startActivity(intent);
//            } catch (FileNotFoundException e) {
//                throw new RuntimeException(e);
//            }

//            initDownload();
        View rootView = getWindow().getDecorView().getRootView();
        rootView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(rootView.getDrawingCache());
        rootView.setDrawingCacheEnabled(false);

        // Save the Bitmap to a file
//            File file = new File(Environment.getExternalStorageDirectory() + "/screenshot.png");
//            try {
//                FileOutputStream fos = new FileOutputStream(file);
//                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
//                fos.flush();
//                fos.close();
//                Toast.makeText(this, "Attendance Saved To " + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
//            } catch (IOException e) {
//                e.printStackTrace();
//                Toast.makeText(this, "Error Saving Attendance", Toast.LENGTH_SHORT).show();
//            }


        // Create a new document
        PdfDocument document = new PdfDocument();

// Create a blank page with the desired dimensions
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(bitmap.getWidth(), bitmap.getHeight(), 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

// Get the canvas for drawing on the page
        Canvas canvas = page.getCanvas();

// Draw the screenshot bitmap on the canvas
        canvas.drawBitmap(bitmap, 0, 0, null);

// Finish the page
        document.finishPage(page);

// Save the document to a file
        File file = new File(Environment.getExternalStorageDirectory() + "/Attendance.pdf");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            document.writeTo(fos);
            fos.close();
            Toast.makeText(this, "attendance saved to " + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Log.e("eeeeeee", e.toString());
            e.printStackTrace();
            Toast.makeText(this, "Error saving attendance", Toast.LENGTH_SHORT).show();
        }

// Close the document
        document.close();

// Clear the Bitmap
        bitmap.recycle();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE) {// storage
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "accepted", Toast.LENGTH_SHORT).show();
                generatePdf();
            } else if (!ActivityCompat.shouldShowRequestPermissionRationale(InsideAttendanceActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(InsideAttendanceActivity.this);
                builder.setMessage("this feature is unavailable , now open settings ")
                        .setTitle("storage to chaiye")
                        .setCancelable(false)
                        .setPositiveButton("accept", (dialog, which) -> {
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", getPackageName(), null);
                            intent.setData(uri);
                            startActivity(intent);
                            dialog.dismiss();
                        })
                        .setNegativeButton("reject", (dialog, which) -> dialog.dismiss())
                        .show();
            } else {
                requestRuntimePermissionFunc("storage");
            }
        }
    }

//    private void initDownload() {
//        String uri = "https://drive.google.com/file/d/1FrGQUneY7UNZCT8w33hPDHYgwhwwjNx5/view?usp=share_link";
//        download(getApplicationContext(), "CodeSpeedy_writer", ".pdf", "Downloads", uri.trim());
//    }

    private void download(Context context, String Filename, String FileExtension, String DesignationDirectory, String url) {
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, DesignationDirectory, Filename + FileExtension);
        assert downloadManager != null;
        downloadManager.enqueue(request);
        Snackbar snackbar = (Snackbar) Snackbar
                .make(findViewById(android.R.id.content), "Downloading...", Snackbar.LENGTH_LONG);
        snackbar.show();
    }


}