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

import com.example.staffin.Adapter.MonthAdapter;
import com.example.staffin.Interface.ApiInterface;
import com.example.staffin.Response.EventResponse;
import com.example.staffin.Response.EventsByYearResponse;
import com.example.staffin.Retrofit.RetrofitServices;
import com.example.staffin.databinding.ActivityEventBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventActivity extends AppCompatActivity {

    ActivityEventBinding binding;

    //    List<MyMonth> monthsList;
    MonthAdapter adapter;
    //    List<MembersOfEvent> membersOnly;
    EventsByYearResponse.EventDetails eventDetails;
    ApiInterface apiInterface;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEventBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        clickListeners();
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);

        if (isNetworkAvailable()) {
            progress.show();
            Call<EventsByYearResponse> callGetEventsByYear = apiInterface.getEventsByYear(2023);
            callGetEventsByYear.enqueue(new Callback<EventsByYearResponse>() {
                @Override
                public void onResponse(Call<EventsByYearResponse> call, Response<EventsByYearResponse> response) {
                    if (response.isSuccessful()) {
                        progress.dismiss();
                        eventDetails = response.body().getEventDetails();
                        adapter = new MonthAdapter(EventActivity.this, eventDetails);
                        binding.EventMonthRv.setAdapter(adapter);

                    } else {
                        Toast.makeText(EventActivity.this, "Find Some Error", Toast.LENGTH_SHORT).show();
                        progress.dismiss();
                        Log.d("jnknfd", response.message());
                    }
                }

                @Override
                public void onFailure(Call<EventsByYearResponse> call, Throwable t) {
                    Toast.makeText(EventActivity.this, "Failure,Try Again", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                    Log.d("kdnf", t.getMessage());
                }
            });
        } else {
            Toast.makeText(this, "Internet Not Available", Toast.LENGTH_SHORT).show();

        }



//        monthsList = new ArrayList<>();
//        membersOnly = new ArrayList<>();
//        membersOnly.add(new MembersOfEvent(1, "fjksdnf"));
//        membersOnly.add(new MembersOfEvent(2, "fjksdnf"));
//        monthsList.add(new MyMonth(1, "yogesh birthday", "bfjisnsdjkf", "shajapur", "at hanuman mandir shajapur", "08-08-1999", membersOnly));
//        monthsList.add(new MyMonth(2, "shubham birthday", "bfjisnsdjkf", "shajapur", "at hanuman mandir shajapur", "08-08-1999", membersOnly));
//        monthsList.add(new MyMonth(3, "sunil birthday", "bfjisnsdjkf", "shajapur", "at hanuman mandir shajapur", "08-08-1999", membersOnly));

    }

    private void clickListeners() {


        progress = new ProgressDialog(EventActivity.this);
        progress.setMessage("Please Wait....");

        binding.EventMonthRv.setLayoutManager(new LinearLayoutManager(this));

        binding.btnAddEvent.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), CreateEventActivity.class));
        });


//        binding.searchBar.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                filter(s.toString());
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//            }
//        });


//        adapter = new MonthAdapter(monthsList, EventActivity.this);
//        binding.EventMonthRv.setAdapter(adapter);

        binding.btnCalendar.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), CalendarSettingActivity.class));
        });

        binding.btnBack.setOnClickListener(v -> {
            finish();
        });
    }

//    void filter(String text) {
//        List<AllEvents> myMonths = new ArrayList<>();
//
//        for (AllEvents a : allEventsList) {
//            if (a.getTitleName().toLowerCase().contains(text.toLowerCase())) {
//                myMonths.add(a);
//            }
//        }
//        //update recyclerview
//        adapter.filterList(myMonths);
//    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}