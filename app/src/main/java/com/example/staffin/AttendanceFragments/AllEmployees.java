package com.example.staffin.AttendanceFragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.staffin.Adapter.AttendanceAdapter;
import com.example.staffin.Response.TodayAttendance;
import com.example.staffin.databinding.FragmentAllEmployeesBinding;

import java.util.ArrayList;
import java.util.List;

public class AllEmployees extends Fragment {

    AttendanceAdapter adapter;
    FragmentAllEmployeesBinding binding;
    //    List<Attendance> attendanceList;
    List<TodayAttendance> todayAttendances;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAllEmployeesBinding.inflate(inflater, container, false);
//        attendanceList = new ArrayList<>();
        todayAttendances = new ArrayList<>();
        todayAttendances = (List<TodayAttendance>) getArguments().getSerializable("allEmployees");
//        attendanceList = (List<Attendance>) getArguments().getSerializable("allEmployees");
        binding.attendanceRv.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new AttendanceAdapter(getContext(), todayAttendances);
        if (todayAttendances.size() > 0) {
            binding.attendanceRv.setVisibility(View.VISIBLE);
            binding.nothingFound.setVisibility(View.GONE);
        } else {
            binding.attendanceRv.setVisibility(View.GONE);
            binding.nothingFound.setVisibility(View.VISIBLE);
        }
        binding.attendanceRv.setAdapter(adapter);

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
        return binding.getRoot();
    }

    void filter(String text) {
        List<TodayAttendance> filteredList = new ArrayList<>();
        for (TodayAttendance a : todayAttendances) {
            if (a.getFullName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(a);
            }
        }
        //update recyclerview
        adapter.filterList(filteredList);
    }
}