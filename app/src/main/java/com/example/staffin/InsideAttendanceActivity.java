package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.staffin.Fragment.PresentBottomSheetFragment;
import com.example.staffin.databinding.ActivityInsideAttendanceBinding;

public class InsideAttendanceActivity extends AppCompatActivity {
    ActivityInsideAttendanceBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsideAttendanceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PresentBottomSheetFragment presentBottomSheetFragment = new PresentBottomSheetFragment();
                presentBottomSheetFragment.show(getSupportFragmentManager(), presentBottomSheetFragment.getTag());

            }
        });

    }
}