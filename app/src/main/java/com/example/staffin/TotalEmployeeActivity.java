package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.staffin.Adapter.TotalEmployeeAdapter;
import com.example.staffin.Interface.ApiInterface;
import com.example.staffin.Response.EmployeeResult;
import com.example.staffin.Response.TotalEmployeeResponse;
import com.example.staffin.Retrofit.RetrofitServices;
import com.example.staffin.databinding.ActivityTotalEmployeeBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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


        final ProgressDialog progressDialog = new ProgressDialog(TotalEmployeeActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

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
        Call<TotalEmployeeResponse> call = apiInterface.getTotalEmployee();
        call.enqueue(new Callback<TotalEmployeeResponse>() {
            @Override
            public void onResponse(Call<TotalEmployeeResponse> call, Response<TotalEmployeeResponse> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    employeeResultList = response.body().getEmployeeResult();
                    adapter = new TotalEmployeeAdapter(TotalEmployeeActivity.this, employeeResultList);
                    binding.totalEmployeeRv.setAdapter(adapter);

                } else {
                    Toast.makeText(TotalEmployeeActivity.this, "Find Some Error", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<TotalEmployeeResponse> call, Throwable t) {
                Toast.makeText(TotalEmployeeActivity.this, "Failure,Try Again", Toast.LENGTH_SHORT).show();

            }
        });


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

}