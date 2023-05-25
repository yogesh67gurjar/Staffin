package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.staffin.Interface.ApiInterface;
import com.example.staffin.Response.Example;
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
                Call<Example> call = apiInterface.hrForgotPassword(binding.numberEt.getText().toString().trim());
                call.enqueue(new Callback<Example>() {
                    @Override
                    public void onResponse(Call<Example> call, Response<Example> response) {
                        if (response.isSuccessful()) {
                            progressDialog.dismiss();
                            if (!response.body().getMessage().contains("Email sent")) {
                                Toast.makeText(ForgotActivity.this, "Email not found in our database , enter valid email", Toast.LENGTH_SHORT).show();
                            } else {

                                Intent i = new Intent(getApplicationContext(), VerificationActivity.class);
                                i.putExtra("email", binding.numberEt.getText().toString());
                                startActivity(i);
                            }

                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(ForgotActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                            Log.e("dgkdfgdfgs", response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<Example> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(ForgotActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("dgkdfdgsdfgdfgs", t.getMessage());
                    }
                });

            }
        });

    }
}