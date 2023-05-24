package com.example.staffin;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.staffin.Adapter.PayRollAdapter;
import com.example.staffin.Adapter.PayslipAdapter;
import com.example.staffin.Interface.ApiInterface;
import com.example.staffin.Response.AllPayroll;
import com.example.staffin.Response.EmployeeResult;
import com.example.staffin.Response.PayslipDetail;
import com.example.staffin.Response.SearchPayslip;
import com.example.staffin.Response.TotalEmployeeResponse;
import com.example.staffin.Retrofit.RetrofitServices;
import com.example.staffin.databinding.ActivityPayrollBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PayrollActivity extends AppCompatActivity {

    ActivityPayrollBinding binding;
    ApiInterface apiInterface;
    List<String> employeesList;
    List<Integer> employeesIdList;
    String selectEmployee, selectedMonth, selectedYear;
    int selectedId;
    String[] allMonths = {"Month", "january", "february", "march", "april", "may", "june", "july", "august", "september", "october", "november", "december"};
    String[] allYears = {"Year", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPayrollBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        employeesList = new ArrayList<>();
//        employeesList.add("Employee");
        employeesIdList = new ArrayList<>();
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        binding.noDataFound.setVisibility(View.GONE);
        binding.PayRollRv.setVisibility(View.GONE);
        binding.shimmerViewContainer.setVisibility(View.VISIBLE);
        binding.shimmerViewContainer.startShimmer();
        if (isNetworkAvailable()) {
            getApi();
            setOnClickListener();
            shortdata();
        } else {
            Toast.makeText(this, "Please Check Your Network...", Toast.LENGTH_SHORT).show();
        }


    }

    private void shortdata() {


        Call<TotalEmployeeResponse> callGetTotalEmployee = apiInterface.getTotalEmployee();
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
                    ArrayAdapter ee = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, names);
                    ee.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.spinnerEmployeeName.setAdapter(ee);
                    binding.spinnerEmployeeName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            selectEmployee = names[position];
                            selectedId = employeesIdList.get(position);
//                            Toast.makeText(PayrollActivity.this, String.valueOf(selectedId), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                } else {

                }
            }

            @Override
            public void onFailure(Call<TotalEmployeeResponse> call, Throwable t) {

            }
        });


//monthSpinner
        ArrayAdapter aa = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, allMonths);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        binding.spinnerMonth.setAdapter(aa);
        binding.spinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedMonth = allMonths[position];


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //yearSpinner
        ArrayAdapter yy = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, allYears);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        binding.spinnerYear.setAdapter(yy);
        binding.spinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedYear = allYears[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void setOnClickListener() {
        binding.btnExpanses.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), ExpansesActivity.class));
        });
        binding.btnHome.setOnClickListener(v -> {
            finish();
        });
        binding.nextBtn.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), InsidePayrollActivity.class));
        });

        binding.btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.PayRollRv.setVisibility(View.GONE);
                binding.noDataFound.setVisibility(View.GONE);
                binding.shimmerViewContainer.setVisibility(View.VISIBLE);
                binding.shimmerViewContainer.startShimmer();
                String xMonth = null;
                String months = binding.spinnerMonth.getSelectedItem().toString();
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


                Call<SearchPayslip> searchPayslipCall = apiInterface.getPayslipBySearch(String.valueOf(selectedId), xMonth, selectedYear);
                searchPayslipCall.enqueue(new Callback<SearchPayslip>() {
                    @Override
                    public void onResponse(Call<SearchPayslip> call, Response<SearchPayslip> response) {
                        if (response.isSuccessful()) {
                            binding.shimmerViewContainer.stopShimmer();
                            binding.shimmerViewContainer.setVisibility(View.GONE);
                            binding.noDataFound.setVisibility(View.GONE);
                            binding.PayRollRv.setVisibility(View.VISIBLE);
                            List<SearchPayslip.PayslipDetail> singleUnit = response.body().getPayslipDetails();
                            binding.PayRollRv.setLayoutManager(new LinearLayoutManager(PayrollActivity.this));
                            binding.PayRollRv.setAdapter(new PayslipAdapter(singleUnit, PayrollActivity.this));
                            Log.d("onResponse: ", response.message());

                        } else {
                            binding.shimmerViewContainer.stopShimmer();
                            binding.shimmerViewContainer.setVisibility(View.GONE);
                            binding.noDataFound.setVisibility(View.VISIBLE);
                            binding.PayRollRv.setVisibility(View.INVISIBLE);
                            Log.d("onResponseElse:", response.message());
                            Toast.makeText(PayrollActivity.this, "No Data Found...", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<SearchPayslip> call, Throwable t) {
                        Log.d("onFailure: ", t.getMessage());
                        Toast.makeText(PayrollActivity.this, "OnResponse Failure", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
    }

    private void getApi() {

        Call<AllPayroll> callGetAllPayroll = apiInterface.getAllPayroll();
        callGetAllPayroll.enqueue(new Callback<AllPayroll>() {
            @Override
            public void onResponse(Call<AllPayroll> call, Response<AllPayroll> response) {
                if (response.isSuccessful()) {
                    binding.shimmerViewContainer.stopShimmer();
                    binding.shimmerViewContainer.setVisibility(View.GONE);
                    binding.PayRollRv.setVisibility(View.VISIBLE);
                    List<AllPayroll.AllPayslipDetail> resp = response.body().getAllPayslipDetails();
                    binding.PayRollRv.setLayoutManager(new LinearLayoutManager(PayrollActivity.this));
                    binding.PayRollRv.setAdapter(new PayRollAdapter(PayrollActivity.this, resp));
                    Log.d("onResponse: ", response.message());
                } else {
                    Log.d("onResponseElse:", response.message());
                    Toast.makeText(PayrollActivity.this, "OnResponse Else", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<AllPayroll> call, Throwable t) {
                Log.d("onFailure: ", t.getMessage());
                Toast.makeText(PayrollActivity.this, "OnResponse Failure", Toast.LENGTH_SHORT).show();
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