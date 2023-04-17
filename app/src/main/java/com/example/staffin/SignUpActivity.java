package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.staffin.Interface.ApiInterface;
import com.example.staffin.Response.SignupResponse;
import com.example.staffin.Retrofit.RetrofitServices;
import com.example.staffin.databinding.ActivitySignUpBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class SignUpActivity extends AppCompatActivity {
    ActivitySignUpBinding binding;
    ApiInterface apiInterface;
    ProgressDialog progress;
    private static final String TAG = "SignUpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        progress = new ProgressDialog(SignUpActivity.this);
        progress.setMessage("please wait...");
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        binding.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.nameEt.getText().toString().isEmpty()) {
                    binding.nameEt.setError("Enter Your Name");
                    binding.nameEt.requestFocus();
                } else if (binding.phoneEt.getText().toString().trim().isEmpty()) {
                    binding.phoneEt.setError("Enter Mobile Number");
                    binding.phoneEt.requestFocus();
                } else if (binding.phoneEt.getText().toString().trim().length() < 10) {
                    binding.phoneEt.setError("Enter Correct Mobile Number");
                    binding.phoneEt.requestFocus();
                } else if (binding.passwordEt.getText().toString().trim().isEmpty()) {
                    binding.passwordEt.setError("Enter Password");
                    binding.passwordEt.requestFocus();
                } else if (binding.passwordEt.getText().toString().trim().length() < 4) {
                    binding.passwordEt.setError("Enter password min 4 words");
                    binding.passwordEt.requestFocus();
                } else if (binding.confirmPasswordEt.getText().toString().trim().isEmpty()) {
                    binding.confirmPasswordEt.setError("Confirm Password");
                    binding.confirmPasswordEt.requestFocus();
                } else if (!binding.confirmPasswordEt.getText().toString().trim().equals(binding.passwordEt.getText().toString().trim())) {
                    binding.confirmPasswordEt.setError("Both passwords are not identical ");
                    binding.confirmPasswordEt.requestFocus();
                } else {
                    progress.show();
                    String name = binding.nameEt.getText().toString();
                    String phone = binding.phoneEt.getText().toString();
                    String email = binding.emailEt.getText().toString();
                    String pw = binding.passwordEt.getText().toString();
                    Call<SignupResponse> callPostSignupResponse = apiInterface.postSignupResponse(name, email, phone, pw);
                    callPostSignupResponse.enqueue(new Callback<SignupResponse>() {
                        @Override
                        public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                            if (response.isSuccessful()) {
                                progress.dismiss();
                                Toast.makeText(SignUpActivity.this, "Manager Registered Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                finish();
                            } else {
                                progress.dismiss();
                                Log.d(TAG, "onResponse: " + response.message());
                                Toast.makeText(SignUpActivity.this, "some error occured", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<SignupResponse> call, Throwable t) {
                            progress.dismiss();
                            Log.d(TAG, "onResponse: " + t.getMessage());
                            Toast.makeText(SignUpActivity.this, "failure", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });
    }
}