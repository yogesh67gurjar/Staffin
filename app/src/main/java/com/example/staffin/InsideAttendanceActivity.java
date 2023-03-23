package com.example.staffin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.staffin.Fragment.PresentBottomSheetFragment;
import com.example.staffin.databinding.ActivityInsideAttendanceBinding;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class InsideAttendanceActivity extends AppCompatActivity {
    ActivityInsideAttendanceBinding binding;
    String name, status, empId, dpImg;
    DownloadManager manager;


    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsideAttendanceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.compactcalendarView.setUseThreeLetterAbbreviation(true);

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

// binding.Calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//     @Override
//     public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
//         Bundle bundle = new Bundle();
//
//         Log.d("DATE", String.valueOf(dayOfMonth) + month + year);
//         initializeCalendar(dayOfMonth,month,year);
//
//         PresentBottomSheetFragment presentBottomSheetFragment = new PresentBottomSheetFragment();
//         presentBottomSheetFragment.setArguments(bundle);
//         presentBottomSheetFragment.show(getSupportFragmentManager(), presentBottomSheetFragment.getTag());
//     }
// });
//        binding.calendarView.setOnDayClickListener(new OnDayClickListener() {
//            @Override
//            public void onDayClick(@NonNull EventDay eventDay) {
//                Bundle bundle = new Bundle();
//                Log.d("DATE", eventDay.getCalendar().getTime().toString());
//                Calendar cal = Calendar.getInstance();
//                cal.setTime(eventDay.getCalendar().getTime());
//                int year = cal.get(Calendar.YEAR);
//                int month = cal.get(Calendar.MONTH);
//                int day = cal.get(Calendar.DATE);
//                month += 1;
//                Log.d("DATE", String.valueOf(day) + month + year);
//                initializeCalendar(day, month, year);
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
            initDownload();

        });
        initializeCalendar();

        progressDialog.dismiss();

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
                Log.d("DATE", String.valueOf(day) + month + year);
                bundle.putString("Date", day + "-" + month + "-" + year);
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
    }

    private void initializeCalendar() {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.YEAR, 2023);
        calendar1.set(Calendar.MONTH, 2);
        calendar1.set(Calendar.DAY_OF_MONTH, 6);
        long milliTime = calendar1.getTimeInMillis();

        Event ev1 = new Event(Color.RED, milliTime, "Teachers' Professional Day");
        binding.compactcalendarView.addEvent(ev1);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.YEAR, 2023);
        calendar2.set(Calendar.MONTH, 2);
        calendar2.set(Calendar.DAY_OF_MONTH, 7);
        milliTime = calendar2.getTimeInMillis();

        Event ev2 = new Event(Color.BLUE, milliTime, "Teachers' Professional Day");
        binding.compactcalendarView.addEvent(ev2);

        Calendar calendar3 = Calendar.getInstance();
        calendar3.set(Calendar.YEAR, 2023);
        calendar3.set(Calendar.MONTH, 2);
        calendar3.set(Calendar.DAY_OF_MONTH, 8);
        milliTime = calendar3.getTimeInMillis();

        Event ev3 = new Event(Color.GREEN, milliTime, "Teachers' Professional Day");
        binding.compactcalendarView.addEvent(ev3);

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