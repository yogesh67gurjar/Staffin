package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.example.staffin.Adapter.AttendanceAdapter;
import com.example.staffin.Adapter.TotalEmployeeAdapter;
import com.example.staffin.Response.Attendance;
import com.example.staffin.databinding.ActivityAttendanceBinding;

import java.util.ArrayList;
import java.util.List;

public class AttendanceActivity extends AppCompatActivity {
    ActivityAttendanceBinding binding;

    List<Attendance> attendanceList;
    AttendanceAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAttendanceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.allEmployeesBtn.setBackgroundResource(R.drawable.bg__blue_attendance);
        binding.allEmployeesBtn.setTextColor(getResources().getColor(R.color.white));
        binding.presentBtn.setBackgroundResource(R.drawable.bg_back_btn);
        binding.presentBtn.setTextColor(getResources().getColor(R.color.black));
        binding.absentBtn.setBackgroundResource(R.drawable.bg_back_btn);
        binding.absentBtn.setTextColor(getResources().getColor(R.color.black));

        attendanceList=new ArrayList<>();
        attendanceList.add(new Attendance("yogesh gurjar","7999717423","ksdfnsdfkjsdn","08/08/1999","1234","yogesh67gurjar@gmail.com"));
        attendanceList.add(new Attendance("sakshi naidu","6262688978","ksdfnsdfkjsdn","10/09/1999","1235","sakshinaidu@gmail.com"));
        attendanceList.add(new Attendance("ashok sir","8998787678","ksdfnsdfkjsdn","08/08/1979","1444","agehlot806@gmail.com"));
        attendanceList.add(new Attendance("shubham sharma","9981688969","ksdfnsdfkjsdn","08/08/1999","2244","shubhamsharma19994@gmail.com"));
        attendanceList.add(new Attendance("shubham raikwar","8319987270","ksdfnsdfkjsdn","08/08/1999","1221","shubhamraikwar@gmail.com"));
        attendanceList.add(new Attendance("pragati sharma","7566579522","ksdfnsdfkjsdn","08/08/1999","1124","psharma@gmail.com"));
        attendanceList.add(new Attendance("madhur sir","8997644533","ksdfnsdfkjsdn","08/08/1999","1111","madhurandroid@gmail.com"));
        attendanceList.add(new Attendance("shubhi gupta","9826821679","ksdfnsdfkjsdn","08/08/1999","8778","shubhigupta@gmail.com"));
        attendanceList.add(new Attendance("shivani mam","9288356233","ksdfnsdfkjsdn","08/08/1999","1284","hrtechpanda@gmail.com"));

        binding.attendanceRv.setLayoutManager(new LinearLayoutManager(this));
        adapter=new AttendanceAdapter(AttendanceActivity.this,attendanceList);
        binding.attendanceRv.setAdapter(adapter);
        
        binding.searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());}

            @Override
            public void afterTextChanged(Editable s) {}
        });

       
    

        binding.allEmployeesBtn.setOnClickListener(v -> {
            binding.allEmployeesBtn.setBackgroundResource(R.drawable.bg__blue_attendance);
            binding.allEmployeesBtn.setTextColor(getResources().getColor(R.color.white));
            binding.presentBtn.setBackgroundResource(R.drawable.bg_back_btn);
            binding.presentBtn.setTextColor(getResources().getColor(R.color.black));
            binding.absentBtn.setBackgroundResource(R.drawable.bg_back_btn);
            binding.absentBtn.setTextColor(getResources().getColor(R.color.black));
            adapter.filterList(attendanceList);
        });

        binding.presentBtn.setOnClickListener(v -> {
            binding.allEmployeesBtn.setBackgroundResource(R.drawable.bg_back_btn);
            binding.allEmployeesBtn.setTextColor(getResources().getColor(R.color.black));
            binding.presentBtn.setBackgroundResource(R.drawable.bg__blue_attendance);
            binding.presentBtn.setTextColor(getResources().getColor(R.color.white));
            binding.absentBtn.setBackgroundResource(R.drawable.bg_back_btn);
            binding.absentBtn.setTextColor(getResources().getColor(R.color.black));

        });

        binding.absentBtn.setOnClickListener(v -> {
            binding.allEmployeesBtn.setBackgroundResource(R.drawable.bg_back_btn);
            binding.allEmployeesBtn.setTextColor(getResources().getColor(R.color.black));
            binding.presentBtn.setBackgroundResource(R.drawable.bg_back_btn);
            binding.presentBtn.setTextColor(getResources().getColor(R.color.black));
            binding.absentBtn.setBackgroundResource(R.drawable.bg__blue_attendance);
            binding.absentBtn.setTextColor(getResources().getColor(R.color.white));

        });
        binding.btnBack.setOnClickListener(v -> {
            finish();
        });
        binding.btnCalendar.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), CalendarSettingActivity.class));
        });

    }
    void filter(String text){
        List<Attendance> filteredList = new ArrayList();

        for(Attendance a: attendanceList){
            if(a.getName().toLowerCase().contains(text.toLowerCase()))
            {
                filteredList.add(a);
            }
        }
        //update recyclerview
        adapter.filterList(filteredList);
    }
}