package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.staffin.Interface.ApiInterface;
import com.example.staffin.Response.LoginResponse;
import com.example.staffin.Retrofit.RetrofitServices;
import com.example.staffin.databinding.ActivityForgotBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotActivity extends AppCompatActivity {
    ActivityForgotBinding binding;
    ApiInterface apiInterface;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgotBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        progressDialog = new ProgressDialog(ForgotActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("please wait...");

        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);

        binding.otpBtn.setOnClickListener(v -> {
            if (binding.numberEt.getText().toString().trim().isEmpty() || !binding.numberEt.getText().toString().contains("@") || !binding.numberEt.getText().toString().contains(".")) {
                binding.numberEt.setError("Enter Email address properly");
                binding.numberEt.requestFocus();
            } else {
                progressDialog.show();
                Call<LoginResponse> call = apiInterface.hrForgotPassword(binding.numberEt.getText().toString().trim());
                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful()) {
                            progressDialog.dismiss();
                            startActivity(new Intent(getApplicationContext(), VerificationActivity.class));
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(ForgotActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                            Log.d("dgkdfgdfgs", response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(ForgotActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("dgkdfgdfgs", t.getMessage());
                    }
                });

            }
        });

    }
}