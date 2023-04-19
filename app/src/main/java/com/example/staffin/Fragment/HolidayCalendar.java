package com.example.staffin.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.staffin.CalendarSettingActivity;
import com.example.staffin.R;
import com.example.staffin.Response.CreatedHolidayResp;
import com.example.staffin.Response.NationalHolidayResp;
import com.example.staffin.databinding.FragmentHolidayCalendarBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HolidayCalendar extends Fragment {
    FragmentHolidayCalendarBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHolidayCalendarBinding.inflate(inflater, container, false);
//        if (isNetworkAvailable()) {
//            ProgressDialog dialog = new ProgressDialog(CalendarSettingActivity.this);
//            dialog.setMessage("please wait.......");
//
//
//            Call<CreatedHolidayResp> callGetAllCreatedHolidays = apiInterface.getCreatedHolidays();
//            dialog.show();
//            callGetAllCreatedHolidays.enqueue(new Callback<CreatedHolidayResp>() {
//                @Override
//                public void onResponse(Call<CreatedHolidayResp> call, Response<CreatedHolidayResp> response) {
//                    if (response.isSuccessful()) {
//
//                        Call<NationalHolidayResp> callGetNationalHolidays = apiInterface.getNationalHolidays("my", "2023", "20220033");
//                        callGetNationalHolidays.enqueue(new Callback<NationalHolidayResp>() {
//                            @Override
//                            public void onResponse(Call<NationalHolidayResp> call, Response<NationalHolidayResp> response) {
//                                if (response.isSuccessful()) {
//                                    dialog.dismiss();
//                                    binding.holidayRecyclerView.setVisibility(View.VISIBLE);
//                                    binding.notFoundLayout.setVisibility(View.INVISIBLE);
////                                    CreatedHolidayResp holidaysResp = response.body();
////                                    List<CreatedHolidayResp.Holiday> holidays = holidaysResp.getHolidayList();
////                                    binding.holidayRecyclerView.setLayoutManager(new LinearLayoutManager(CalendarSettingActivity.this));
////                                  binding.holidayRecyclerView.setAdapter(new HolidayAdapter(CalendarSettingActivity.this, holidays));
//
//
//                                } else {
//                                    dialog.dismiss();
//                                    Log.d("kfndkfjn", response.message());
//                                    binding.holidayRecyclerView.setVisibility(View.INVISIBLE);
//                                    binding.notFoundLayout.setVisibility(View.VISIBLE);
//                                    Toast.makeText(CalendarSettingActivity.this, "unable to get information", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//
//                            @Override
//                            public void onFailure(Call<NationalHolidayResp> call, Throwable t) {
//                                Log.d("kfndkfjn", t.getMessage());
//                                dialog.dismiss();
//                                binding.holidayRecyclerView.setVisibility(View.INVISIBLE);
//                                binding.notFoundLayout.setVisibility(View.VISIBLE);
//                                Toast.makeText(CalendarSettingActivity.this, "some failure orrured", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//
////
//
//                    } else {
//                        dialog.dismiss();
//                        Log.d("kfndkfjn", response.message());
//                        binding.holidayRecyclerView.setVisibility(View.INVISIBLE);
//                        binding.notFoundLayout.setVisibility(View.VISIBLE);
//                        Toast.makeText(CalendarSettingActivity.this, "unable to get information", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<CreatedHolidayResp> call, Throwable t) {
//                    Log.d("kfndkfjn", t.getMessage());
//                    dialog.dismiss();
//                    binding.holidayRecyclerView.setVisibility(View.INVISIBLE);
//                    binding.notFoundLayout.setVisibility(View.VISIBLE);
//                    Toast.makeText(CalendarSettingActivity.this, "some failure orrured", Toast.LENGTH_SHORT).show();
//                }
//            });
//
//
//        } else {
//            Toast.makeText(this, "Internet Not Available", Toast.LENGTH_SHORT).show();
//        }

        return binding.getRoot();
    }
}