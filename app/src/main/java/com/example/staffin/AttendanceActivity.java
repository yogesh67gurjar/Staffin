package com.example.staffin;

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
import com.example.staffin.Response.EmployeeResult;
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
    ActivityAttendanceBinding binding;

    List<Attendance> attendanceList;
    List<Attendance> present;
    List<Attendance> absent;
    AttendanceAdapter adapter;
    ApiInterface apiInterface;
    List<AttendanceFulFaal> attendanceFulFaalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAttendanceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        attendanceFulFaalList = new ArrayList<>();
        attendanceFulFaalList.add(new AttendanceFulFaal(23, "sdfjsd", "yoga", "sdfjsd", "sdfjsd", "sdfjsd", "sdfjsd", "sdfjsd", "sdfjsd", "fail", "sdfjsd", "sdfjsd", "sdfjsd"));

        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);

        binding.allEmployeesBtn.setBackgroundResource(R.drawable.bg__blue_attendance);
        binding.allEmployeesBtn.setTextColor(getResources().getColor(R.color.white));
        binding.presentBtn.setBackgroundResource(R.drawable.bg_back_btn);
        binding.presentBtn.setTextColor(getResources().getColor(R.color.black));
        binding.absentBtn.setBackgroundResource(R.drawable.bg_back_btn);
        binding.absentBtn.setTextColor(getResources().getColor(R.color.black));

        attendanceList = new ArrayList<>();

        Call<TotalEmployeeResponse> callGetTotalEmployee = apiInterface.getTotalEmployee();
        callGetTotalEmployee.enqueue(new Callback<TotalEmployeeResponse>() {
            @Override
            public void onResponse(Call<TotalEmployeeResponse> call, Response<TotalEmployeeResponse> response) {
                if (response.isSuccessful()) {
                    List<EmployeeResult> employeeResultList = response.body().getEmployeeResult();
                    for (EmployeeResult e : employeeResultList) {
                        Call<AttendanceResponse> callGetAttendanceById = apiInterface.getAttendanceById(e.getId());
                        callGetAttendanceById.enqueue(new Callback<AttendanceResponse>() {
                            @Override
                            public void onResponse(Call<AttendanceResponse> call, Response<AttendanceResponse> response) {
                                if (response.isSuccessful()) {
//                                    Toast.makeText(AttendanceActivity.this, "SDFSDFSDFSDF", Toast.LENGTH_SHORT).show();
                                    attendanceFulFaalList.add(new AttendanceFulFaal(e.getId(), e.getEmployeeID(), e.getFullName(), e.getEmail(), e.getMobileNumber(), e.getDateOfBirth(), e.getDepartmentId(), e.getDesignation(), e.getProfileImage(), "response.body().getTodayAttendanceList().getStatus()", e.getWorkDuration(), "response.body().getTodayAttendanceList().getClockIn()", "response.body().getTodayAttendanceList().getClockOut()"));
                                    if (response.body().getMessage().equalsIgnoreCase("success")) {
                                        Log.d("SUCCESS", response.body().getMessage());
                                        attendanceFulFaalList.add(new AttendanceFulFaal(e.getId(), e.getEmployeeID(), e.getFullName(), e.getEmail(), e.getMobileNumber(), e.getDateOfBirth(), e.getDepartmentId(), e.getDesignation(), e.getProfileImage(), response.body().getTodayAttendanceList().getStatus(), e.getWorkDuration(), response.body().getTodayAttendanceList().getClockIn(), response.body().getTodayAttendanceList().getClockOut()));
                                    } else {
                                        Log.d("ABSENT", response.body().getMessage());
                                        attendanceFulFaalList.add(new AttendanceFulFaal(e.getId(), e.getEmployeeID(), e.getFullName(), e.getEmail(), e.getMobileNumber(), e.getDateOfBirth(), e.getDepartmentId(), e.getDesignation(), e.getProfileImage(), "absent", e.getWorkDuration(), "punch in", "punch out"));
                                    }
                                } else {
                                    Toast.makeText(AttendanceActivity.this, "some error occured", Toast.LENGTH_SHORT).show();
                                    Log.d("fdfs", response.message());
                                }
                            }

                            @Override
                            public void onFailure(Call<AttendanceResponse> call, Throwable t) {
                                Toast.makeText(AttendanceActivity.this, "some failure occured", Toast.LENGTH_SHORT).show();
                                Log.d("dfsdfsf", t.getMessage());
                            }
                        });

                    }
                    for (AttendanceFulFaal a : attendanceFulFaalList) {
                        Log.d("ATTENDANCEDANCE", a.getFullName());
                        Log.d("ATTENCE", a.getStatus());
                    }


                } else {
                    Toast.makeText(AttendanceActivity.this, "some error occured", Toast.LENGTH_SHORT).show();
                    Log.d("knsdfsdf", response.message());
                }
            }

            @Override
            public void onFailure(Call<TotalEmployeeResponse> call, Throwable t) {
                Toast.makeText(AttendanceActivity.this, "some failure occured", Toast.LENGTH_SHORT).show();
                Log.d("sdjkfnsdkf", t.getMessage());
            }
        });



        attendanceList.add(new Attendance("yogesh gurjar", "+917999717423", "ksdfnsdfkjsdn", "08/08/1999", "1234", "yogesh67gurjar@gmail.com", "present"));
        attendanceList.add(new Attendance("sakshi naidu", "+916262688978", "ksdfnsdfkjsdn", "10/09/1999", "1235", "sakshinaidu@gmail.com", "present"));
        attendanceList.add(new Attendance("ashok sir", "+918998787678", "ksdfnsdfkjsdn", "08/08/1979", "1444", "agehlot806@gmail.com", "absent"));
        attendanceList.add(new Attendance("shubham sharma", "+919981688969", "ksdfnsdfkjsdn", "08/08/1999", "2244", "shubhamsharma19994@gmail.com", "absent"));
        attendanceList.add(new Attendance("shubham raikwar", "+918319987270", "ksdfnsdfkjsdn", "08/08/1999", "1221", "shubhamraikwar@gmail.com", "absent"));
        attendanceList.add(new Attendance("pragati sharma", "+917566579522", "ksdfnsdfkjsdn", "08/08/1999", "1124", "psharma@gmail.com", "absent"));
        attendanceList.add(new Attendance("madhur sir", "+918997644533", "ksdfnsdfkjsdn", "08/08/1999", "1111", "madhurandroid@gmail.com", "absent"));
        attendanceList.add(new Attendance("shubhi gupta", "+919826821679", "ksdfnsdfkjsdn", "08/08/1999", "8778", "shubhigupta@gmail.com", "absent"));
        attendanceList.add(new Attendance("shivani mam", "+919288356233", "ksdfnsdfkjsdn", "08/08/1999", "1284", "hrtechpanda@gmail.com", "absent"));

        present = new ArrayList<>();
        absent = new ArrayList<>();

        for (Attendance a : attendanceList) {
            if (a.getStatus().equalsIgnoreCase("present")) {
                present.add(a);
            } else {
                absent.add(a);
            }
        }
