package com.example.staffin;

import androidx.annotation.NonNull;
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
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private static long backPressed;
    private static final int TIME_DELAY = 2000;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);

        sharedPreferences = getSharedPreferences("staffin", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        binding.card1.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), TotalEmployeeActivity.class);
//            startActivity(new Intent(getApplicationContext(), TotalEmployeeActivity.class));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
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

                    if (sharedPreferences.contains("firebase")) {
                        FirebaseAuth.getInstance().signOut();
                        googleSignInClient.signOut().addOnCompleteListener(MainActivity.this,
                                new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        editor.remove("firebase");
                                    }
                                });
                    }


                    editor.remove("login");

                    editor.apply();
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));

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