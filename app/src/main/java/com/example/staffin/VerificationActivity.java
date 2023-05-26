package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Toast;

import com.example.staffin.Interface.ApiInterface;
import com.example.staffin.Response.Example;
import com.example.staffin.Response.LoginResponse;
import com.example.staffin.Retrofit.RetrofitServices;
import com.example.staffin.databinding.ActivityVerificationBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerificationActivity extends AppCompatActivity {
    ActivityVerificationBinding binding;
    ApiInterface apiInterface;

    ProgressDialog progressDialog;

    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        email = getIntent().getExtras().getString("email");
//        MoveNumToNext();

        progressDialog = new ProgressDialog(VerificationActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("please wait...");

        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);

        binding.confirmBtn.setOnClickListener(v -> {

            if (binding.numberEt.getText().toString().toString().isEmpty()) {
                binding.numberEt.setError("please enter token");
            } else {
                Call<LoginResponse> call = apiInterface.verifyTokenForgot(binding.numberEt.getText().toString().trim());
                progressDialog.show();
                call.enqueue(new Callback<>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful()) {
                            progressDialog.dismiss();
                            if (response.body().getMessage().contains("nvalid")) {
                                Toast.makeText(VerificationActivity.this, "invalid token", Toast.LENGTH_SHORT).show();
                            } else {

                                Call<LoginResponse> call2 = apiInterface.getResetGet(binding.numberEt.getText().toString().trim());
                                progressDialog.show();
                                call2.enqueue(new Callback<LoginResponse>() {
                                    @Override
                                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                                        if (response.isSuccessful()) {
                                            progressDialog.dismiss();
                                            Intent intent = new Intent(getApplicationContext(), ConfirmPasswordActivity.class);
                                            intent.putExtra("token", binding.numberEt.getText().toString().trim());
                                            intent.putExtra("email", email);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            progressDialog.dismiss();
                                            Toast.makeText(VerificationActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                                            Log.e("dgkdfgdfgs", response.message());
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                                        progressDialog.dismiss();
                                        Toast.makeText(VerificationActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                                        Log.e("dgkdfdgsdfgdfgs", t.getMessage());
                                    }
                                });


                            }

                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(VerificationActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                            Log.e("dgkdfgdfgs", response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(VerificationActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("dgkdfdgsdfgdfgs", t.getMessage());
                    }
                });


            }
//
//
//            if (binding.otp1.getText().toString().trim().isEmpty()) {
//                Toast.makeText(this, "Enter Otp", Toast.LENGTH_SHORT).show();
//
//            } else if (binding.otp2.getText().toString().trim().isEmpty()) {
//                Toast.makeText(this, "Enter Otp", Toast.LENGTH_SHORT).show();
//
//            } else if (binding.otp3.getText().toString().trim().isEmpty()) {
//                Toast.makeText(this, "Enter Otp", Toast.LENGTH_SHORT).show();
//
//            } else if (binding.otp4.getText().toString().trim().isEmpty()) {
//                Toast.makeText(this, "Enter Otp", Toast.LENGTH_SHORT).show();
//
//            } else {
//
//                startActivity(new Intent(getApplicationContext(), ConfirmPasswordActivity.class));
//                finish();
//            }
        });

    }

//    private void MoveNumToNext() {
//
//        binding.otp1.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if (!charSequence.toString().trim().isEmpty()) {
//                    binding.otp2.requestFocus();
//                }
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
//        binding.otp2.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if (!charSequence.toString().trim().isEmpty()) {
//                    binding.otp3.requestFocus();
//                }
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
//        binding.otp3.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if (!charSequence.toString().trim().isEmpty()) {
//                    binding.otp4.requestFocus();
//                }
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
//
//
//    }
}