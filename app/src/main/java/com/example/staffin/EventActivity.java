package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.media.metrics.Event;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import com.example.staffin.Adapter.EventAdapter;
import com.example.staffin.Adapter.MonthAdapter;
import com.example.staffin.Interface.ApiInterface;
import com.example.staffin.Response.AllEvents;
import com.example.staffin.Response.Attendance;
import com.example.staffin.Response.EventResponse;
import com.example.staffin.Response.EventsResponse;
import com.example.staffin.Response.MembersOfEvent;
import com.example.staffin.Response.MyMonth;
import com.example.staffin.Retrofit.RetrofitServices;
import com.example.staffin.databinding.ActivityEventBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventActivity extends AppCompatActivity {

    ActivityEventBinding binding;

//    List<MyMonth> monthsList;
    MonthAdapter adapter;
    //    List<MembersOfEvent> membersOnly;
    List<AllEvents> allEventsList;
    ApiInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEventBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.EventMonthRv.setLayoutManager(new LinearLayoutManager(this));

        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        Call<EventResponse> call = apiInterface.getAllEvents();
        call.enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                if (response.isSuccessful()) {
                    allEventsList = response.body().getAllEvents();
                    adapter = new MonthAdapter(EventActivity.this, allEventsList);
                    binding.EventMonthRv.setAdapter(adapter);

                } else {
                    Toast.makeText(EventActivity.this, "Find Some Error", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                Toast.makeText(EventActivity.this, "Failure,Try Again", Toast.LENGTH_SHORT).show();

            }
        });
//
//        monthsList = new ArrayList<>();
//        membersOnly = new ArrayList<>();
//        membersOnly.add(new MembersOfEvent(1, "fjksdnf"));
//        membersOnly.add(new MembersOfEvent(2, "fjksdnf"));
//        monthsList.add(new MyMonth(1, "yogesh birthday", "bfjisnsdjkf", "shajapur", "at hanuman mandir shajapur", "08-08-1999", membersOnly));
//        monthsList.add(new MyMonth(2, "shubham birthday", "bfjisnsdjkf", "shajapur", "at hanuman mandir shajapur", "08-08-1999", membersOnly));
//        monthsList.add(new MyMonth(3, "sunil birthday", "bfjisnsdjkf", "shajapur", "at hanuman mandir shajapur", "08-08-1999", membersOnly));

        binding.btnAddEvent.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), CreateEventActivity.class));
        });


        binding.searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


//        adapter = new MonthAdapter(monthsList, EventActivity.this);
//        binding.EventMonthRv.setAdapter(adapter);

        binding.btnCalendar.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), CalendarSettingActivity.class));
        });

        binding.btnBack.setOnClickListener(v -> {
            finish();
        });

    }

    void filter(String text) {
        List<AllEvents> myMonths = new ArrayList<>();

        for (AllEvents a : allEventsList) {
            if (a.getTitleName().toLowerCase().contains(text.toLowerCase())) {
                myMonths.add(a);
            }
        }
        //update recyclerview
        adapter.filterList(myMonths);
    }
}