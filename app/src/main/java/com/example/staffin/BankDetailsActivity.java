package com.example.staffin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.staffin.Interface.ApiInterface;
import com.example.staffin.Response.BankDetailsResponse;
import com.example.staffin.Retrofit.RetrofitServices;
import com.example.staffin.databinding.ActivityBankDetailsBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BankDetailsActivity extends AppCompatActivity {
    ActivityBankDetailsBinding binding;

    String from;
    int Id;
    String empId;
    ApiInterface apiInterface;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBankDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        clickListeners();
        from = getIntent().getStringExtra("from");
        empId = getIntent().getStringExtra("empId");
        Id = getIntent().getIntExtra("Id", 0);

//        if (from.equalsIgnoreCase("edit")) {
//            Call<SingleEmployeeResponse> call = apiInterface.getSingleEmployee(Id);
//            call.enqueue(new Callback<SingleEmployeeResponse>() {
//                @Override
//                public void onResponse(Call<SingleEmployeeResponse> call, Response<SingleEmployeeResponse> response) {
//
//                }
//
//                @Override
//                public void onFailure(Call<SingleEmployeeResponse> call, Throwable t) {
//
//                }
//            });
//        }


    }

    private void clickListeners() {
        progress = new ProgressDialog(BankDetailsActivity.this);
        progress.setMessage("please wait....");
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        binding.btnBack.setOnClickListener(v -> {
            onBackPressed();
        });

        binding.nextBtn.setOnClickListener(v -> {
            String branch = null;
            if (!binding.ifscEt.getText().toString().trim().isEmpty()) {
                branch = binding.ifscEt.getText().toString();
            }

            if (binding.holderEt.getText().toString().isEmpty()) {
                binding.holderEt.setError("Enter Holder Name");
                binding.holderEt.requestFocus();
            } else if (binding.accNoEt.getText().toString().trim().isEmpty()) {
                binding.accNoEt.setError("Enter Account Number");
                binding.accNoEt.requestFocus();
            } else if (binding.bankEt.getText().toString().trim().isEmpty()) {
                binding.bankEt.setError("Enter Bank Name");
                binding.bankEt.requestFocus();
            } else {
                if (isNetworkAvailable()) {
                    if (from.equalsIgnoreCase("add")) {
                        progress.show();
                        Call<BankDetailsResponse> callPostSingleBankDetails = apiInterface.postSingleBankDetails(Id, binding.holderEt.getText().toString(), binding.accNoEt.getText().toString(), binding.bankEt.getText().toString(), branch);
                        callPostSingleBankDetails.enqueue(new Callback<BankDetailsResponse>() {
                            @Override
                            public void onResponse(Call<BankDetailsResponse> call, Response<BankDetailsResponse> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(BankDetailsActivity.this, "New Employee Added...", Toast.LENGTH_SHORT).show();
                                    progress.dismiss();
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                    finish();
                                } else {
                                    progress.dismiss();
                                    Log.d("nfsdf", response.message());
                                    Toast.makeText(BankDetailsActivity.this, "error", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<BankDetailsResponse> call, Throwable t) {
                                Log.d("jnkdfn", t.getMessage());
                                progress.dismiss();
                                Toast.makeText(BankDetailsActivity.this, "some failure occured", Toast.LENGTH_SHORT).show();
                            }
                        });


                    } else {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                        Toast.makeText(this, "Employee Details Updated...", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Internet Not Available", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            super.onKeyDown(keyCode, event);
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Can't Go Back", Toast.LENGTH_SHORT).show();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}