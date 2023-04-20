package com.example.staffin.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.staffin.Adapter.HolidayAdapter;
import com.example.staffin.CalendarSettingActivity;
import com.example.staffin.R;
import com.example.staffin.Response.CreatedHolidayResp;
import com.example.staffin.Response.NationalCreatedMix;
import com.example.staffin.Response.NationalHolidayResp;
import com.example.staffin.databinding.FragmentHolidayCalendarBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HolidayCalendar extends Fragment {
    FragmentHolidayCalendarBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHolidayCalendarBinding.inflate(inflater, container, false);

        List<NationalCreatedMix> mix = (List<NationalCreatedMix>) this.getArguments().getSerializable("recyclerData");

        if (mix.size() == 0) {
            binding.holidayRecyclerView.setVisibility(View.GONE);
            binding.notFoundLayout.setVisibility(View.VISIBLE);
        } else {
            binding.holidayRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            binding.holidayRecyclerView.setAdapter(new HolidayAdapter(getActivity(), mix));

            binding.holidayRecyclerView.setVisibility(View.VISIBLE);
            binding.notFoundLayout.setVisibility(View.GONE);
        }


        return binding.getRoot();
    }
}