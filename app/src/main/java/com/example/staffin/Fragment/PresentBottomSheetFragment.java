package com.example.staffin.Fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.staffin.R;
import com.example.staffin.databinding.FragmentPresentBottomSheetBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class PresentBottomSheetFragment extends BottomSheetDialogFragment {
    FragmentPresentBottomSheetBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPresentBottomSheetBinding.inflate(inflater, container, false);

        String date = this.getArguments().getString("Date");

        binding.txt1.setText(date);

        binding.txtPresent.setOnClickListener(v -> {
            binding.txtPresent.setBackgroundResource(R.drawable.bg_green);
            binding.txtPresent.setTextColor(getResources().getColor(R.color.white));
            binding.txtDoublePresent.setBackgroundResource(R.drawable.bg_green_corner);
            binding.txtDoublePresent.setTextColor(getResources().getColor(R.color.txtGreen));
            binding.txtAbsent.setBackgroundResource(R.drawable.bg_red_corner);
            binding.txtAbsent.setTextColor(getResources().getColor(R.color.txtRed));
            binding.txtHalfDay.setBackgroundResource(R.drawable.bg_orange_corner);
            binding.txtHalfDay.setTextColor(getResources().getColor(R.color.txtOrange));
        });
        binding.txtDoublePresent.setOnClickListener(v -> {
            binding.txtPresent.setBackgroundResource(R.drawable.bg_green_corner);
            binding.txtPresent.setTextColor(getResources().getColor(R.color.txtGreen));
            binding.txtDoublePresent.setBackgroundResource(R.drawable.bg_green);
            binding.txtDoublePresent.setTextColor(getResources().getColor(R.color.white));
            binding.txtAbsent.setBackgroundResource(R.drawable.bg_red_corner);
            binding.txtAbsent.setTextColor(getResources().getColor(R.color.txtRed));
            binding.txtHalfDay.setBackgroundResource(R.drawable.bg_orange_corner);
            binding.txtHalfDay.setTextColor(getResources().getColor(R.color.txtOrange));
        });
        binding.txtAbsent.setOnClickListener(v -> {
            binding.txtPresent.setBackgroundResource(R.drawable.bg_green_corner);
            binding.txtPresent.setTextColor(getResources().getColor(R.color.txtGreen));
            binding.txtDoublePresent.setBackgroundResource(R.drawable.bg_green_corner);
            binding.txtDoublePresent.setTextColor(getResources().getColor(R.color.txtGreen));
            binding.txtAbsent.setBackgroundResource(R.drawable.bg_red);
            binding.txtAbsent.setTextColor(getResources().getColor(R.color.white));
            binding.txtHalfDay.setBackgroundResource(R.drawable.bg_orange_corner);
            binding.txtHalfDay.setTextColor(getResources().getColor(R.color.txtOrange));
        });
        binding.txtHalfDay.setOnClickListener(v -> {
            binding.txtPresent.setBackgroundResource(R.drawable.bg_green_corner);
            binding.txtPresent.setTextColor(getResources().getColor(R.color.txtGreen));
            binding.txtDoublePresent.setBackgroundResource(R.drawable.bg_green_corner);
            binding.txtDoublePresent.setTextColor(getResources().getColor(R.color.txtGreen));
            binding.txtAbsent.setBackgroundResource(R.drawable.bg_red_corner);
            binding.txtAbsent.setTextColor(getResources().getColor(R.color.txtRed));
            binding.txtHalfDay.setBackgroundResource(R.drawable.bg_orange);
            binding.txtHalfDay.setTextColor(getResources().getColor(R.color.white));
        });

        binding.txtOverTime.setOnClickListener(v -> {
            binding.txtOverTime.setBackgroundResource(R.drawable.bg_green);
            binding.txtOverTime.setTextColor(getResources().getColor(R.color.white));
            binding.txtOverTime.setText("2 Hour");
        });

        binding.txtPaidLeave.setOnClickListener(v -> {
            binding.txtPaidLeave.setBackgroundResource(R.drawable.bg_purple);
            binding.txtPaidLeave.setTextColor(getResources().getColor(R.color.white));
            binding.txtSickLeave.setBackgroundResource(R.drawable.bg_purple_corner);
            binding.txtSickLeave.setTextColor(getResources().getColor(R.color.txtPurple));
            binding.txtUnPaidLeave.setBackgroundResource(R.drawable.bg_purple_corner);
            binding.txtUnPaidLeave.setTextColor(getResources().getColor(R.color.txtPurple));
        });
        binding.txtSickLeave.setOnClickListener(v -> {
            binding.txtPaidLeave.setBackgroundResource(R.drawable.bg_purple_corner);
            binding.txtPaidLeave.setTextColor(getResources().getColor(R.color.txtPurple));
            binding.txtSickLeave.setBackgroundResource(R.drawable.bg_purple);
            binding.txtSickLeave.setTextColor(getResources().getColor(R.color.white));
            binding.txtUnPaidLeave.setBackgroundResource(R.drawable.bg_purple_corner);
            binding.txtUnPaidLeave.setTextColor(getResources().getColor(R.color.txtPurple));
        });
        binding.txtUnPaidLeave.setOnClickListener(v -> {
            binding.txtPaidLeave.setBackgroundResource(R.drawable.bg_purple_corner);
            binding.txtPaidLeave.setTextColor(getResources().getColor(R.color.txtPurple));
            binding.txtSickLeave.setBackgroundResource(R.drawable.bg_purple_corner);
            binding.txtSickLeave.setTextColor(getResources().getColor(R.color.txtPurple));
            binding.txtUnPaidLeave.setBackgroundResource(R.drawable.bg_purple);
            binding.txtUnPaidLeave.setTextColor(getResources().getColor(R.color.white));
        });

        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();

                Toast.makeText(getActivity(), "Changes Saved", Toast.LENGTH_SHORT).show();
                PresentBottomSheetFragment.this.dismiss();
            }
        });

        return binding.getRoot();
    }
}