package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.staffin.Interface.ApiInterface;
import com.example.staffin.Response.PaySlipResponse;
import com.example.staffin.Response.PayslipDetail;
import com.example.staffin.Retrofit.RetrofitServices;
import com.example.staffin.databinding.ActivityPaySlipBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaySlipActivity extends AppCompatActivity {
    ActivityPaySlipBinding binding;

    ApiInterface apiInterface;
    int Id;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaySlipBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sharedPreferences = getApplicationContext().getSharedPreferences("staffin", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
//        Id = getIntent().getIntExtra("Id", 0);
        //        Log.i("Id AArahi AHI", Id);
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        setOnClickListener();
//        getApi();
    }

//    private void getApi() {
//        final ProgressDialog progressDialog = new ProgressDialog(getApplicationContext());
//        progressDialog.setMessage("Loading...");
//        progressDialog.show();
//        Call<PaySlipResponse> paySlipResponseCall = apiInterface.getPaySlip(Integer.parseInt(Id));
//        paySlipResponseCall.enqueue(new Callback<PaySlipResponse>() {
//            @Override
//            public void onResponse(Call<PaySlipResponse> call, Response<PaySlipResponse> response) {
//                if (response.isSuccessful()) {
//                    progressDialog.dismiss();
//                    if (response.body().getPayslipDetails().size() == 0) {
//
//                        binding.notFoundLayout.setVisibility(View.VISIBLE);
//                        binding.nestedScrollFirst.setVisibility(View.GONE);
//
//                    } else {
//                        binding.nestedScrollFirst.setVisibility(View.VISIBLE);
//                        binding.notFoundLayout.setVisibility(View.GONE);
//                        PayslipDetail singleUnit = response.body().getPayslipDetails().get(0);
//                        binding.indicator.setText(singleUnit.getStatus());
//                        binding.basicAmount.setText(singleUnit.getBasic());
//                        binding.hourlyAmount.setText(singleUnit.getOvertimeHours());
//                        binding.expenseAmount.setText(singleUnit.getExpense());
//                        binding.bounceAmount.setText(singleUnit.getTotalAllowance());
//                        binding.deductionAmount.setText(singleUnit.getDeductions());
//                        binding.netAmount.setText(singleUnit.getNetSalary());
//                        binding.empId.setText("Emp. ID - " + singleUnit.getEmployeeId());
//                    }
//
//
//                } else {
//                    progressDialog.dismiss();
//                    Toast.makeText(getApplicationContext(), "Try Again", Toast.LENGTH_SHORT).show();
//                    Log.e("Try Again Karo", response.message());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<PaySlipResponse> call, Throwable t) {
//                progressDialog.dismiss();
//                Toast.makeText(getApplicationContext(), "Network Error", Toast.LENGTH_SHORT).show();
//                Log.e("Api not Working", t.getMessage());
//
//
//            }
//        });
//
//    }
    private void setOnClickListener() {
        binding.btnHome.setOnClickListener(v -> {
            finish();
        });
        binding.btnShare.setOnClickListener(v -> {
            finish();
        });
    }
}