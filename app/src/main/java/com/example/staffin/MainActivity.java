package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.ScaleAnimation;
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
            Intent intent = new Intent(getApplicationContext(), CalendarSettingActivity.class);
            intent.putExtra("from", "mainactivity");
            startActivity(intent);
        });
        binding.card7.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), LeaveActivity.class));
        });
        binding.card6.setOnClickListener(v -> {

            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Logout");
            dialog.setCancelable(false);
            dialog.setMessage("Are you sure");


            dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    editor.remove("mobile");
                    editor.apply();
                    finish();

                }
            });
            dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog alertDialog = dialog.create();
            alertDialog.show();
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