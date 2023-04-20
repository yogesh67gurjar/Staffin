package com.example.staffin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.staffin.Adapter.PayRollAdapter;
import com.example.staffin.Interface.ApiInterface;
import com.example.staffin.Response.AllPayroll;
import com.example.staffin.Response.PayslipDetail;
import com.example.staffin.Retrofit.RetrofitServices;
import com.example.staffin.databinding.ActivityPayrollBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PayrollActivity extends AppCompatActivity {

    ActivityPayrollBinding binding;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPayrollBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        Call<AllPayroll> callGetAllPayroll = apiInterface.getAllPayroll();
        binding.PayRollRv.setVisibility(View.GONE);
        binding.shimmerViewContainer.setVisibility(View.VISIBLE);
        binding.shimmerViewContainer.startShimmer();
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

                } else {

                }

            }

            @Override
            public void onFailure(Call<AllPayroll> call, Throwable t) {

            }
        });


        binding.btnExpanses.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), ExpansesActivity.class));
        });
        binding.btnHome.setOnClickListener(v -> {
            finish();
        });

        binding.nextBtn.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), InsidePayrollActivity.class));
        });

    }
}