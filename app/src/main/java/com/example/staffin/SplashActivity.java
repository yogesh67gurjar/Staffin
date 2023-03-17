package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.example.staffin.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {
    ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        new CountDownTimer(3000, 1000) {

            public void onTick(long millisUntilFinished) {
//                binding.splashLogo2.animate().alpha(0).setDuration(1500);
            }

            public void onFinish() {
                startActivity(new Intent(getApplicationContext(), OnBoardingActivity.class));
                finish();
//                mTextField.setText("done!");
            }
        }.start();

    }
}