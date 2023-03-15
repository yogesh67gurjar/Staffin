package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.example.staffin.Adapter.OnBoardingAdapter;
import com.example.staffin.databinding.ActivityOnBordingBinding;

public class OnBoardingActivity extends AppCompatActivity {
    ActivityOnBordingBinding binding;
    OnBoardingAdapter adapter;
    TextView[] dots;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnBordingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter = new OnBoardingAdapter(this);
        binding.viewPager.setAdapter(adapter);
        setDotIndicator(0);
        binding.viewPager.addOnPageChangeListener(viewPagerListener);

        binding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getItem(0) < 2) {
                    binding.viewPager.setCurrentItem(getItem(1), true);
                } else {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
    public void setDotIndicator(int position) {
        dots = new TextView[3];
        binding.dotIndicatorLinearLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                dots[i].setText(Html.fromHtml("&#8211", Html.FROM_HTML_MODE_LEGACY));
                dots[i].setTextSize(35);
                dots[i].setTextColor(Color.parseColor("#808080"));
                binding.dotIndicatorLinearLayout.addView(dots[i]);
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            dots[position].setText(Html.fromHtml("&#8212", Html.FROM_HTML_MODE_LEGACY));
        }
        dots[position].setTextColor(Color.parseColor("#4766F9"));
    }
    private int getItem(int i) {
        return binding.viewPager.getCurrentItem() + i;
    }

    ViewPager.OnPageChangeListener viewPagerListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            // calling setDotIndicator and passing position jaha pe active dot ko set krna he
            setDotIndicator(position);

        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }

    };
}