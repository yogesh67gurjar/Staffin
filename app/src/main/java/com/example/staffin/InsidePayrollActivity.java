package com.example.staffin;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.staffin.Interface.ApiInterface;
import com.example.staffin.Response.EmployeeResult;
import com.example.staffin.Response.PayrollResponse;
import com.example.staffin.Response.TotalEmployeeResponse;
import com.example.staffin.Retrofit.RetrofitServices;
import com.example.staffin.databinding.ActivityInsidePayrollBinding;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsidePayrollActivity extends AppCompatActivity {


    private static final String TAG = "InsidePayrollActivity";
    ActivityInsidePayrollBinding binding;

    String selectedMonth, selectedYear;
    ApiInterface apiInterface;
    ProgressDialog progress;
    List<String> employeesList;
    List<Integer> myId;
    List<Integer> employeesIdList;
    boolean[] selectedEmployees;


    String[] allMonths = {"january", "february", "march", "april", "may", "june", "july", "august", "september", "october", "november", "december"};
    String[] allYears = {"2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsidePayrollBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        monthFunc();
        yearFunc();
        clickListeners();
    }

    private void clickListeners() {


        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        progress = new ProgressDialog(InsidePayrollActivity.this);
        progress.setMessage("please wait.....");
        employeesList = new ArrayList<>();
        employeesIdList = new ArrayList<>();
        myId = new ArrayList<>();
        if (isNetworkAvailable()) {
            Call<TotalEmployeeResponse> callGetTotalEmployee = apiInterface.getTotalEmployee();
            progress.show();
            callGetTotalEmployee.enqueue(new Callback<TotalEmployeeResponse>() {
                @Override
                public void onResponse(Call<TotalEmployeeResponse> call, Response<TotalEmployeeResponse> response) {
                    if (response.isSuccessful()) {
                        List<EmployeeResult> resp = response.body().getEmployeeResult();

                        TextView[] textViews = new TextView[resp.size()];

                        String[] names = new String[response.body().getEmployeeResult().size()];

                        for (int i = 0; i < response.body().getEmployeeResult().size(); i++) {
                            employeesList.add(resp.get(i).getFullName());
                            employeesIdList.add(resp.get(i).getId());
                            names[i] = employeesList.get(i);
                        }

                        selectedEmployees = new boolean[response.body().getEmployeeResult().size()];
                        binding.addMemberBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(InsidePayrollActivity.this);

                                builder.setTitle("Select Employees");
                                builder.setIcon(R.drawable.img_dp);

                                builder.setMultiChoiceItems(names, selectedEmployees, (dialog, which, isChecked) -> {
                                    selectedEmployees[which] = isChecked;

                                });

                                builder.setCancelable(false);

                                builder.setNeutralButton("CLEAR ALL", (dialog, which) -> {
                                    Arrays.fill(selectedEmployees, false);
                                    binding.dynamicLl.removeAllViews();
                                });
                                builder.setNegativeButton("CANCEL", (dialog, which) -> {
                                });
                                builder.setPositiveButton("Done", (dialog, which) -> {
                                    binding.dynamicLl.removeAllViews();
                                    for (int i = 0; i < selectedEmployees.length; i++) {
                                        if (selectedEmployees[i]) {
                                            textViews[i] = new TextView(InsidePayrollActivity.this);
                                            if (binding.dynamicLl.getChildCount() < 1) {
                                                textViews[i].setText(employeesList.get(i));
                                            } else {
                                                textViews[i].setText(" , " + employeesList.get(i));
                                            }
                                            myId.add(employeesIdList.get(i));
                                            binding.dynamicLl.addView(textViews[i]);
                                        }
                                    }
                                });

                                builder.create();
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            }
                        });

                        binding.dynamicLl.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                binding.addMemberBtn.performClick();
                            }
                        });

                        progress.dismiss();
                    } else {
                        Log.d(TAG, "onResponse: " + response.message());
                        Toast.makeText(InsidePayrollActivity.this, "unable to process", Toast.LENGTH_SHORT).show();
                        progress.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<TotalEmployeeResponse> call, Throwable t) {
                    Toast.makeText(InsidePayrollActivity.this, "some failure occured", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onFailure: " + t.getMessage());
                    progress.dismiss();
                }
            });
        } else {
            Toast.makeText(this, "Internet Not Available", Toast.LENGTH_SHORT).show();
        }

        binding.btnHome.setOnClickListener(v -> {
            finish();
        });
        binding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String xMonth = null;
                String months = binding.monthEt.getSelectedItem().toString();
                if (months == "january") {
                    xMonth = "01";
                } else if (months == "february") {
                    xMonth = "02";
                } else if (months == "march") {
                    xMonth = "03";
                } else if (months == "april") {
                    xMonth = "04";
                } else if (months == "may") {
                    xMonth = "05";
                } else if (months == "june") {
                    xMonth = "06";
                } else if (months == "july") {
                    xMonth = "07";
                } else if (months == "august") {
                    xMonth = "08";
                } else if (months == "september") {
                    xMonth = "09";
                } else if (months == "october") {
                    xMonth = "10";
                } else if (months == "november") {
                    xMonth = "11";
                } else if (months == "december") {
                    xMonth = "12";
                }


//                List<Integer> ids = new ArrayList<>();
//                String check;
//                check = String.valueOf(binding.dynamicLl.getChildCount());
//                if (check == "") {
//                    ids.add(employeesIdList.get(0));
//                } else if (check == ",") {
//                    ids.add(employeesIdList.get(1));
//                }


                String years = binding.yearEt.getSelectedItem().toString();
                Call<PayrollResponse> payrollResponseCall = apiInterface.postPayRoll(myId, years, xMonth);
                payrollResponseCall.enqueue(new Callback<PayrollResponse>() {
                    @Override
                    public void onResponse(Call<PayrollResponse> call, Response<PayrollResponse> response) {
                        if (response.isSuccessful()) {
                            Log.e("employeelistOfId", myId.toString());
                            Log.e("employee of yera", years);
                            Log.e("employee of months", months);
                            startActivity(new Intent(InsidePayrollActivity.this, MainActivity.class));
                            Toast.makeText(InsidePayrollActivity.this, "Payrolls have been added successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Log.d(TAG, "onResponse: " + response.message());
                            Toast.makeText(InsidePayrollActivity.this, "try again", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<PayrollResponse> call, Throwable t) {
                        Log.d(TAG, "onFailure:" + t.getMessage());
                        Toast.makeText(InsidePayrollActivity.this, "error found", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
    }

    private void yearFunc() {
        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, allYears);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.yearEt.setAdapter(ad);
        binding.yearEt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedYear = allYears[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void monthFunc() {
        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, allMonths);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.monthEt.setAdapter(ad);
        binding.monthEt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedMonth = allMonths[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}