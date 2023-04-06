package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.staffin.Adapter.ExpansesAdapter;
import com.example.staffin.databinding.ActivityExpansesBinding;

public class ExpansesActivity extends AppCompatActivity {
    ActivityExpansesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExpansesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setOnClickListener();

        binding.expansesRv.setLayoutManager(new LinearLayoutManager(this));
        binding.expansesRv.setAdapter(new ExpansesAdapter(ExpansesActivity.this));

    }

    private void setOnClickListener() {

        binding.btnBack.setOnClickListener(v -> {
            finish();
        });
    }
}