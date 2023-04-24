package com.example.staffin;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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
        binding.PayRollRv.setVisibility(View.GONE);
        binding.shimmerViewContainer.setVisibility(View.VISIBLE);
        binding.shimmerViewContainer.startShimmer();
        if (isNetworkAvailable()) {
            getApi();
            setOnClickListener();

        } else {
            Toast.makeText(this, "Please Check Your Network...", Toast.LENGTH_SHORT).show();
        }


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