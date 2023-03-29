package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.staffin.databinding.ActivityMainBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private static long backPressed;
    private static final int TIME_DELAY = 2000;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sharedPreferences = getSharedPreferences("staffin", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        binding.card1.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), TotalEmployeeActivity.class));
        });
        binding.card2.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), AttendanceActivity.class));
        });
        binding.card3.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), EventActivity.class));
        });
        binding.card4.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), PayrollActivity.class));
        });
        binding.card5.setOnClickListener(v -> {
            Toast.makeText(this, "Calendar Activity", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getApplicationContext(), CalendarSettingActivity.class);
            intent.putExtra("from", "mainactivity");
            startActivity(intent);
        });
        binding.card6.setOnClickListener(v -> {
//            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
//            editor.remove("mobile");
//            editor.apply();
//            finish();








        });
    }

    @Override
    public void onBackPressed() {
        if (backPressed + TIME_DELAY > System.currentTimeMillis()) {

            finishAffinity();

        } else {
            Toast.makeText(this, "Press once again to exit!", Toast.LENGTH_SHORT).show();
        }
        backPressed = System.currentTimeMillis();
    }

}