package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.staffin.Interface.ApiInterface;
import com.example.staffin.Response.LoginResponse;
import com.example.staffin.Retrofit.RetrofitServices;
import com.example.staffin.databinding.ActivityLoginBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    ApiInterface apiInterface;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);

        sharedPreferences = getSharedPreferences("staffin", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if (sharedPreferences.getAll().containsKey("mobile")) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.phoneEt.getText().toString().trim().isEmpty()) {
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
                } else {

                    String number = binding.phoneEt.getText().toString();
                    String password = binding.passwordEt.getText().toString();

//                    Call<LoginResponse> call = apiInterface.postLoginResponse(number, password);
//                    call.enqueue(new Callback<LoginResponse>() {
//                        @Override
//                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
//                            if (response.isSuccessful()) {
//                                Toast.makeText(LoginActivity.this, "Welcome...", Toast.LENGTH_SHORT).show();
//                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                                finish();
//                            } else {
//                                Toast.makeText(LoginActivity.this, "Response Error", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<LoginResponse> call, Throwable t) {
//                           // Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                            Log.d("Message karo", t.getMessage());
//                          //  Toast.makeText(LoginActivity.this, "Enter Correct Details ", Toast.LENGTH_SHORT).show();
//                        }
//                    });
                    Call<LoginResponse> call = apiInterface.postLoginResponse(number, password);
                    call.enqueue(new Callback<>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Welcome...", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                editor.putString("mobile", number);
                                editor.apply();
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, "Please Enter Correct Details", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            // Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d("Message karo", t.getMessage());
                              Toast.makeText(LoginActivity.this, "Enter Correct Details", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

    }
}