package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.staffin.Interface.ApiInterface;
import com.example.staffin.Response.AllPayroll;
import com.example.staffin.Response.LoginResponse;
import com.example.staffin.Response.PaySlipResponse;
import com.example.staffin.Response.PayslipDetail;
import com.example.staffin.Retrofit.RetrofitServices;
import com.example.staffin.databinding.ActivitySalaryInfoBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.Path;

public class SalaryInfoActivity extends AppCompatActivity {
    ActivitySalaryInfoBinding binding;
    ApiInterface apiInterface;
    int IdI = 0;

    ProgressDialog progressDialog;
    String[] Status = {"Paid", "Unpaid"};
    String paidUnpaidByPosition = "paid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySalaryInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        progressDialog = new ProgressDialog(SalaryInfoActivity.this);
        progressDialog.setMessage("please wait...");
        progressDialog.setCancelable(false);
        IdI = getIntent().getIntExtra("IdI", 0);

        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        setOnClickListener();
        getApi();
    }

    private void getApi() {

        Call<PaySlipResponse> callProfileDetails = apiInterface.getPaySlip(IdI);
        progressDialog.show();
        callProfileDetails.enqueue(new Callback<PaySlipResponse>() {
            @Override
            public void onResponse(Call<PaySlipResponse> call, Response<PaySlipResponse> response) {
                if (response.isSuccessful()) {
                    List<AllPayroll.AllPayslipDetail.EmployeeId> resp = response.body().getPayslipDetails().get(0).getEmployeeId();
                    List<PayslipDetail> resp2 = response.body().getPayslipDetails();
                    Glide.with(getApplicationContext()).load(resp.get(0).getProfileImageUrl()).placeholder(R.drawable.img_dp).into(binding.dpImg);
                    binding.nameTv.setText(resp.get(0).getFullName());
                    binding.empId.setText("Emp Id:-" + resp.get(0).getEmployeeID());
                    binding.employeeIdEt.setText(resp2.get(0).getDaily_rate());
                    binding.departmentEt.setText(resp2.get(0).getTotal_working_day());
                    binding.jDateEt.setText(resp2.get(0).getOvertimeHours());
                    binding.rDateEt.setText(resp2.get(0).getOvertimePay());
                    binding.EtBasicSalary.setText(resp2.get(0).getBasic());
                    binding.EtExpenseClaim.setText(resp2.get(0).getExpense());
                    binding.EtBonus.setText(resp2.get(0).getBonus());
                    binding.EtDuration.setText(resp2.get(0).getTotalDeduction());
                    binding.EtNetSalary.setText(resp2.get(0).getNetSalary());

                    ArrayAdapter aa = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, Status);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.spinner.setAdapter(aa);
                    if (resp2.get(0).getStatus().trim().equalsIgnoreCase("paid")) {
                        binding.spinner.setSelection(0);
                        paidUnpaidByPosition = "paid";
                    } else {
                        binding.spinner.setSelection(1);
                        paidUnpaidByPosition = "unpaid";
                    }

                    progressDialog.dismiss();
                } else {
                    Toast.makeText(SalaryInfoActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
                    Log.d("sdfsdfas", response.message());
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<PaySlipResponse> call, Throwable t) {
                Toast.makeText(SalaryInfoActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                Log.d("gsdgsd", t.getMessage());
                progressDialog.dismiss();
            }
        });

    }

    private void setOnClickListener() {
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    paidUnpaidByPosition = "paid";
                } else {
                    paidUnpaidByPosition = "unpaid";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.btnHome.setOnClickListener(v -> {
            finish();
        });

        binding.nextBtn.setOnClickListener(v -> {

            if (binding.employeeIdEt.getText().toString().isEmpty()) {
                binding.employeeIdEt.setError("enter daily rate");
                binding.employeeIdEt.requestFocus();
            } else if (binding.departmentEt.getText().toString().isEmpty()) {
                binding.departmentEt.setError("enter total working days");
                binding.departmentEt.requestFocus();
            } else if (binding.jDateEt.getText().toString().isEmpty()) {
                binding.jDateEt.setError("enter overtime");
                binding.jDateEt.requestFocus();
            } else if (binding.rDateEt.getText().toString().isEmpty()) {
                binding.rDateEt.setError("enter amount");
                binding.rDateEt.requestFocus();
            } else if (binding.EtBasicSalary.getText().toString().isEmpty()) {
                binding.EtBasicSalary.setError("enter basic salary");
                binding.EtBasicSalary.requestFocus();
            } else if (binding.EtExpenseClaim.getText().toString().isEmpty()) {
                binding.EtExpenseClaim.setError("enter expenses claimed");
                binding.EtExpenseClaim.requestFocus();
            } else if (binding.EtBonus.getText().toString().isEmpty()) {
                binding.EtBonus.setError("enter bonus amount");
                binding.EtBonus.requestFocus();
            } else if (binding.EtDuration.getText().toString().isEmpty()) {
                binding.EtDuration.setError("enter deductions");
                binding.EtDuration.requestFocus();
            } else if (binding.EtNetSalary.getText().toString().isEmpty()) {
                binding.EtNetSalary.setError("enter net salary");
                binding.EtNetSalary.requestFocus();
            } else {
                String daily_rate, total_working_day, overtime_pay, overtime_hours, basic, expense, bonus, deduction, net_salary;
                daily_rate = binding.employeeIdEt.getText().toString();
                total_working_day = binding.departmentEt.getText().toString();
                overtime_pay = binding.jDateEt.getText().toString();
                overtime_hours = binding.rDateEt.getText().toString();
                basic = binding.EtBasicSalary.getText().toString();
                expense = binding.EtExpenseClaim.getText().toString();
                bonus = binding.EtBonus.getText().toString();
                deduction = binding.EtDuration.getText().toString();
                net_salary = binding.EtNetSalary.getText().toString();


                Call<LoginResponse> callPostUpdatePayroll = apiInterface.postUpdatePayroll(IdI, paidUnpaidByPosition, daily_rate, total_working_day, overtime_pay, overtime_hours, basic, expense, bonus, deduction, net_salary);
                progressDialog.show();
                callPostUpdatePayroll.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful()) {
                            progressDialog.dismiss();
                            Toast.makeText(SalaryInfoActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(SalaryInfoActivity.this, "some error occured", Toast.LENGTH_SHORT).show();
                            Log.d("fdfsa", response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(SalaryInfoActivity.this, "failure", Toast.LENGTH_SHORT).show();
                        Log.d("fdfsa", t.getMessage());
                        progressDialog.dismiss();
                    }
                });
            }
        });
    }
}