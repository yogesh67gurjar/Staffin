package com.example.staffin;

import static android.icu.lang.UCharacter.DecompositionType.SQUARE;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.staffin.Fragment.PresentBottomSheetFragment;
import com.example.staffin.Interface.ApiInterface;
import com.example.staffin.Response.AllHolidays;
import com.example.staffin.Response.GetMonthlyAttendance;
import com.example.staffin.Retrofit.RetrofitServices;

import com.example.staffin.databinding.ActivityInsideAttendanceBinding;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class InsideAttendanceActivity extends AppCompatActivity {
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
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        progressDialog = new ProgressDialog(InsideAttendanceActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();


        name = getIntent().getStringExtra("name");
        status = getIntent().getStringExtra("status");
        empId = getIntent().getStringExtra("empId");
        dpImg = getIntent().getStringExtra("dpImg");
        Id = getIntent().getIntExtra("Id", 0);
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
        progressDialog.show();
        callGetMonthlyAttendanceByEid.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<GetMonthlyAttendance> call, Response<GetMonthlyAttendance> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    binding.presentCount.setText(response.body().getPresentDay().toString());
                    binding.absentCount.setText(response.body().getAbsent().toString());
                    binding.paidLeaveCount.setText(response.body().getPaidLeaveCount().toString());
                    binding.lateCount.setText(response.body().getLateComing().toString());
                    binding.halfDayCount.setText(response.body().getHalfday().toString());
                    initializeCalendar(response.body().getPresentDate(), response.body().getAbsentDate(), response.body().getPaidLeaveDate(), response.body().getLateComingDate(), response.body().getHalfdayDate(), response.body().getHolidayDate());
                } else {
                    Log.d("kfndkfjn", response.message());
                    progressDialog.dismiss();
                    Toast.makeText(InsideAttendanceActivity.this, "some error orrured", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetMonthlyAttendance> call, Throwable t) {
                Log.d("kfndkfjn", t.getMessage());
                progressDialog.dismiss();
                Toast.makeText(InsideAttendanceActivity.this, "some failure orrured", Toast.LENGTH_SHORT).show();
            }
        });
        //////////////////////////

        binding.compactcalendarView.setUseThreeLetterAbbreviation(true);

        binding.btnBack.setOnClickListener(v -> {
            finish();
        });
        binding.imageButton.setOnClickListener(v -> {
            initDownload();

        });


        progressDialog.dismiss();


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

    private void initializeCalendar(List<GetMonthlyAttendance.PresentDate> presentDates, List<GetMonthlyAttendance.AbsentDate> absentDates, List<GetMonthlyAttendance.PaidLeaveDate> paidLeaveDates, List<GetMonthlyAttendance.LateComingDate> lateComingDates, List<GetMonthlyAttendance.HalfDayDate> halfDayDates, List<GetMonthlyAttendance.HolidayDate> holidayDates) {
        CompactCalendarView compactCalendarView = findViewById(R.id.compactcalendar_view);
        compactCalendarView.setLocale(TimeZone.getDefault(), Locale.ENGLISH);
        compactCalendarView.setUseThreeLetterAbbreviation(true);
        progressDialog.show();
        long milliTime;

        for (GetMonthlyAttendance.PresentDate singleUnit : presentDates) {
            String[] dateInParts = singleUnit.getDate().split("-");
            Log.d("DATEOFPRESENT", singleUnit.getDate());
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.parseInt(dateInParts[0]));
            calendar.set(Calendar.MONTH, Integer.parseInt(dateInParts[1]) - 1);
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateInParts[2]));
            milliTime = calendar.getTimeInMillis();
            Event e;
            Log.d("PRESENT_IN_MAP", singleUnit.getDate());
            mapPresent.put(singleUnit.getDate(), true);
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
            String[] dateInParts = singleUnit.getDate().split("-");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.parseInt(dateInParts[0]));
            calendar.set(Calendar.MONTH, Integer.parseInt(dateInParts[1]) - 1);
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateInParts[2]));
            milliTime = calendar.getTimeInMillis();
            Event e;
            Log.d("ABSENT_IN_MAP", singleUnit.getDate());
            mapAbsent.put(singleUnit.getDate(), true);
            e = new Event(getResources().getColor(R.color.calRed), milliTime, "present");
            binding.compactcalendarView.addEvent(e);
        }

        for (GetMonthlyAttendance.PaidLeaveDate singleUnit : paidLeaveDates) {
            Log.d("DATEOFPL", singleUnit.getDate());

            String[] dateInParts = singleUnit.getDate().split("-");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.parseInt(dateInParts[0]));
            calendar.set(Calendar.MONTH, Integer.parseInt(dateInParts[1]) - 1);
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateInParts[2]));
            milliTime = calendar.getTimeInMillis();
            Event e;
            Log.d("PAIDLEAVE_IN_MAP", singleUnit.getDate());
            mapPaidLeave.put(singleUnit.getDate(), true);
            e = new Event(getResources().getColor(R.color.calPurple), milliTime, "present");
            binding.compactcalendarView.addEvent(e);
        }

        for (GetMonthlyAttendance.LateComingDate singleUnit : lateComingDates) {
            Log.d("DATEOFLATE", singleUnit.getDate());

            String[] dateInParts = singleUnit.getDate().split("-");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.parseInt(dateInParts[0]));
            calendar.set(Calendar.MONTH, Integer.parseInt(dateInParts[1]) - 1);
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateInParts[2]));
            milliTime = calendar.getTimeInMillis();
            Event e;
            Log.d("LATE_IN_MAP", singleUnit.getDate());
            mapLateComing.put(singleUnit.getDate(), true);
            e = new Event(getResources().getColor(R.color.calBlue), milliTime, "present");
            binding.compactcalendarView.addEvent(e);
        }

        for (GetMonthlyAttendance.HalfDayDate singleUnit : halfDayDates) {
            Log.d("DATEOFHALF", singleUnit.getDate());

            String[] dateInParts = singleUnit.getDate().split("-");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.parseInt(dateInParts[0]));
            calendar.set(Calendar.MONTH, Integer.parseInt(dateInParts[1]) - 1);
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateInParts[2]));
            milliTime = calendar.getTimeInMillis();
            Event e;
            Log.d("HALFDAY_IN_MAP", singleUnit.getDate());
            mapHalfDay.put(singleUnit.getDate(), true);
            e = new Event(getResources().getColor(R.color.calOrange), milliTime, "present");
            binding.compactcalendarView.addEvent(e);
        }

        binding.compactcalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                List<Event> events = binding.compactcalendarView.getEvents(dateClicked);

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
                    } else if (mapAbsent.containsKey(key)) {
                        bundle.putString("color", "red");
                    } else if (mapHalfDay.containsKey(key)) {
                        bundle.putString("color", "orange");
                    } else if (mapPaidLeave.containsKey(key)) {
                        bundle.putString("color", "purple");
                    } else if (mapLateComing.containsKey(key)) {
                        bundle.putString("color", "blue");
                    } else {
                        bundle.putString("color", "black");
                    }
                } else if (month > 10 && day > 10) {
                    Log.d("CASE", "date and month dono bde he 10 se");
                    key = year + "-" + month + "-" + day;
                    Log.d("KEY", key);
                    if (mapPresent.containsKey(key)) {
                        bundle.putString("color", "green");
                    } else if (mapAbsent.containsKey(key)) {
                        bundle.putString("color", "red");
                    } else if (mapHalfDay.containsKey(key)) {
                        bundle.putString("color", "orange");
                    } else if (mapPaidLeave.containsKey(key)) {
                        bundle.putString("color", "purple");
                    } else if (mapLateComing.containsKey(key)) {
                        bundle.putString("color", "blue");
                    } else {
                        bundle.putString("color", "black");
                    }
                } else if (month < 10 && day > 10) {
                    Log.d("CASE", "month chota he 10 se");
                    key = year + "-" + zeroMonth + "-" + day;
                    Log.d("KEY", key);
                    if (mapPresent.containsKey(key)) {
                        bundle.putString("color", "green");
                    } else if (mapAbsent.containsKey(key)) {
                        bundle.putString("color", "red");
                    } else if (mapHalfDay.containsKey(key)) {
                        bundle.putString("color", "orange");
                    } else if (mapPaidLeave.containsKey(key)) {
                        bundle.putString("color", "purple");
                    } else if (mapLateComing.containsKey(key)) {
                        bundle.putString("color", "blue");
                    } else {
                        bundle.putString("color", "black");
                    }
                } else if (month > 10 && day < 10) {
                    Log.d("CASE", "month chota he 10 se");
                    key = year + "-" + month + "-" + zeroDate;
                    Log.d("KEY", key);
                    if (mapPresent.containsKey(key)) {
                        bundle.putString("color", "green");
                    } else if (mapAbsent.containsKey(key)) {
                        bundle.putString("color", "red");
                    } else if (mapHalfDay.containsKey(key)) {
                        bundle.putString("color", "orange");
                    } else if (mapPaidLeave.containsKey(key)) {
                        bundle.putString("color", "purple");
                    } else if (mapLateComing.containsKey(key)) {
                        bundle.putString("color", "blue");
                    } else {
                        bundle.putString("color", "black");
                    }
                }
                bundle.putString("Date", day + "-" + monthName + "-" + year);
                bundle.putInt("tareekh", day);
                bundle.putString("mahina", monthName);
                bundle.putInt("saal", year);


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
        progressDialog.dismiss();
    }

    private void initDownload() {
        String uri = "https://drive.google.com/file/d/1FrGQUneY7UNZCT8w33hPDHYgwhwwjNx5/view?usp=share_link";
        download(getApplicationContext(), "CodeSpeedy_writer", ".pdf", "Downloads", uri.trim());
    }

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