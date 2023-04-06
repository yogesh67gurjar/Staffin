package com.example.staffin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.staffin.Adapter.PayRollAdapter;
import com.example.staffin.databinding.ActivityPayrollBinding;

public class PayrollActivity extends AppCompatActivity {

    ActivityPayrollBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPayrollBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.PayRollRv.setVisibility(View.GONE);
        binding.shimmerViewContainer.setVisibility(View.VISIBLE);
        binding.shimmerViewContainer.startShimmer();


        binding.PayRollRv.setLayoutManager(new LinearLayoutManager(this));
        binding.PayRollRv.setAdapter(new PayRollAdapter(PayrollActivity.this));

        binding.shimmerViewContainer.stopShimmer();
        binding.shimmerViewContainer.setVisibility(View.GONE);
        binding.PayRollRv.setVisibility(View.VISIBLE);
        binding.btnExpanses.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), ExpansesActivity.class));
        });
        binding.btnHome.setOnClickListener(v -> {
            finish();
        });

        binding.nextBtn.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), InsidePayrollActivity.class));
        });

    }
}