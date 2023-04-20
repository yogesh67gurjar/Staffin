package com.example.staffin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.staffin.Adapter.HolidayAdapter;
import com.example.staffin.Fragment.HolidayCalendar;
import com.example.staffin.Interface.ApiInterface;
import com.example.staffin.Response.AllHolidays;
import com.example.staffin.Response.CreatedHolidayResp;
import com.example.staffin.Response.HolidayResponse;
import com.example.staffin.Response.NationalCreatedMix;
import com.example.staffin.Response.LoginResponse;
import com.example.staffin.Response.NationalHolidayResp;
import com.example.staffin.Retrofit.RetrofitServices;
import com.example.staffin.databinding.ActivityCalendarSettingBinding;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class CalendarSettingActivity extends AppCompatActivity {
    ActivityCalendarSettingBinding binding;
    static String from = "";
    ApiInterface apiInterface;
    private static final String TAG = "CalendarSettingActivity";
    ProgressDialog progressDialog;
    List<NationalCreatedMix> nationalCreatedMixList;
    List<NationalCreatedMix> nationalCreatedMixListMonthly;
    List<NationalCreatedMix> nationalCreatedMixListWithoutT;
    List<NationalCreatedMix> nationalCreatedMixListMonthlyWithoutT;

    List<NationalHolidayResp.Response.Holiday> nationalHolidaysYearly;
    List<CreatedHolidayResp.Holiday> createdHolidays;
    List<NationalHolidayResp.Response.Holiday> nationalHolidaysMonthly;
    String[] dateInParts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCalendarSettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        nationalCreatedMixList = new ArrayList<>();
        nationalCreatedMixListMonthly = new ArrayList<>();
        nationalCreatedMixListWithoutT = new ArrayList<>();
        nationalCreatedMixListMonthlyWithoutT = new ArrayList<>();

        nationalHolidaysYearly = new ArrayList<>();
        createdHolidays = new ArrayList<>();
        nationalHolidaysMonthly = new ArrayList<>();


        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        progressDialog = new ProgressDialog(CalendarSettingActivity.this);
        progressDialog.setMessage("please wait");
        progressDialog.setCancelable(false);
        clickListeners();

        if (isNetworkAvailable()) {
            monthSetByDefault();

            binding.rightScroll.setOnClickListener(v -> {
                binding.compactcalendarView.scrollRight();
            });
            binding.leftScroll.setOnClickListener(v -> {
                binding.compactcalendarView.scrollLeft();
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
//                apiCallNationalAndCreatedHoliday(year);
//                apiCallNationalAndCreatedHolidayMonthly(year, month);

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

                    apiCallNationalAndCreatedHoliday(year, month);
                }

            });
        } else {
            Toast.makeText(this, "Internet Not Available", Toast.LENGTH_SHORT).show();
        }

    }

    private void apiCallNationalAndCreatedHoliday(int year, int month) {
        if (isNetworkAvailable()) {
            progressDialog.show();

            Call<NationalHolidayResp> callGetNationalHoliday = apiInterface.getNationalHoliday("my", year);
            callGetNationalHoliday.enqueue(new Callback<>() {
                @Override
                public void onResponse(Call<NationalHolidayResp> call, Response<NationalHolidayResp> response) {
                    if (response.isSuccessful()) {
                        nationalHolidaysYearly = response.body().getResponse().getHolidays();

                        Call<CreatedHolidayResp> callGetCreatedHolidays = apiInterface.getCreatedHolidays();
                        callGetCreatedHolidays.enqueue(new Callback<>() {
                            @Override
                            public void onResponse(Call<CreatedHolidayResp> call, Response<CreatedHolidayResp> response) {
                                if (response.isSuccessful()) {
                                    createdHolidays = response.body().getHolidayList();

                                    Call<NationalHolidayResp> callGetNationalHolidayMonthly = apiInterface.getNationalHolidayMonthly("my", month, year);
                                    callGetNationalHolidayMonthly.enqueue(new Callback<NationalHolidayResp>() {
                                        @Override
                                        public void onResponse(Call<NationalHolidayResp> call, Response<NationalHolidayResp> response) {
                                            if (response.isSuccessful()) {
                                                nationalHolidaysMonthly = response.body().getResponse().getHolidays();
                                                nationalCreatedMixList.clear();
                                                nationalCreatedMixListMonthly.clear();
                                                nationalCreatedMixListWithoutT.clear();
                                                nationalCreatedMixListMonthlyWithoutT.clear();
                                                for (NationalHolidayResp.Response.Holiday N : nationalHolidaysYearly) {
                                                    nationalCreatedMixList.add(new NationalCreatedMix(N.getName(), N.getDate().getIso(), N.getDescription(), "national"));
                                                }
                                                for (CreatedHolidayResp.Holiday C : createdHolidays) {
                                                    nationalCreatedMixList.add(new NationalCreatedMix(C.getOccassion(), C.getDate(), C.getHolidayDescription(), "created"));
                                                }

                                                for (CreatedHolidayResp.Holiday cc : createdHolidays) {
                                                    nationalCreatedMixListMonthly.add(new NationalCreatedMix(cc.getOccassion(), cc.getDate(), cc.getHolidayDescription(), "created"));
                                                }

                                                for (NationalHolidayResp.Response.Holiday hh : nationalHolidaysMonthly) {
                                                    nationalCreatedMixListMonthly.add(new NationalCreatedMix(hh.getName(), hh.getDate().getIso(), hh.getDescription(), "national"));
                                                }

                                                for (NationalCreatedMix m : nationalCreatedMixList) {
                                                    if (m.getDate().contains("T")) {
                                                        nationalCreatedMixListWithoutT.add(new NationalCreatedMix(m.getName(), m.getDate().split("T")[0], m.getDesc(), m.getType()));
                                                    } else {
                                                        nationalCreatedMixListWithoutT.add(m);
                                                    }
                                                }
                                                for (NationalCreatedMix m : nationalCreatedMixListMonthly) {
                                                    if (m.getDate().contains("T")) {
                                                        nationalCreatedMixListMonthlyWithoutT.add(new NationalCreatedMix(m.getName(), m.getDate().split("T")[0], m.getDesc(), m.getType()));
                                                    } else {
                                                        nationalCreatedMixListMonthlyWithoutT.add(m);
                                                    }
                                                }
                                                for (NationalCreatedMix mm : nationalCreatedMixListMonthlyWithoutT) {
                                                    Log.d(TAG, "nationalCreatedMixMonthly" + mm.getName() + "----" + mm.getDate() + "----" + mm.getType());
                                                }
                                                for (NationalCreatedMix m : nationalCreatedMixListWithoutT) {
                                                    Log.d(TAG, "nationalCreatedMix: " + String.valueOf(m.getName()) + "----" + m.getDate() + "----" + m.getType());
                                                }
                                                progressDialog.dismiss();
                                                initializeCalendar(nationalCreatedMixListWithoutT);
                                                ArrayList<NationalCreatedMix> fragmentData = new ArrayList<>(nationalCreatedMixListMonthlyWithoutT.size());
                                                fragmentData.addAll(nationalCreatedMixListMonthlyWithoutT);
                                                Bundle bundle = new Bundle();
                                                bundle.putSerializable("recyclerData", fragmentData);
                                                HolidayCalendar holidayCalendarFragment = new HolidayCalendar();
                                                holidayCalendarFragment.setArguments(bundle);

                                                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                                                ft.replace(R.id.containerRecyclerView, holidayCalendarFragment).commit();

                                            } else {
                                                progressDialog.dismiss();
                                                Toast.makeText(CalendarSettingActivity.this, "Error Occured", Toast.LENGTH_SHORT).show();
                                                Log.d(TAG, "onResponseElse11: " + response.message());
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<NationalHolidayResp> call, Throwable t) {
                                            progressDialog.dismiss();

                                            Log.d(TAG, "onFaidfsdfsdfsdflure: " + t.getMessage() + t.getCause().toString());
                                            Toast.makeText(CalendarSettingActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(CalendarSettingActivity.this, "Error Occured", Toast.LENGTH_SHORT).show();
                                    Log.d(TAG, "onResponseElsdfsdfsdfse11: " + response.message());
                                }

                            }

                            @Override
                            public void onFailure(Call<CreatedHolidayResp> call, Throwable t) {
                                progressDialog.dismiss();
                                Log.d(TAG, "onFaifsdfsfsdfsdfaflure11: " + t.getMessage());
                                Toast.makeText(CalendarSettingActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(CalendarSettingActivity.this, "Error Occured", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onResponseElse11: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<NationalHolidayResp> call, Throwable t) {
                    progressDialog.dismiss();

                    Log.d(TAG, "onFaidfsdfsdfsdflure: " + t.getMessage());
                    Toast.makeText(CalendarSettingActivity.this, "Failure", Toast.LENGTH_SHORT).show();
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

        apiCallNationalAndCreatedHoliday(year, month);


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

        binding.loginBtn.setOnClickListener(v -> {
            startActivity(new Intent(CalendarSettingActivity.this, HolidayActivity.class));
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
    }

    private void initializeCalendar(List<NationalCreatedMix> allHolidays) {
        progressDialog.show();
        CompactCalendarView compactCalendarView = findViewById(R.id.compactcalendar_view);
        compactCalendarView.setLocale(TimeZone.getDefault(), Locale.ENGLISH);
        compactCalendarView.setUseThreeLetterAbbreviation(true);
        int count = 0;
        long milliTime;

        for (NationalCreatedMix singleUnit : allHolidays) {
            Log.d("dsfsdfsdf", singleUnit.getDate());
            Event e;
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
            if (count % 3 == 0) {
                e = new Event(getResources().getColor(R.color.mainColor), milliTime, singleUnit.getDesc());
            } else if (count % 5 == 0) {
                e = new Event(getResources().getColor(R.color.yellow), milliTime, singleUnit.getDesc());
            } else if (count % 2 == 0) {
                e = new Event(getResources().getColor(R.color.pink), milliTime, singleUnit.getDesc());
            } else {
                e = new Event(getResources().getColor(R.color.green), milliTime, singleUnit.getDesc());
            }
            count++;
            binding.compactcalendarView.addEvent(e);
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

        progressDialog.dismiss();
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