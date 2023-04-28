package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.staffin.Interface.ApiInterface;
import com.example.staffin.Response.PaySlipResponse;
import com.example.staffin.Response.PayslipDetail;
import com.example.staffin.Retrofit.RetrofitServices;
import com.example.staffin.databinding.ActivityPaySlipBinding;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaySlipActivity extends AppCompatActivity {
    ActivityPaySlipBinding binding;

    ApiInterface apiInterface;
    int Id = 0;
    int IdI = 0;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaySlipBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPreferences = getApplicationContext().getSharedPreferences("staffin", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        Id = getIntent().getIntExtra("Id", 0);
        IdI = getIntent().getIntExtra("IdI", 0);
        Log.i("Id AArahi AHI", String.valueOf(Id));
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        setOnClickListener();
        if (isNetworkAvailable()) {
            getApi();
        } else {
            binding.notFoundLayout.setVisibility(View.GONE);
            binding.nestedScrollFirst.setVisibility(View.GONE);
            Toast.makeText(this, "Please Check Your Network...", Toast.LENGTH_SHORT).show();
        }

    }

    private void getApi() {
        final ProgressDialog progressDialog = new ProgressDialog(PaySlipActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        Call<PaySlipResponse> paySlipResponseCall = apiInterface.getPaySlip(Integer.parseInt(String.valueOf(IdI)));
        paySlipResponseCall.enqueue(new Callback<PaySlipResponse>() {
            @Override
            public void onResponse(Call<PaySlipResponse> call, Response<PaySlipResponse> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    if (response.body().getPayslipDetails().size() == 0) {

                        binding.notFoundLayout.setVisibility(View.VISIBLE);
                        binding.nestedScrollFirst.setVisibility(View.GONE);

                    } else {
                        binding.nestedScrollFirst.setVisibility(View.VISIBLE);
                        binding.notFoundLayout.setVisibility(View.GONE);
                        PayslipDetail singleUnit = response.body().getPayslipDetails().get(0);
                        Glide.with(getApplicationContext()).load(singleUnit.getEmployeeId().get(0).getProfileImageUrl()).placeholder(R.drawable.img_dp).into(binding.dpImg);
                        binding.nameTv.setText(singleUnit.getEmployeeId().get(0).getFullName());
                        binding.indicator.setText(singleUnit.getStatus());
                        binding.basicAmount.setText(singleUnit.getBasic());
                        binding.hourlyAmount.setText(singleUnit.getOvertimeHours());
                        binding.expenseAmount.setText(singleUnit.getExpense());
                        if (singleUnit.getBonus() == null) {
                            binding.bounceAmount.setText("0");
                        } else {
                            binding.bounceAmount.setText(singleUnit.getBonus());
                        }

                        binding.deductionAmount.setText(singleUnit.getTotalDeduction());
                        binding.netAmount.setText(singleUnit.getNetSalary());
                        binding.empId.setText("Emp. ID - " + singleUnit.getEmployeeId().get(0).getEmployeeID());
                        binding.txt1.setText("Month:-" + singleUnit.getMonth());
                        binding.txt2.setText("Year:-" + singleUnit.getYear());
                        Log.e("data DEkho to", response.message());
                    }


                } else {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Try Again", Toast.LENGTH_SHORT).show();
                    Log.e("Try Again Karo", response.message());
                }
            }

            @Override
            public void onFailure(Call<PaySlipResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Network Error", Toast.LENGTH_SHORT).show();
                Log.e("Api not Working", t.getMessage());


            }
        });
    }

    private void setOnClickListener() {
        binding.btnHome.setOnClickListener(v -> {
            finish();
        });
        binding.btnShare.setOnClickListener(v -> {

            // Get the root view of the shopping list activity
            View rootView = findViewById(android.R.id.content);
            // Get a bitmap of the root view
            rootView.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(rootView.getDrawingCache());
            rootView.setDrawingCacheEnabled(false);
            // Save the bitmap to a file
            File cacheDir = getCacheDir();
            File file = new File(cacheDir, "payslip.png");
            FileOutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            try {
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Create a share intent with the image file
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("image/png");
            Uri uri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".file-provider", file);
            shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
            // Launch the share intent
            startActivity(Intent.createChooser(shareIntent, "Share PaySlip list"));

        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}