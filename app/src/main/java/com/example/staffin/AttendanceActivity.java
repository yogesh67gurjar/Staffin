package com.example.staffin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.staffin.Adapter.AttendanceAdapter;
import com.example.staffin.AttendanceFragments.Absent;
import com.example.staffin.AttendanceFragments.AllEmployees;
import com.example.staffin.AttendanceFragments.Present;
import com.example.staffin.Interface.ApiInterface;
import com.example.staffin.Response.Attendance;
import com.example.staffin.Response.AttendanceFulFaal;
import com.example.staffin.Response.AttendanceResponse;
import com.example.staffin.Response.DailyAttendance;
import com.example.staffin.Response.EmployeeResult;
import com.example.staffin.Response.TodayAttendance;
import com.example.staffin.Response.TotalEmployeeResponse;
import com.example.staffin.Retrofit.RetrofitServices;
import com.example.staffin.databinding.ActivityAttendanceBinding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class AttendanceActivity extends AppCompatActivity {
    private static final String TAG = "AttendanceActivity";
    ActivityAttendanceBinding binding;

    List<Attendance> attendanceList;

    ApiInterface apiInterface;

    List<TodayAttendance> presentToday;
    List<TodayAttendance> absentToday;

    ProgressDialog progress;
    Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAttendanceBinding.inflate(getLayoutInflater());
        progress = new ProgressDialog(AttendanceActivity.this);
        progress.setMessage("please wait....");
        setContentView(binding.getRoot());
        bundle = new Bundle();

        presentToday = new ArrayList<>();
        absentToday = new ArrayList<>();

        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);

        binding.allEmployeesBtn.setBackgroundResource(R.drawable.bg__blue_attendance);
        binding.allEmployeesBtn.setTextColor(getResources().getColor(R.color.white));
        binding.presentBtn.setBackgroundResource(R.drawable.bg_back_btn);
        binding.presentBtn.setTextColor(getResources().getColor(R.color.black));
        binding.absentBtn.setBackgroundResource(R.drawable.bg_back_btn);
        binding.absentBtn.setTextColor(getResources().getColor(R.color.black));

        attendanceList = new ArrayList<>();

        Call<DailyAttendance> callGetAllEmployeeDailyAttendance = apiInterface.getAllEmployeeDailyAttendance();
        progress.show();
        callGetAllEmployeeDailyAttendance.enqueue(new Callback<DailyAttendance>() {
            @Override
            public void onResponse(Call<DailyAttendance> call, Response<DailyAttendance> response) {
                if (response.isSuccessful()) {
                    progress.dismiss();
                    List<TodayAttendance> todayAttendanceList = response.body().getTodayAttendanceList();

                    for (TodayAttendance tA : todayAttendanceList) {
                        if (tA.getAttendanceData().size() < 1) {
                            absentToday.add(tA);
                        } else {
                            presentToday.add(tA);
                        }
                    }
                    AllEmployees allEmpByDef = new AllEmployees();
                    bundle.putSerializable("allEmployees", (Serializable) todayAttendanceList);
                    allEmpByDef.setArguments(bundle);

                    // on activity open
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, allEmpByDef).commit();

                    binding.allEmployeesBtn.setOnClickListener(v -> {
                        binding.allEmployeesBtn.setBackgroundResource(R.drawable.bg__blue_attendance);
                        binding.allEmployeesBtn.setTextColor(getResources().getColor(R.color.white));
                        binding.presentBtn.setBackgroundResource(R.drawable.bg_back_btn);
                        binding.presentBtn.setTextColor(getResources().getColor(R.color.black));
                        binding.absentBtn.setBackgroundResource(R.drawable.bg_back_btn);
                        binding.absentBtn.setTextColor(getResources().getColor(R.color.black));

                        bundle.putSerializable("allEmployees", (Serializable) todayAttendanceList);
                        allEmpByDef.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, allEmpByDef).commit();
                    });

                    binding.presentBtn.setOnClickListener(v -> {
                        binding.allEmployeesBtn.setBackgroundResource(R.drawable.bg_back_btn);
                        binding.allEmployeesBtn.setTextColor(getResources().getColor(R.color.black));
                        binding.presentBtn.setBackgroundResource(R.drawable.bg__blue_attendance);
                        binding.presentBtn.setTextColor(getResources().getColor(R.color.white));
                        binding.absentBtn.setBackgroundResource(R.drawable.bg_back_btn);
                        binding.absentBtn.setTextColor(getResources().getColor(R.color.black));

                        Present presentFrag = new Present();
                        bundle.putSerializable("present", (Serializable) presentToday);
                        presentFrag.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, presentFrag).commit();
                    });

                    binding.absentBtn.setOnClickListener(v -> {
                        binding.allEmployeesBtn.setBackgroundResource(R.drawable.bg_back_btn);
                        binding.allEmployeesBtn.setTextColor(getResources().getColor(R.color.black));
                        binding.presentBtn.setBackgroundResource(R.drawable.bg_back_btn);
                        binding.presentBtn.setTextColor(getResources().getColor(R.color.black));
                        binding.absentBtn.setBackgroundResource(R.drawable.bg__blue_attendance);
                        binding.absentBtn.setTextColor(getResources().getColor(R.color.white));

                        Absent absentFrag = new Absent();
                        bundle.putSerializable("absent", (Serializable) absentToday);
                        absentFrag.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, absentFrag).commit();
                    });


                } else {
                    Toast.makeText(AttendanceActivity.this, "some error occured", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onResponse: " + response.message());
                    progress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<DailyAttendance> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(AttendanceActivity.this, "failed to load data", Toast.LENGTH_SHORT).show();
                Log.d(TAG + "11", "onFailure: " + t.getMessage());
            }
        });
        binding.btnBack.setOnClickListener(v -> {
            finish();
        });
        binding.btnCalendar.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), CalendarSettingActivity.class));
        });

    }

}