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
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.staffin.Fragment.PresentBottomSheetFragment;
import com.example.staffin.databinding.ActivityInsideAttendanceBinding;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class InsideAttendanceActivity extends AppCompatActivity {
    ActivityInsideAttendanceBinding binding;
    String name, status, empId, dpImg;
    DownloadManager manager;
    private Rect textSizeRect;
    private int heightPerDay;


    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsideAttendanceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
//                int day = cal.get(Calendar.DATE);
        month += 1;
        int year = cal.get(Calendar.YEAR);
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


        binding.compactcalendarView.shouldScrollMonth(false);
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
                String monthName = makeStringMonth(month);
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

    private void initializeCalendar() {
        long milliTime;
        CompactCalendarView compactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendarView.setLocale(TimeZone.getDefault(), Locale.ENGLISH);
        compactCalendarView.setUseThreeLetterAbbreviation(true);


        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.YEAR, 2023);
        calendar1.set(Calendar.MONTH, 2);
        calendar1.set(Calendar.DAY_OF_MONTH, 1);
        milliTime = calendar1.getTimeInMillis();

        Event ev1 = new Event(getResources().getColor(R.color.calRed), milliTime, "Teachers' Professional Day");
        
        binding.compactcalendarView.addEvent(ev1);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.YEAR, 2023);
        calendar2.set(Calendar.MONTH, 2);
        calendar2.set(Calendar.DAY_OF_MONTH, 2);
        milliTime = calendar2.getTimeInMillis();
        Event ev2 = new Event(getResources().getColor(R.color.calBlue), milliTime, "Teachers' Professional Day");
        binding.compactcalendarView.addEvent(ev2);

        Calendar calendar3 = Calendar.getInstance();
        calendar3.set(Calendar.YEAR, 2023);
        calendar3.set(Calendar.MONTH, 2);
        calendar3.set(Calendar.DAY_OF_MONTH, 3);
        milliTime = calendar3.getTimeInMillis();
        Event ev3 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
        binding.compactcalendarView.addEvent(ev3);

        Calendar calendar4 = Calendar.getInstance();
        calendar4.set(Calendar.YEAR, 2023);
        calendar4.set(Calendar.MONTH, 2);
        calendar4.set(Calendar.DAY_OF_MONTH, 4);
        milliTime = calendar4.getTimeInMillis();
        Event ev4 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
        binding.compactcalendarView.addEvent(ev4);

        Calendar calendar5 = Calendar.getInstance();
        calendar5.set(Calendar.YEAR, 2023);
        calendar5.set(Calendar.MONTH, 2);
        calendar5.set(Calendar.DAY_OF_MONTH, 5);
        milliTime = calendar5.getTimeInMillis();
        Event ev5 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
        binding.compactcalendarView.addEvent(ev5);

        Calendar calendar6 = Calendar.getInstance();
        calendar6.set(Calendar.YEAR, 2023);
        calendar6.set(Calendar.MONTH, 2);
        calendar6.set(Calendar.DAY_OF_MONTH, 6);
        milliTime = calendar6.getTimeInMillis();
        Event ev6 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
        binding.compactcalendarView.addEvent(ev6);


        Calendar calendar7 = Calendar.getInstance();
        calendar7.set(Calendar.YEAR, 2023);
        calendar7.set(Calendar.MONTH, 2);
        calendar7.set(Calendar.DAY_OF_MONTH, 7);
        milliTime = calendar7.getTimeInMillis();
        Event ev7 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
        binding.compactcalendarView.addEvent(ev7);

        Calendar calendar8 = Calendar.getInstance();
        calendar8.set(Calendar.YEAR, 2023);
        calendar8.set(Calendar.MONTH, 2);
        calendar8.set(Calendar.DAY_OF_MONTH, 8);
        milliTime = calendar8.getTimeInMillis();
        Event ev8 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
        binding.compactcalendarView.addEvent(ev8);

        Calendar calendar9 = Calendar.getInstance();
        calendar9.set(Calendar.YEAR, 2023);
        calendar9.set(Calendar.MONTH, 2);
        calendar9.set(Calendar.DAY_OF_MONTH, 9);
        milliTime = calendar9.getTimeInMillis();
        Event ev9 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
        binding.compactcalendarView.addEvent(ev9);

        Calendar calendar10 = Calendar.getInstance();
        calendar10.set(Calendar.YEAR, 2023);
        calendar10.set(Calendar.MONTH, 2);
        calendar10.set(Calendar.DAY_OF_MONTH, 10);
        milliTime = calendar10.getTimeInMillis();
        Event ev10 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
        binding.compactcalendarView.addEvent(ev10);

        Calendar calendar11 = Calendar.getInstance();
        calendar11.set(Calendar.YEAR, 2023);
        calendar11.set(Calendar.MONTH, 2);
        calendar11.set(Calendar.DAY_OF_MONTH, 11);
        milliTime = calendar11.getTimeInMillis();
        Event ev11 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
        binding.compactcalendarView.addEvent(ev11);

        Calendar calendar12 = Calendar.getInstance();
        calendar12.set(Calendar.YEAR, 2023);
        calendar12.set(Calendar.MONTH, 2);
        calendar12.set(Calendar.DAY_OF_MONTH, 12);
        milliTime = calendar12.getTimeInMillis();
        Event ev12 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
        binding.compactcalendarView.addEvent(ev12);

        Calendar calendar13 = Calendar.getInstance();
        calendar13.set(Calendar.YEAR, 2023);
        calendar13.set(Calendar.MONTH, 2);
        calendar13.set(Calendar.DAY_OF_MONTH, 13);
        milliTime = calendar13.getTimeInMillis();
        Event ev13 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
        binding.compactcalendarView.addEvent(ev13);

        Calendar calendar14 = Calendar.getInstance();
        calendar14.set(Calendar.YEAR, 2023);
        calendar14.set(Calendar.MONTH, 2);
        calendar14.set(Calendar.DAY_OF_MONTH, 14);
        milliTime = calendar14.getTimeInMillis();
        Event ev14 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
        binding.compactcalendarView.addEvent(ev14);

        Calendar calendar15 = Calendar.getInstance();
        calendar15.set(Calendar.YEAR, 2023);
        calendar15.set(Calendar.MONTH, 2);
        calendar15.set(Calendar.DAY_OF_MONTH, 15);
        milliTime = calendar15.getTimeInMillis();
        Event ev15 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
        binding.compactcalendarView.addEvent(ev15);

        Calendar calendar16 = Calendar.getInstance();
        calendar16.set(Calendar.YEAR, 2023);
        calendar16.set(Calendar.MONTH, 2);
        calendar16.set(Calendar.DAY_OF_MONTH, 16);
        milliTime = calendar16.getTimeInMillis();
        Event ev16 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
        binding.compactcalendarView.addEvent(ev16);

        Calendar calendar17 = Calendar.getInstance();
        calendar17.set(Calendar.YEAR, 2023);
        calendar17.set(Calendar.MONTH, 2);
        calendar17.set(Calendar.DAY_OF_MONTH, 17);
        milliTime = calendar17.getTimeInMillis();
        Event ev17 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
        binding.compactcalendarView.addEvent(ev17);

        Calendar calendar24 = Calendar.getInstance();
        calendar24.set(Calendar.YEAR, 2023);
        calendar24.set(Calendar.MONTH, 2);
        calendar24.set(Calendar.DAY_OF_MONTH, 24);
        milliTime = calendar24.getTimeInMillis();
        Event ev24 = new Event(getResources().getColor(R.color.calYellow), milliTime, "Teachers' Professional Day");
        binding.compactcalendarView.addEvent(ev24);

        Calendar calendar26 = Calendar.getInstance();
        calendar26.set(Calendar.YEAR, 2023);
        calendar26.set(Calendar.MONTH, 2);
        calendar26.set(Calendar.DAY_OF_MONTH, 26);
        milliTime = calendar26.getTimeInMillis();
        Event ev26 = new Event(getResources().getColor(R.color.calBlue), milliTime, "Teachers' Professional Day");
        binding.compactcalendarView.addEvent(ev26);


        Calendar calendar30 = Calendar.getInstance();
        calendar30.set(Calendar.YEAR, 2023);
        calendar30.set(Calendar.MONTH, 2);
        calendar30.set(Calendar.DAY_OF_MONTH, 30);
        milliTime = calendar30.getTimeInMillis();
        Event ev30 = new Event(getResources().getColor(R.color.txtOrange), milliTime, "Teachers' Professional Day");
        binding.compactcalendarView.addEvent(ev30);