//        Toast.makeText(this, "ffffffffffffffffff", Toast.LENGTH_SHORT).show();

        AllEmployees allEmployeesByDefault = new AllEmployees();

        Bundle bundle = new Bundle();
        bundle.putSerializable("allEmployees", (Serializable) attendanceList);
        allEmployeesByDefault.setArguments(bundle);

        // on activity open
        getSupportFragmentManager().beginTransaction().replace(R.id.container, allEmployeesByDefault).commit();

        binding.allEmployeesBtn.setOnClickListener(v -> {
            binding.allEmployeesBtn.setBackgroundResource(R.drawable.bg__blue_attendance);
            binding.allEmployeesBtn.setTextColor(getResources().getColor(R.color.white));
            binding.presentBtn.setBackgroundResource(R.drawable.bg_back_btn);
            binding.presentBtn.setTextColor(getResources().getColor(R.color.black));
            binding.absentBtn.setBackgroundResource(R.drawable.bg_back_btn);
            binding.absentBtn.setTextColor(getResources().getColor(R.color.black));

            bundle.putSerializable("allEmployees", (Serializable) attendanceList);
            allEmployeesByDefault.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, allEmployeesByDefault).commit();
//            adapter.filterList(attendanceList);
        });

        binding.presentBtn.setOnClickListener(v -> {
            binding.allEmployeesBtn.setBackgroundResource(R.drawable.bg_back_btn);
            binding.allEmployeesBtn.setTextColor(getResources().getColor(R.color.black));
            binding.presentBtn.setBackgroundResource(R.drawable.bg__blue_attendance);
            binding.presentBtn.setTextColor(getResources().getColor(R.color.white));
            binding.absentBtn.setBackgroundResource(R.drawable.bg_back_btn);
            binding.absentBtn.setTextColor(getResources().getColor(R.color.black));

            Present presentFrag = new Present();
            bundle.putSerializable("present", (Serializable) present);
            presentFrag.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, presentFrag).commit();

//            filterPresent("present");
        });

        binding.absentBtn.setOnClickListener(v -> {
            binding.allEmployeesBtn.setBackgroundResource(R.drawable.bg_back_btn);
            binding.allEmployeesBtn.setTextColor(getResources().getColor(R.color.black));
            binding.presentBtn.setBackgroundResource(R.drawable.bg_back_btn);
            binding.presentBtn.setTextColor(getResources().getColor(R.color.black));
            binding.absentBtn.setBackgroundResource(R.drawable.bg__blue_attendance);
            binding.absentBtn.setTextColor(getResources().getColor(R.color.white));

            Absent absentFrag = new Absent();
            bundle.putSerializable("absent", (Serializable) absent);
            absentFrag.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, absentFrag).commit();

//            filterAbsent("absent");
        });
        binding.btnBack.setOnClickListener(v -> {
            finish();
        });
        binding.btnCalendar.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), CalendarSettingActivity.class));
        });

    }

//    void filter(String text) {
//        List<Attendance> filteredList = new ArrayList();
//
//        for (Attendance a : attendanceList) {
//            if (a.getName().toLowerCase().contains(text.toLowerCase())) {
//                filteredList.add(a);
//            }
//        }
//        //update recyclerview
//        adapter.filterList(filteredList);
//    }

//    void filterPresent(String text) {
//        List<Attendance> filteredList = new ArrayList();
//
//        for (Attendance a : attendanceList) {
//            if (a.getStatus().toLowerCase().contains(text.toLowerCase())) {
//                filteredList.add(a);
//            }
//        }
//        //update recyclerview
//        adapter.filterList(filteredList);
//    }
//
//    void filterAbsent(String text) {
//        List<Attendance> filteredList = new ArrayList();
//
//        for (Attendance a : attendanceList) {
//            if (a.getStatus().toLowerCase().contains(text.toLowerCase())) {
//                filteredList.add(a);
//            }
//        }
//        //update recyclerview
//        adapter.filterList(filteredList);
//    }
}