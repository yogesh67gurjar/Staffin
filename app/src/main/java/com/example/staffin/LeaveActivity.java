package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.staffin.Adapter.LeaveAdapter;
import com.example.staffin.Adapter.MonthAdapter;
import com.example.staffin.Interface.ApiInterface;
import com.example.staffin.Response.EmployeeLeaveResult;
import com.example.staffin.Response.LeaveResponse;
import com.example.staffin.Retrofit.RetrofitServices;
import com.example.staffin.databinding.ActivityLeaveBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaveActivity extends AppCompatActivity {
    ActivityLeaveBinding binding;
    LeaveAdapter adapter;
    ApiInterface apiInterface;
    List<EmployeeLeaveResult> employeeLeaveResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLeaveBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.leaveRV.setVisibility(View.GONE);
        binding.nothingTv.setVisibility(View.VISIBLE);
        final ProgressDialog progressDialog = new ProgressDialog(LeaveActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        Call<LeaveResponse> leaveResponseCall = apiInterface.getAllEmployeeLeave();
        leaveResponseCall.enqueue(new Callback<LeaveResponse>() {
            @Override
            public void onResponse(Call<LeaveResponse> call, Response<LeaveResponse> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    employeeLeaveResult = response.body().getEmployeeLeaveResult();
                    binding.leaveRV.setLayoutManager(new LinearLayoutManager(LeaveActivity.this));
                    if (employeeLeaveResult.size() < 1) {
                        binding.leaveRV.setVisibility(View.GONE);
                        binding.nothingTv.setVisibility(View.VISIBLE);
                    } else {
                        adapter = new LeaveAdapter(LeaveActivity.this, employeeLeaveResult);
                        binding.leaveRV.setAdapter(adapter);
                        binding.nothingTv.setVisibility(View.GONE);
                        binding.leaveRV.setVisibility(View.VISIBLE);
                    }

                } else {
                    binding.leaveRV.setVisibility(View.GONE);
                    binding.nothingTv.setVisibility(View.VISIBLE);
                    progressDialog.dismiss();
                    Toast.makeText(LeaveActivity.this, "Some Error Occured", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LeaveResponse> call, Throwable t) {
                progressDialog.dismiss();
                binding.leaveRV.setVisibility(View.GONE);
                binding.nothingTv.setVisibility(View.VISIBLE);
                Toast.makeText(LeaveActivity.this, "Failure,Try Again", Toast.LENGTH_SHORT).show();

            }
        });

        binding.btnHome.setOnClickListener(v -> {
            finish();
        });


    }
}