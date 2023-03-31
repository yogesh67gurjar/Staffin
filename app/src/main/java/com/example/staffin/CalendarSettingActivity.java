package com.example.staffin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.staffin.Adapter.HolidayAdapter;
import com.example.staffin.databinding.ActivityCalendarSettingBinding;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

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

        binding.rightScroll.setOnClickListener(v -> {
            binding.compactcalendarView.scrollRight();
        });
        binding.leftScroll.setOnClickListener(v -> {
            binding.compactcalendarView.scrollLeft();
        });
        binding.loginBtn.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), HolidayActivity.class));
        });

        binding.btnBack.setOnClickListener(v -> {

            if (from.equalsIgnoreCase("mainactivity")) {
//                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                from = "";
                finish();
            } else {
                finish();
            }
        });


        binding.compactcalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
//                List<Event> events = binding.compactcalendarView.getEvents(dateClicked);
//
//                Bundle bundle = new Bundle();
//
//                Calendar cal = Calendar.getInstance();
//                cal.setTime(dateClicked);
//                int year = cal.get(Calendar.YEAR);
//                int month = cal.get(Calendar.MONTH);
//                int day = cal.get(Calendar.DATE);
//                month += 1;
//                Log.d("DATE", String.valueOf(day) + month + year);
//                bundle.putString("Date", day + "-" + month + "-" + year);
//                PresentBottomSheetFragment presentBottomSheetFragment = new PresentBottomSheetFragment();
//                presentBottomSheetFragment.setArguments(bundle);
//                presentBottomSheetFragment.show(getSupportFragmentManager(), presentBottomSheetFragment.getTag());
//
//                Log.d("CLICKED", "Day was clicked: " + dateClicked + " with events " + events);
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
        binding.holidayRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.holidayRecyclerView.setAdapter(new HolidayAdapter(CalendarSettingActivity.this));

        initializeCalendar();
    }

    private void initializeCalendar() {

        CompactCalendarView compactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendarView.setLocale(TimeZone.getDefault(), Locale.ENGLISH);
        compactCalendarView.setUseThreeLetterAbbreviation(true);

        Calendar[] calendars = new Calendar[7];
        long milliTime;
        for (int i = 0; i < 7; i++) {
            calendars[i] = Calendar.getInstance();
            calendars[i].set(Calendar.YEAR, 2023);
            calendars[i].set(Calendar.MONTH, 2);
            calendars[i].set(Calendar.YEAR, i + 1);
            milliTime = calendars[i].getTimeInMillis();
            Event event = new Event(getResources().getColor(R.color.calGreen), milliTime, "huifuhsdfusdn");
            binding.compactcalendarView.addEvent(event);
        }

//        Calendar calendar1 = Calendar.getInstance();
//        calendar1.set(Calendar.YEAR, 2023);
//        calendar1.set(Calendar.MONTH, 2);
//        calendar1.set(Calendar.DAY_OF_MONTH, 2);
//        milliTime = calendar1.getTimeInMillis();
//        Event ev1 = new Event(getResources().getColor(R.color.calGreen), milliTime, "Teachers' Professional Day");
//        binding.compactcalendarView.addEvent(ev1);


    }

}