package com.example.staffin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.staffin.Adapter.MonthAdapter;
import com.example.staffin.Interface.ApiInterface;
import com.example.staffin.Response.EmployeeResult;
import com.example.staffin.Response.EventResponse;
import com.example.staffin.Response.EventsByYearResponse;
import com.example.staffin.Response.EventsMix;
import com.example.staffin.Retrofit.RetrofitServices;
import com.example.staffin.databinding.ActivityEventBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventActivity extends AppCompatActivity {

    ActivityEventBinding binding;
    MonthAdapter adapter;
    ApiInterface apiInterface;
    ProgressDialog progress;
    List<EventsMix> eventsMixList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEventBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        progress = new ProgressDialog(EventActivity.this);
        progress.setCancelable(false);
        progress.setMessage("Please Wait....");
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        binding.EventMonthRv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        eventsMixList = new ArrayList<>();

        if (isNetworkAvailable()) {

            binding.searchbar.addTextChangedListener(new TextWatcher() {
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

//            progress.show();
            binding.lottie.setVisibility(View.VISIBLE);
            Call<EventsByYearResponse> callGetEventsByYear = apiInterface.getEventsByYear(2023);
            callGetEventsByYear.enqueue(new Callback<EventsByYearResponse>() {
                @Override
                public void onResponse(Call<EventsByYearResponse> call, Response<EventsByYearResponse> response) {
                    if (response.isSuccessful()) {
//                        progress.dismiss();
                        binding.lottie.setVisibility(View.GONE);
                        if (response.body().getEventDetails().getJanuary().size() > 0) {
                            for (EventsByYearResponse.EventDetails.January x : response.body().getEventDetails().getJanuary()) {
                                eventsMixList.add(new EventsMix(x.getId(), x.getTitleName(), x.getImage(), x.getImg1(), x.getImg2(), x.getImg3(), x.getLocation(), x.getDescription(), x.getDate(), x.getAddMember(), 1,x.getAdd_member_images(),x.getAdd_member_count(),x.getAdd_intruted_member()));
                            }
                        }
                        if (response.body().getEventDetails().getFebruary().size() > 0) {
                            for (EventsByYearResponse.EventDetails.February x : response.body().getEventDetails().getFebruary()) {
                                eventsMixList.add(new EventsMix(x.getId(), x.getTitleName(), x.getImage(), x.getImg1(), x.getImg2(), x.getImg3(), x.getLocation(), x.getDescription(), x.getDate(), x.getAddMember(), 2,x.getAdd_member_images(),x.getAdd_member_count(),x.getAdd_intruted_member()));
                            }
                        }
                        if (response.body().getEventDetails().getMarch().size() > 0) {
                            for (EventsByYearResponse.EventDetails.March x : response.body().getEventDetails().getMarch()) {
                                eventsMixList.add(new EventsMix(x.getId(), x.getTitleName(), x.getImage(), x.getImg1(), x.getImg2(), x.getImg3(), x.getLocation(), x.getDescription(), x.getDate(), x.getAddMember(), 3,x.getAdd_member_images(),x.getAdd_member_count(),x.getAdd_intruted_member()));
                            }
                        }
                        if (response.body().getEventDetails().getApril().size() > 0) {
                            for (EventsByYearResponse.EventDetails.April f : response.body().getEventDetails().getApril()) {
                                eventsMixList.add(new EventsMix(f.getId(), f.getTitleName(), f.getImage(), f.getImg1(), f.getImg2(), f.getImg3(), f.getLocation(), f.getDescription(), f.getDate(), f.getAddMember(), 4,f.getAdd_member_images(),f.getAdd_member_count(),f.getAdd_intruted_member()));
                            }
                        }
                        if (response.body().getEventDetails().getMay().size() > 0) {
                            for (EventsByYearResponse.EventDetails.May j : response.body().getEventDetails().getMay()) {
                                eventsMixList.add(new EventsMix(j.getId(), j.getTitleName(), j.getImage(), j.getImg1(), j.getImg2(), j.getImg3(), j.getLocation(), j.getDescription(), j.getDate(), j.getAddMember(), 5,j.getAdd_member_images(),j.getAdd_member_count(),j.getAdd_intruted_member()));
                            }
                        }
                        if (response.body().getEventDetails().getJune().size() > 0) {
                            for (EventsByYearResponse.EventDetails.June f : response.body().getEventDetails().getJune()) {
                                eventsMixList.add(new EventsMix(f.getId(), f.getTitleName(), f.getImage(), f.getImg1(), f.getImg2(), f.getImg3(), f.getLocation(), f.getDescription(), f.getDate(), f.getAddMember(), 6,f.getAdd_member_images(),f.getAdd_member_count(),f.getAdd_intruted_member()));
                            }
                        }
                        if (response.body().getEventDetails().getJuly().size() > 0) {
                            for (EventsByYearResponse.EventDetails.July j : response.body().getEventDetails().getJuly()) {
                                eventsMixList.add(new EventsMix(j.getId(), j.getTitleName(), j.getImage(), j.getImg1(), j.getImg2(), j.getImg3(), j.getLocation(), j.getDescription(), j.getDate(), j.getAddMember(), 7,j.getAdd_member_images(),j.getAdd_member_count(),j.getAdd_intruted_member()));
                            }
                        }
                        if (response.body().getEventDetails().getAugust().size() > 0) {
                            for (EventsByYearResponse.EventDetails.August f : response.body().getEventDetails().getAugust()) {
                                eventsMixList.add(new EventsMix(f.getId(), f.getTitleName(), f.getImage(), f.getImg1(), f.getImg2(), f.getImg3(), f.getLocation(), f.getDescription(), f.getDate(), f.getAddMember(), 8,f.getAdd_member_images(),f.getAdd_member_count(),f.getAdd_intruted_member()));
                            }
                        }
                        if (response.body().getEventDetails().getSeptember().size() > 0) {
                            for (EventsByYearResponse.EventDetails.September j : response.body().getEventDetails().getSeptember()) {
                                eventsMixList.add(new EventsMix(j.getId(), j.getTitleName(), j.getImage(), j.getImg1(), j.getImg2(), j.getImg3(), j.getLocation(), j.getDescription(), j.getDate(), j.getAddMember(), 9,j.getAdd_member_images(),j.getAdd_member_count(),j.getAdd_intruted_member()));
                            }
                        }
                        if (response.body().getEventDetails().getOctober().size() > 0) {
                            for (EventsByYearResponse.EventDetails.October f : response.body().getEventDetails().getOctober()) {
                                eventsMixList.add(new EventsMix(f.getId(), f.getTitleName(), f.getImage(), f.getImg1(), f.getImg2(), f.getImg3(), f.getLocation(), f.getDescription(), f.getDate(), f.getAddMember(), 10,f.getAdd_member_images(),f.getAdd_member_count(),f.getAdd_intruted_member()));
                            }
                        }
                        if (response.body().getEventDetails().getNovember().size() > 0) {
                            for (EventsByYearResponse.EventDetails.November j : response.body().getEventDetails().getNovember()) {
                                eventsMixList.add(new EventsMix(j.getId(), j.getTitleName(), j.getImage(), j.getImg1(), j.getImg2(), j.getImg3(), j.getLocation(), j.getDescription(), j.getDate(), j.getAddMember(), 11,j.getAdd_member_images(),j.getAdd_member_count(),j.getAdd_intruted_member()));
                            }
                        }
                        if (response.body().getEventDetails().getDecember().size() > 0) {
                            for (EventsByYearResponse.EventDetails.December f : response.body().getEventDetails().getDecember()) {
                                eventsMixList.add(new EventsMix(f.getId(), f.getTitleName(), f.getImage(), f.getImg1(), f.getImg2(), f.getImg3(), f.getLocation(), f.getDescription(), f.getDate(), f.getAddMember(), 12,f.getAdd_member_images(),f.getAdd_member_count(),f.getAdd_intruted_member()));
                            }
                        }
                        for (EventsMix ex : eventsMixList) {
                            Log.d("EVENTMIX " + ex.getMonth(), ex.getDate());
                        }
                        if (eventsMixList.size() > 0) {
                            adapter = new MonthAdapter(getApplicationContext(), eventsMixList);
                            binding.EventMonthRv.setAdapter(adapter);
                        }

//                        eventDetails = response.body().getEventDetails();
//                        adapter = new MonthAdapter(getActivity(), eventDetails);
//                        binding.EventMonthRv.setAdapter(adapter);

                    } else {
                        Toast.makeText(getApplicationContext(), "Find Some Error", Toast.LENGTH_SHORT).show();
//                        progress.dismiss();
                        binding.lottie.setVisibility(View.GONE);
                        Log.d("jnknfd", response.message());
                    }
                }

                @Override
                public void onFailure(Call<EventsByYearResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Failure,Try Again", Toast.LENGTH_SHORT).show();
//                    progress.dismiss();
                    binding.lottie.setVisibility(View.GONE);
                    Log.d("kdnf", t.getMessage());
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Internet Not Available", Toast.LENGTH_SHORT).show();
        }
        binding.btnBack.setOnClickListener(v -> {
            finish();
        });
        binding.btnAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CreateEventActivity.class));
            }
        });

//        monthsList = new ArrayList<>();
//        membersOnly = new ArrayList<>();
//        membersOnly.add(new MembersOfEvent(1, "fjksdnf"));
//        membersOnly.add(new MembersOfEvent(2, "fjksdnf"));
//        monthsList.add(new MyMonth(1, "yogesh birthday", "bfjisnsdjkf", "shajapur", "at hanuman mandir shajapur", "08-08-1999", membersOnly));
//        monthsList.add(new MyMonth(2, "shubham birthday", "bfjisnsdjkf", "shajapur", "at hanuman mandir shajapur", "08-08-1999", membersOnly));
//        monthsList.add(new MyMonth(3, "sunil birthday", "bfjisnsdjkf", "shajapur", "at hanuman mandir shajapur", "08-08-1999", membersOnly));


    }

    private void clickListeners() {

        binding.EventMonthRv.setLayoutManager(new LinearLayoutManager(this));

        binding.btnAddEvent.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), CreateEventActivity.class));
        });


        adapter = new MonthAdapter(EventActivity.this, eventsMixList);
        binding.EventMonthRv.setAdapter(adapter);

        binding.btnCalendar.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), CalendarSettingActivity.class));
        });


    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    void filter(String text) {
        List<EventsMix> filteredList = new ArrayList();
        for (EventsMix d : eventsMixList) {
            if (d.getTitleName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(d);
            }
        }
        //update recyclerview
        adapter.filterList(filteredList);
    }


}