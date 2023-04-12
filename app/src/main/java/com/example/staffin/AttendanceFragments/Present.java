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
import com.example.staffin.Response.Attendance;
import com.example.staffin.Response.TodayAttendance;
import com.example.staffin.databinding.FragmentPresentBinding;

import java.util.ArrayList;
import java.util.List;

public class Present extends Fragment {

    FragmentPresentBinding binding;
    AttendanceAdapter adapter;
    List<TodayAttendance> attendanceList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPresentBinding.inflate(inflater, container, false);
        attendanceList = new ArrayList<>();
        attendanceList = (List<TodayAttendance>) getArguments().getSerializable("present");
        binding.attendanceRv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AttendanceAdapter(getContext(), attendanceList);
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
        List<TodayAttendance> filteredList = new ArrayList();
        for (TodayAttendance a : attendanceList) {
            if (a.getFullName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(a);
            }
        }
        //update recyclerview
        adapter.filterList(filteredList);
    }
}