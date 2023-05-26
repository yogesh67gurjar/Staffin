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
import com.example.staffin.databinding.ActivityConfirmPasswordBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmPasswordActivity extends AppCompatActivity {
    ActivityConfirmPasswordBinding binding;
    ApiInterface apiInterface;

    ProgressDialog progressDialog;
    String token, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConfirmPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        token = getIntent().getExtras().getString("token");
        email = getIntent().getExtras().getString("email");
        progressDialog = new ProgressDialog(ConfirmPasswordActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("please wait...");

        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);

        binding.confirmBtn.setOnClickListener(v -> {

            if (binding.newPasswordEt.getText().toString().trim().isEmpty()) {
                binding.newPasswordEt.setError("Enter New Password");
                binding.newPasswordEt.requestFocus();
            } else if (binding.newPasswordEt.getText().toString().trim().length() < 4) {
                binding.newPasswordEt.setError("Enter password min 4 words");
                binding.newPasswordEt.requestFocus();
            } else if (binding.confirmPasswordEt.getText().toString().trim().isEmpty()) {
                binding.confirmPasswordEt.setError("Confirm Password");
                binding.confirmPasswordEt.requestFocus();
            } else if (!binding.confirmPasswordEt.getText().toString().trim().equals(binding.newPasswordEt.getText().toString().trim())) {
                binding.confirmPasswordEt.setError("Both passwords are not identical ");
                binding.confirmPasswordEt.requestFocus();
            } else {

                Call<LoginResponse> call = apiInterface.newPassword(binding.newPasswordEt.getText().toString().trim(), email);
                progressDialog.show();
                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getMessage().contains("successfull")) {
                                progressDialog.dismiss();
                                Toast.makeText(ConfirmPasswordActivity.this, "Password Updated Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                finish();
                            } else {
                                Log.d("sdfasdg", response.message());
                                Toast.makeText(ConfirmPasswordActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }

                        } else {
                            Log.d("sdfasdg", response.message());
                            Toast.makeText(ConfirmPasswordActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        progressDialog.dismiss();
                        Log.d("sdfasdg", t.getMessage());
                        Toast.makeText(ConfirmPasswordActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("dgkdfdgsdfgdfgs", t.getMessage());
                    }
                });


            }
        });

    }
}