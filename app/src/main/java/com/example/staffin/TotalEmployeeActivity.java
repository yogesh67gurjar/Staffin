package com.example.staffin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import android.content.res.Configuration;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.staffin.Adapter.TotalEmployeeAdapter;
import com.example.staffin.Interface.ApiInterface;
import com.example.staffin.Response.EmployeeResult;
import com.example.staffin.Response.TotalEmployeeResponse;
import com.example.staffin.Retrofit.RetrofitServices;
import com.example.staffin.databinding.ActivityTotalEmployeeBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TotalEmployeeActivity extends AppCompatActivity {

    ActivityTotalEmployeeBinding binding;
//    List<String> employeesList;

    List<EmployeeResult> employeeResultList;

    TotalEmployeeAdapter adapter;

    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTotalEmployeeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        employeeResultList = new ArrayList<>();
        adapter = new TotalEmployeeAdapter(TotalEmployeeActivity.this, employeeResultList);

        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);


        final ProgressDialog progressDialog = new ProgressDialog(TotalEmployeeActivity.this);
        progressDialog.setMessage("Loading...");


        binding.totalEmployeeRv.setVisibility(View.GONE);
        binding.fbShimmer.setVisibility(View.VISIBLE);
        binding.fbShimmer.startShimmer();
//        progressDialog.show();

//        employeesList=new ArrayList<>();
//
//        employeesList.add("ashok sir");
//        employeesList.add("shubham raikwar");
//        employeesList.add("shubham sharma");
//        employeesList.add("pragati sharma");
//        employeesList.add("yogesh gurjar");
//        employeesList.add("sakshi naidu");
//        employeesList.add("madhur sir");
//        employeesList.add("shubhi gupta");


        binding.totalEmployeeRv.setLayoutManager(new LinearLayoutManager(this));


        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);


        if (isNetworkAvailable()) {
            Call<TotalEmployeeResponse> call = apiInterface.getTotalEmployee();
            call.enqueue(new Callback<TotalEmployeeResponse>() {
                @Override
                public void onResponse(Call<TotalEmployeeResponse> call, Response<TotalEmployeeResponse> response) {
                    if (response.isSuccessful()) {
                        binding.fbShimmer.stopShimmer();
                        binding.fbShimmer.setVisibility(View.GONE);
                        binding.totalEmployeeRv.setVisibility(View.VISIBLE);
                        employeeResultList = response.body().getEmployeeResult();
                        adapter = new TotalEmployeeAdapter(TotalEmployeeActivity.this, employeeResultList);
                        binding.totalEmployeeRv.setAdapter(adapter);

                    } else {
                        Log.d("ndfnsdf", response.message());
                        Toast.makeText(TotalEmployeeActivity.this, "Some Error Occured", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<TotalEmployeeResponse> call, Throwable t) {
                    Toast.makeText(TotalEmployeeActivity.this, "Failure,Try Again", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(TotalEmployeeActivity.this, "Internet Not Available", Toast.LENGTH_SHORT).show();
        }

        binding.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CalendarSettingActivity.class));
            }
        });

        binding.imgBtnAddEmployee.setOnClickListener(v -> {
            Intent addIntent = new Intent(TotalEmployeeActivity.this, AddEmployeeActivity.class);
            addIntent.putExtra("from", "add");
            startActivity(addIntent);
        });

        binding.searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.btnBack.setOnClickListener(v -> {
            finish();
        });
    }


    void filter(String text) {
        List<EmployeeResult> filteredList = new ArrayList();
        for (EmployeeResult d : employeeResultList) {
            if (d.getFullName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(d);
            }
        }
        //update recyclerview
        adapter.filterList(filteredList);
    }

    @Override
    public void onConfigurationChanged(@NotNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            startActivity(new Intent(getApplicationContext(), TotalEmployeeActivity.class));
            finish();
//            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            startActivity(new Intent(getApplicationContext(), TotalEmployeeActivity.class));
            finish();
//            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}