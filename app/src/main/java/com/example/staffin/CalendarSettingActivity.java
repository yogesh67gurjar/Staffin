package com.example.staffin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.staffin.Adapter.HolidayAdapter;
import com.example.staffin.Interface.ApiInterface;
import com.example.staffin.Response.AllHolidays;
import com.example.staffin.Response.HolidayResponse;
import com.example.staffin.Retrofit.RetrofitServices;
import com.example.staffin.databinding.ActivityCalendarSettingBinding;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalendarSettingActivity extends AppCompatActivity {
    ActivityCalendarSettingBinding binding;
    static String from = "";
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCalendarSettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        clickListeners();
        monthSetByDefault();

        if (isNetworkAvailable()) {
            ProgressDialog dialog = new ProgressDialog(CalendarSettingActivity.this);
            dialog.setMessage("please wait.......");
            dialog.show();

            Call<HolidayResponse> callGetAllHolidays = apiInterface.getAllHolidays();
            callGetAllHolidays.enqueue(new Callback<HolidayResponse>() {
                @Override
                public void onResponse(Call<HolidayResponse> call, Response<HolidayResponse> response) {
                    if (response.isSuccessful()) {
                        HolidayResponse holidaysResp = response.body();
                        List<AllHolidays> holidays = holidaysResp.getHoliday_list();

                        binding.holidayRecyclerView.setLayoutManager(new LinearLayoutManager(CalendarSettingActivity.this));
                        binding.holidayRecyclerView.setAdapter(new HolidayAdapter(CalendarSettingActivity.this, holidays));
                        initializeCalendar(holidays);
                        dialog.dismiss();
                    } else {
                        dialog.dismiss();
                        Log.d("kfndkfjn", response.message());
                        Toast.makeText(CalendarSettingActivity.this, "unable to get information", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<HolidayResponse> call, Throwable t) {
                    Log.d("kfndkfjn", t.getMessage());
                    dialog.dismiss();
                    Toast.makeText(CalendarSettingActivity.this, "some failure orrured", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Internet Not Available", Toast.LENGTH_SHORT).show();
        }
    }

    private void monthSetByDefault() {
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
    }

    private void clickListeners() {
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

    private void initializeCalendar(List<AllHolidays> allHolidays) {
        CompactCalendarView compactCalendarView = findViewById(R.id.compactcalendar_view);
        compactCalendarView.setLocale(TimeZone.getDefault(), Locale.ENGLISH);
        compactCalendarView.setUseThreeLetterAbbreviation(true);

        long milliTime;

        for(AllHolidays singleUnit:allHolidays)
        {
            String dateInParts[]=singleUnit.getDate().split("-");
            Calendar calendar=Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.parseInt(dateInParts[0]));
            calendar.set(Calendar.MONTH, Integer.parseInt(dateInParts[1])-1);
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateInParts[2]));
            milliTime = calendar.getTimeInMillis();
            Event ev1 = new Event(getResources().getColor(R.color.txtRed), milliTime, singleUnit.getHoliday_description());
            binding.compactcalendarView.addEvent(ev1);
        }




//        Calendar calendar1 = Calendar.getInstance();
//        calendar1.set(Calendar.YEAR, 2023);
//        calendar1.set(Calendar.MONTH, 2);
//        calendar1.set(Calendar.DAY_OF_MONTH, 1);
//        milliTime = calendar1.getTimeInMillis();
//        Event ev1 = new Event(getResources().getColor(R.color.txtRed), milliTime, "Teachers' Professional Day");
//        binding.compactcalendarView.addEvent(ev1);
//
//        Calendar calendar2 = Calendar.getInstance();
//        calendar2.set(Calendar.YEAR, 2023);
//        calendar2.set(Calendar.MONTH, 2);
//        calendar2.set(Calendar.DAY_OF_MONTH, 2);
//        milliTime = calendar2.getTimeInMillis();
//        Event ev2 = new Event(getResources().getColor(R.color.yellow), milliTime, "Teachers' Professional Day");
//        binding.compactcalendarView.addEvent(ev2);
//
//        Calendar calendar3 = Calendar.getInstance();
//        calendar3.set(Calendar.YEAR, 2023);
//        calendar3.set(Calendar.MONTH, 2);
//        calendar3.set(Calendar.DAY_OF_MONTH, 3);
//        milliTime = calendar3.getTimeInMillis();
//        Event ev3 = new Event(getResources().getColor(R.color.green), milliTime, "Teachers' Professional Day");
//        binding.compactcalendarView.addEvent(ev3);
//
//        Calendar calendar4 = Calendar.getInstance();
//        calendar4.set(Calendar.YEAR, 2023);
//        calendar4.set(Calendar.MONTH, 2);
//        calendar4.set(Calendar.DAY_OF_MONTH, 4);
//        milliTime = calendar4.getTimeInMillis();
//        Event ev4 = new Event(getResources().getColor(R.color.bluetext), milliTime, "Teachers' Professional Day");
//        binding.compactcalendarView.addEvent(ev4);
//
//        Calendar calendar5 = Calendar.getInstance();
//        calendar5.set(Calendar.YEAR, 2023);
//        calendar5.set(Calendar.MONTH, 2);
//        calendar5.set(Calendar.DAY_OF_MONTH, 5);
//        milliTime = calendar5.getTimeInMillis();
//        Event ev5 = new Event(getResources().getColor(R.color.txtGray), milliTime, "Teachers' Professional Day");
//        binding.compactcalendarView.addEvent(ev5);
//
//        Calendar calendar6 = Calendar.getInstance();
//        calendar6.set(Calendar.YEAR, 2023);
//        calendar6.set(Calendar.MONTH, 2);
//        calendar6.set(Calendar.DAY_OF_MONTH, 6);
//        milliTime = calendar6.getTimeInMillis();
//        Event ev6 = new Event(getResources().getColor(R.color.txtPurple), milliTime, "Teachers' Professional Day");
//        binding.compactcalendarView.addEvent(ev6);
//
//
//        Calendar calendar7 = Calendar.getInstance();
//        calendar7.set(Calendar.YEAR, 2023);
//        calendar7.set(Calendar.MONTH, 2);
//        calendar7.set(Calendar.DAY_OF_MONTH, 7);
//        milliTime = calendar7.getTimeInMillis();
//        Event ev7 = new Event(getResources().getColor(R.color.black), milliTime, "Teachers' Professional Day");
//        binding.compactcalendarView.addEvent(ev7);
//
//
//        Calendar calendar10 = Calendar.getInstance();
//        calendar10.set(Calendar.YEAR, 2023);
//        calendar10.set(Calendar.MONTH, 2);
//        calendar10.set(Calendar.DAY_OF_MONTH, 29);
//        milliTime = calendar10.getTimeInMillis();
//        Event ev10 = new Event(getResources().getColor(R.color.purple_200), milliTime, "Ram Navami");
//        binding.compactcalendarView.addEvent(ev10);


    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}


//  if (getIntent().hasExtra("from")) {
//          from = getIntent().getStringExtra("from");
//          }