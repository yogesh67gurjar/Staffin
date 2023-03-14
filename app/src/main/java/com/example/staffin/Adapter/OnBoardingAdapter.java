package com.example.staffin.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.staffin.R;

public class OnBoardingAdapter extends PagerAdapter {
    Context context;

    public OnBoardingAdapter(Context context) {
        this.context = context;
    }

    int sliderAllImages[] = {R.drawable.onboarding_one, R.drawable.onboarding_two, R.drawable.onboarding_third};
    int sliderAllTitles[] = {R.string.onBoardingTxt1, R.string.onBoardingTxt1, R.string.onBoardingTxt1};
    int sliderAllDesc[] = {R.string.OnBoardingTxt2, R.string.OnBoardingTxt2, R.string.OnBoardingTxt2};
    int sliderAllDesc2[] = {R.string.OnBoardingTxt3, R.string.OnBoardingTxt3, R.string.OnBoardingTxt3};


    @Override
    public int getCount() {
        return sliderAllImages.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_screen, container, false);

        ImageView sliderImage = (ImageView) view.findViewById(R.id.sliderImage);
        TextView sliderTitle = (TextView) view.findViewById(R.id.tv1);
        TextView sliderDesc = (TextView) view.findViewById(R.id.tv2);
        TextView sliderDesc2 = (TextView) view.findViewById(R.id.tv3);

        sliderImage.setImageResource(sliderAllImages[position]);
        sliderTitle.setText(this.sliderAllTitles[position]);
        sliderDesc.setText(this.sliderAllDesc[position]);
        sliderDesc2.setText(this.sliderAllDesc2[position]);

        container.addView(view);

        return view;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }
}
