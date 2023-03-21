package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.staffin.Adapter.TotalEmployeeAdapter;
import com.example.staffin.databinding.ActivityTotalEmployeeBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TotalEmployeeActivity extends AppCompatActivity {

    ActivityTotalEmployeeBinding binding;
    List<String> employeesList;

    TotalEmployeeAdapter adapter;

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
        employeesList.add("sakshi naidu");
        employeesList.add("madhur sir");
        employeesList.add("shubhi gupta");

        adapter=new TotalEmployeeAdapter(employeesList,TotalEmployeeActivity.this);
        binding.totalEmployeeRv.setLayoutManager(new LinearLayoutManager(this));
        binding.totalEmployeeRv.setAdapter(adapter);

        binding.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CalendarSettingActivity.class));
            }
        });

        binding.imgBtnAddEmployee.setOnClickListener(v -> {
            Intent addIntent=new Intent(TotalEmployeeActivity.this,AddEmployeeActivity.class);
            addIntent.putExtra("from","add");
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

    void filter(String text){
        List<String> filteredList = new ArrayList();
        for(String d: employeesList){
            if(d.toLowerCase().contains(text.toLowerCase()))
            {
                filteredList.add(d);
            }
        }
        //update recyclerview
        adapter.filterList(filteredList);
    }

}