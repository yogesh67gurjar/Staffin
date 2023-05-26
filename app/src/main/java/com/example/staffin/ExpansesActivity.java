package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.staffin.Adapter.ExpansesAdapter;
import com.example.staffin.Interface.ApiInterface;
import com.example.staffin.Response.AllExpenses;
import com.example.staffin.Retrofit.RetrofitServices;
import com.example.staffin.databinding.ActivityExpansesBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExpansesActivity extends AppCompatActivity {
    ActivityExpansesBinding binding;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExpansesBinding.inflate(getLayoutInflater());
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        setContentView(binding.getRoot());
        if (isNetworkAvailable()) {
            getApi();
            setOnClickListener();
        } else {
            Toast.makeText(this, "Please Check Your Network...", Toast.LENGTH_SHORT).show();
        }

    }

    private void getApi() {
        final ProgressDialog progressDialog = new ProgressDialog(ExpansesActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        Call<AllExpenses> allExpensesCall = apiInterface.getAllExpenses();
        allExpensesCall.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<AllExpenses> call, Response<AllExpenses> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    List<AllExpenses.GetAllExpenseDetail> resp = response.body().getGetAllExpenseDetails();
                    binding.expansesRv.setLayoutManager(new LinearLayoutManager(ExpansesActivity.this));
                    binding.expansesRv.setAdapter(new ExpansesAdapter(ExpansesActivity.this, resp));
                    Log.d("onResponse: ", response.message());

                } else {
                    progressDialog.dismiss();
                    Log.d("onResponseElse:", response.message());
                    Toast.makeText(ExpansesActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AllExpenses> call, Throwable t) {
                progressDialog.dismiss();
                Log.d("onFailure: ", t.getMessage());
                Toast.makeText(ExpansesActivity.this, "Server Problem", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setOnClickListener() {

        binding.btnBack.setOnClickListener(v -> {
            finish();
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}