//        Calendar calendar3 = Calendar.getInstance();
//        calendar3.set(Calendar.YEAR, 2023);
//        calendar3.set(Calendar.MONTH, 2);
//        calendar3.set(Calendar.DAY_OF_MONTH, 3);
//        milliTime = calendar3.getTimeInMillis();
//        Event ev3 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
//        binding.compactcalendarView.addEvent(ev3);
//        Calendar calendar3 = Calendar.getInstance();
//        calendar3.set(Calendar.YEAR, 2023);
//        calendar3.set(Calendar.MONTH, 2);
//        calendar3.set(Calendar.DAY_OF_MONTH, 3);
//        milliTime = calendar3.getTimeInMillis();
//        Event ev3 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
//        binding.compactcalendarView.addEvent(ev3);
//        Calendar calendar3 = Calendar.getInstance();
//        calendar3.set(Calendar.YEAR, 2023);
//        calendar3.set(Calendar.MONTH, 2);
//        calendar3.set(Calendar.DAY_OF_MONTH, 3);
//        milliTime = calendar3.getTimeInMillis();
//        Event ev3 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
//        binding.compactcalendarView.addEvent(ev3);
//        Calendar calendar3 = Calendar.getInstance();
//        calendar3.set(Calendar.YEAR, 2023);
//        calendar3.set(Calendar.MONTH, 2);
//        calendar3.set(Calendar.DAY_OF_MONTH, 3);
//        milliTime = calendar3.getTimeInMillis();
//        Event ev3 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
//        binding.compactcalendarView.addEvent(ev3);
//        Calendar calendar3 = Calendar.getInstance();
//        calendar3.set(Calendar.YEAR, 2023);
//        calendar3.set(Calendar.MONTH, 2);
//        calendar3.set(Calendar.DAY_OF_MONTH, 3);
//        milliTime = calendar3.getTimeInMillis();
//        Event ev3 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
//        binding.compactcalendarView.addEvent(ev3);
//        Calendar calendar3 = Calendar.getInstance();
//        calendar3.set(Calendar.YEAR, 2023);
//        calendar3.set(Calendar.MONTH, 2);
//        calendar3.set(Calendar.DAY_OF_MONTH, 3);
//        milliTime = calendar3.getTimeInMillis();
//        Event ev3 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
//        binding.compactcalendarView.addEvent(ev3);
//        Calendar calendar3 = Calendar.getInstance();
//        calendar3.set(Calendar.YEAR, 2023);
//        calendar3.set(Calendar.MONTH, 2);
//        calendar3.set(Calendar.DAY_OF_MONTH, 3);
//        milliTime = calendar3.getTimeInMillis();
//        Event ev3 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
//        binding.compactcalendarView.addEvent(ev3);
//        Calendar calendar3 = Calendar.getInstance();
//        calendar3.set(Calendar.YEAR, 2023);
//        calendar3.set(Calendar.MONTH, 2);
//        calendar3.set(Calendar.DAY_OF_MONTH, 3);
//        milliTime = calendar3.getTimeInMillis();
//        Event ev3 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
//        binding.compactcalendarView.addEvent(ev3);
//        Calendar calendar3 = Calendar.getInstance();
//        calendar3.set(Calendar.YEAR, 2023);
//        calendar3.set(Calendar.MONTH, 2);
//        calendar3.set(Calendar.DAY_OF_MONTH, 3);
//        milliTime = calendar3.getTimeInMillis();
//        Event ev3 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
//        binding.compactcalendarView.addEvent(ev3);
//        Calendar calendar3 = Calendar.getInstance();
//        calendar3.set(Calendar.YEAR, 2023);
//        calendar3.set(Calendar.MONTH, 2);
//        calendar3.set(Calendar.DAY_OF_MONTH, 3);
//        milliTime = calendar3.getTimeInMillis();
//        Event ev3 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
//        binding.compactcalendarView.addEvent(ev3);
//        Calendar calendar3 = Calendar.getInstance();
//        calendar3.set(Calendar.YEAR, 2023);
//        calendar3.set(Calendar.MONTH, 2);
//        calendar3.set(Calendar.DAY_OF_MONTH, 3);
//        milliTime = calendar3.getTimeInMillis();
//        Event ev3 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
//        binding.compactcalendarView.addEvent(ev3);
//        Calendar calendar3 = Calendar.getInstance();
//        calendar3.set(Calendar.YEAR, 2023);
//        calendar3.set(Calendar.MONTH, 2);
//        calendar3.set(Calendar.DAY_OF_MONTH, 3);
//        milliTime = calendar3.getTimeInMillis();
//        Event ev3 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
//        binding.compactcalendarView.addEvent(ev3);
//        Calendar calendar3 = Calendar.getInstance();
//        calendar3.set(Calendar.YEAR, 2023);
//        calendar3.set(Calendar.MONTH, 2);
//        calendar3.set(Calendar.DAY_OF_MONTH, 3);
//        milliTime = calendar3.getTimeInMillis();
//        Event ev3 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
//        binding.compactcalendarView.addEvent(ev3);
//        Calendar calendar3 = Calendar.getInstance();
//        calendar3.set(Calendar.YEAR, 2023);
//        calendar3.set(Calendar.MONTH, 2);
//        calendar3.set(Calendar.DAY_OF_MONTH, 3);
//        milliTime = calendar3.getTimeInMillis();
//        Event ev3 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
//        binding.compactcalendarView.addEvent(ev3);
//        Calendar calendar3 = Calendar.getInstance();
//        calendar3.set(Calendar.YEAR, 2023);
//        calendar3.set(Calendar.MONTH, 2);
//        calendar3.set(Calendar.DAY_OF_MONTH, 3);
//        milliTime = calendar3.getTimeInMillis();
//        Event ev3 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
//        binding.compactcalendarView.addEvent(ev3);
//        Calendar calendar3 = Calendar.getInstance();
//        calendar3.set(Calendar.YEAR, 2023);
//        calendar3.set(Calendar.MONTH, 2);
//        calendar3.set(Calendar.DAY_OF_MONTH, 3);
//        milliTime = calendar3.getTimeInMillis();
//        Event ev3 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
//        binding.compactcalendarView.addEvent(ev3);
//        Calendar calendar3 = Calendar.getInstance();
//        calendar3.set(Calendar.YEAR, 2023);
//        calendar3.set(Calendar.MONTH, 2);
//        calendar3.set(Calendar.DAY_OF_MONTH, 3);
//        milliTime = calendar3.getTimeInMillis();
//        Event ev3 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
//        binding.compactcalendarView.addEvent(ev3);
//        Calendar calendar3 = Calendar.getInstance();
//        calendar3.set(Calendar.YEAR, 2023);
//        calendar3.set(Calendar.MONTH, 2);
//        calendar3.set(Calendar.DAY_OF_MONTH, 3);
//        milliTime = calendar3.getTimeInMillis();
//        Event ev3 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
//        binding.compactcalendarView.addEvent(ev3);
//        Calendar calendar3 = Calendar.getInstance();
//        calendar3.set(Calendar.YEAR, 2023);
//        calendar3.set(Calendar.MONTH, 2);
//        calendar3.set(Calendar.DAY_OF_MONTH, 3);
//        milliTime = calendar3.getTimeInMillis();
//        Event ev3 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
//        binding.compactcalendarView.addEvent(ev3);
//        Calendar calendar3 = Calendar.getInstance();
//        calendar3.set(Calendar.YEAR, 2023);
//        calendar3.set(Calendar.MONTH, 2);
//        calendar3.set(Calendar.DAY_OF_MONTH, 3);
//        milliTime = calendar3.getTimeInMillis();
//        Event ev3 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
//        binding.compactcalendarView.addEvent(ev3);
//        Calendar calendar3 = Calendar.getInstance();
//        calendar3.set(Calendar.YEAR, 2023);
//        calendar3.set(Calendar.MONTH, 2);
//        calendar3.set(Calendar.DAY_OF_MONTH, 3);
//        milliTime = calendar3.getTimeInMillis();
//        Event ev3 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
//        binding.compactcalendarView.addEvent(ev3);
//        Calendar calendar3 = Calendar.getInstance();
//        calendar3.set(Calendar.YEAR, 2023);
//        calendar3.set(Calendar.MONTH, 2);
//        calendar3.set(Calendar.DAY_OF_MONTH, 3);
//        milliTime = calendar3.getTimeInMillis();
//        Event ev3 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
//        binding.compactcalendarView.addEvent(ev3);
//        Calendar calendar3 = Calendar.getInstance();
//        calendar3.set(Calendar.YEAR, 2023);
//        calendar3.set(Calendar.MONTH, 2);
//        calendar3.set(Calendar.DAY_OF_MONTH, 3);
//        milliTime = calendar3.getTimeInMillis();
//        Event ev3 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
//        binding.compactcalendarView.addEvent(ev3);
//        Calendar calendar3 = Calendar.getInstance();
//        calendar3.set(Calendar.YEAR, 2023);
//        calendar3.set(Calendar.MONTH, 2);
//        calendar3.set(Calendar.DAY_OF_MONTH, 3);
//        milliTime = calendar3.getTimeInMillis();
//        Event ev3 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
//        binding.compactcalendarView.addEvent(ev3);
//        Calendar calendar3 = Calendar.getInstance();
//        calendar3.set(Calendar.YEAR, 2023);
//        calendar3.set(Calendar.MONTH, 2);
//        calendar3.set(Calendar.DAY_OF_MONTH, 3);
//        milliTime = calendar3.getTimeInMillis();
//        Event ev3 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
//        binding.compactcalendarView.addEvent(ev3);
//        Calendar calendar3 = Calendar.getInstance();
//        calendar3.set(Calendar.YEAR, 2023);
//        calendar3.set(Calendar.MONTH, 2);
//        calendar3.set(Calendar.DAY_OF_MONTH, 3);
//        milliTime = calendar3.getTimeInMillis();
//        Event ev3 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
//        binding.compactcalendarView.addEvent(ev3);
//        Calendar calendar3 = Calendar.getInstance();
//        calendar3.set(Calendar.YEAR, 2023);
//        calendar3.set(Calendar.MONTH, 2);
//        calendar3.set(Calendar.DAY_OF_MONTH, 3);
//        milliTime = calendar3.getTimeInMillis();
//        Event ev3 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
//        binding.compactcalendarView.addEvent(ev3);
//        Calendar calendar3 = Calendar.getInstance();
//        calendar3.set(Calendar.YEAR, 2023);
//        calendar3.set(Calendar.MONTH, 2);
//        calendar3.set(Calendar.DAY_OF_MONTH, 3);
//        milliTime = calendar3.getTimeInMillis();
//        Event ev3 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
//        binding.compactcalendarView.addEvent(ev3);
//        Calendar calendar3 = Calendar.getInstance();
//        calendar3.set(Calendar.YEAR, 2023);
//        calendar3.set(Calendar.MONTH, 2);
//        calendar3.set(Calendar.DAY_OF_MONTH, 3);
//        milliTime = calendar3.getTimeInMillis();
//        Event ev3 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
//        binding.compactcalendarView.addEvent(ev3);
//        Calendar calendar3 = Calendar.getInstance();
//        calendar3.set(Calendar.YEAR, 2023);
//        calendar3.set(Calendar.MONTH, 2);
//        calendar3.set(Calendar.DAY_OF_MONTH, 3);
//        milliTime = calendar3.getTimeInMillis();
//        Event ev3 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
//        binding.compactcalendarView.addEvent(ev3);
//        Calendar calendar3 = Calendar.getInstance();
//        calendar3.set(Calendar.YEAR, 2023);
//        calendar3.set(Calendar.MONTH, 2);
//        calendar3.set(Calendar.DAY_OF_MONTH, 3);
//        milliTime = calendar3.getTimeInMillis();
//        Event ev3 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
//        binding.compactcalendarView.addEvent(ev3);
//        Calendar calendar3 = Calendar.getInstance();
//        calendar3.set(Calendar.YEAR, 2023);
//        calendar3.set(Calendar.MONTH, 2);
//        calendar3.set(Calendar.DAY_OF_MONTH, 3);
//        milliTime = calendar3.getTimeInMillis();
//        Event ev3 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
//        binding.compactcalendarView.addEvent(ev3);
//        Calendar calendar3 = Calendar.getInstance();
//        calendar3.set(Calendar.YEAR, 2023);
//        calendar3.set(Calendar.MONTH, 2);
//        calendar3.set(Calendar.DAY_OF_MONTH, 3);
//        milliTime = calendar3.getTimeInMillis();
//        Event ev3 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
//        binding.compactcalendarView.addEvent(ev3);
//        Calendar calendar3 = Calendar.getInstance();
//        calendar3.set(Calendar.YEAR, 2023);
//        calendar3.set(Calendar.MONTH, 2);
//        calendar3.set(Calendar.DAY_OF_MONTH, 3);
//        milliTime = calendar3.getTimeInMillis();
//        Event ev3 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
//        binding.compactcalendarView.addEvent(ev3);
//        Calendar calendar3 = Calendar.getInstance();
//        calendar3.set(Calendar.YEAR, 2023);
//        calendar3.set(Calendar.MONTH, 2);
//        calendar3.set(Calendar.DAY_OF_MONTH, 3);
//        milliTime = calendar3.getTimeInMillis();
//        Event ev3 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
//        binding.compactcalendarView.addEvent(ev3);
//        Calendar calendar3 = Calendar.getInstance();
//        calendar3.set(Calendar.YEAR, 2023);
//        calendar3.set(Calendar.MONTH, 2);
//        calendar3.set(Calendar.DAY_OF_MONTH, 3);
//        milliTime = calendar3.getTimeInMillis();
//        Event ev3 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
//        binding.compactcalendarView.addEvent(ev3);
//        Calendar calendar3 = Calendar.getInstance();
//        calendar3.set(Calendar.YEAR, 2023);
//        calendar3.set(Calendar.MONTH, 2);
//        calendar3.set(Calendar.DAY_OF_MONTH, 3);
//        milliTime = calendar3.getTimeInMillis();
//        Event ev3 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
//        binding.compactcalendarView.addEvent(ev3);
//        Calendar calendar3 = Calendar.getInstance();
//        calendar3.set(Calendar.YEAR, 2023);
//        calendar3.set(Calendar.MONTH, 2);
//        calendar3.set(Calendar.DAY_OF_MONTH, 3);
//        milliTime = calendar3.getTimeInMillis();
//        Event ev3 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
//        binding.compactcalendarView.addEvent(ev3);


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