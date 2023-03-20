package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.staffin.Fragment.PresentBottomSheetFragment;
import com.example.staffin.databinding.ActivityInsideAttendanceBinding;

public class InsideAttendanceActivity extends AppCompatActivity {
    ActivityInsideAttendanceBinding binding;
    String name,status,empId,dpImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsideAttendanceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        final ProgressDialog progressDialog = new ProgressDialog(InsideAttendanceActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        name=getIntent().getStringExtra("name");
        status=getIntent().getStringExtra("status");
        empId=getIntent().getStringExtra("empId");
        dpImg=getIntent().getStringExtra("dpImg");
        binding.nameTv.setText(name);
        binding.empId.setText("Emp. ID - " + empId);
        if(status.equalsIgnoreCase("absent"))
        {
            binding.indicator.setBackgroundResource(R.drawable.bg_red);
            binding.indicator.setText("Absent");
            binding.indicator.setTextColor(Color.WHITE);
        }
        else
        {
            binding.indicator.setBackgroundResource(R.drawable.bg_green);
            binding.indicator.setText("Present");
            binding.indicator.setTextColor(Color.WHITE);
        }

        binding.btnBack.setOnClickListener(v -> {
            finish();
        });
        binding.imageButton.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), CalendarSettingActivity.class));
        });

        binding.indicator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PresentBottomSheetFragment presentBottomSheetFragment = new PresentBottomSheetFragment();
                presentBottomSheetFragment.show(getSupportFragmentManager(), presentBottomSheetFragment.getTag());

            }
        });
        progressDialog.dismiss();
    }
}