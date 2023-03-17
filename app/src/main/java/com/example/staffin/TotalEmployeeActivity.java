package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.staffin.Adapter.TotalEmployeeAdapter;
import com.example.staffin.databinding.ActivityTotalEmployeeBinding;

import java.util.ArrayList;
import java.util.List;

public class TotalEmployeeActivity extends AppCompatActivity {

    ActivityTotalEmployeeBinding binding;
    List<String> employeesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTotalEmployeeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        employeesList=new ArrayList<>();

        employeesList.add("ashok sir");
        employeesList.add("shubham raikwar");
        employeesList.add("shubham sharma");
        employeesList.add("pragati sharma");
        employeesList.add("yogesh gurjar");
        employeesList.add("madhur sir");
        employeesList.add("sakshi naidu");
        employeesList.add("shubhi gupta");

        binding.imgBtnAddEmployee.setOnClickListener(v -> {
            Intent addIntent=new Intent(TotalEmployeeActivity.this,AddEmployeeActivity.class);
            addIntent.putExtra("from","add");
            startActivity(addIntent);
        });

        binding.totalEmployeeRv.setLayoutManager(new LinearLayoutManager(this));
        binding.totalEmployeeRv.setAdapter(new TotalEmployeeAdapter(employeesList,TotalEmployeeActivity.this));

        binding.btnBack.setOnClickListener(v -> {
            finish();
        });
    }
}