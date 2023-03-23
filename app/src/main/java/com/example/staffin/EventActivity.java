package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.media.metrics.Event;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.example.staffin.Adapter.EventAdapter;
import com.example.staffin.Adapter.MonthAdapter;
import com.example.staffin.Response.Attendance;
import com.example.staffin.Response.EventsResponse;
import com.example.staffin.Response.MembersOfEvent;
import com.example.staffin.Response.MyMonth;
import com.example.staffin.databinding.ActivityEventBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EventActivity extends AppCompatActivity {

    ActivityEventBinding binding;

    List<MyMonth> monthsList;
    MonthAdapter adapter;
    List<MembersOfEvent> membersOnly;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEventBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        monthsList = new ArrayList<>();
        membersOnly = new ArrayList<>();
        membersOnly.add(new MembersOfEvent(1, "fjksdnf"));
        membersOnly.add(new MembersOfEvent(2, "fjksdnf"));
        monthsList.add(new MyMonth(1, "yogesh birthday", "bfjisnsdjkf", "shajapur", "at hanuman mandir shajapur", "08-08-1999", membersOnly));
        monthsList.add(new MyMonth(2, "shubham birthday", "bfjisnsdjkf", "shajapur", "at hanuman mandir shajapur", "08-08-1999", membersOnly));
        monthsList.add(new MyMonth(3, "sunil birthday", "bfjisnsdjkf", "shajapur", "at hanuman mandir shajapur", "08-08-1999", membersOnly));

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


        binding.EventMonthRv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MonthAdapter(monthsList, EventActivity.this);
        binding.EventMonthRv.setAdapter(adapter);

        binding.btnCalendar.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), CalendarSettingActivity.class));
        });

        binding.btnBack.setOnClickListener(v -> {
            finish();
        });

    }

    void filter(String text) {
        List<MyMonth> myMonths = new ArrayList<>();

        for (MyMonth a : monthsList) {
            if (a.getTitle().toLowerCase().contains(text.toLowerCase())) {
                myMonths.add(a);
            }
        }
        //update recyclerview
        adapter.filterList(myMonths);
    }
}