package com.example.staffin.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.staffin.R;
import com.example.staffin.databinding.FragmentPresentBottomSheetBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class PresentBottomSheetFragment extends BottomSheetDialogFragment {
    FragmentPresentBottomSheetBinding binding;

    boolean present, absent, doublePresent, halfDay, paidLeave, sickLeave, unpaidLeave, overTime;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPresentBottomSheetBinding.inflate(inflater, container, false);
        present = absent = doublePresent = halfDay = paidLeave = sickLeave = unpaidLeave = false;

        String date = this.getArguments().getString("Date");

        binding.txt1.setText(date);

        binding.txtPresent.setOnClickListener(v -> {
            if (!present) {
                present = true;
                absent = doublePresent = halfDay = paidLeave = sickLeave = unpaidLeave = false;
                binding.txtPresent.setBackgroundResource(R.drawable.bg_green);
                binding.txtPresent.setTextColor(getResources().getColor(R.color.white));

                binding.txtDoublePresent.setBackgroundResource(R.drawable.bg_green_corner);
                binding.txtDoublePresent.setTextColor(getResources().getColor(R.color.txtGreen));
                binding.txtAbsent.setBackgroundResource(R.drawable.bg_red_corner);
                binding.txtAbsent.setTextColor(getResources().getColor(R.color.txtRed));
                binding.txtHalfDay.setBackgroundResource(R.drawable.bg_orange_corner);
                binding.txtHalfDay.setTextColor(getResources().getColor(R.color.txtOrange));

                binding.txtPaidLeave.setBackgroundResource(R.drawable.bg_purple_corner);
                binding.txtPaidLeave.setTextColor(getResources().getColor(R.color.txtPurple));
                binding.txtSickLeave.setBackgroundResource(R.drawable.bg_purple_corner);
                binding.txtSickLeave.setTextColor(getResources().getColor(R.color.txtPurple));
                binding.txtUnPaidLeave.setBackgroundResource(R.drawable.bg_purple_corner);
                binding.txtUnPaidLeave.setTextColor(getResources().getColor(R.color.txtPurple));
            } else {
                present = false;
                binding.txtPresent.setBackgroundResource(R.drawable.bg_green_corner);
                binding.txtPresent.setTextColor(getResources().getColor(R.color.txtGreen));
            }

        });


        binding.txtDoublePresent.setOnClickListener(v -> {
            if (!doublePresent) {
                doublePresent = true;
                absent = present = halfDay = paidLeave = sickLeave = unpaidLeave = false;
                binding.txtDoublePresent.setBackgroundResource(R.drawable.bg_green);
                binding.txtDoublePresent.setTextColor(getResources().getColor(R.color.white));

                binding.txtPresent.setBackgroundResource(R.drawable.bg_green_corner);
                binding.txtPresent.setTextColor(getResources().getColor(R.color.txtGreen));
                binding.txtAbsent.setBackgroundResource(R.drawable.bg_red_corner);
                binding.txtAbsent.setTextColor(getResources().getColor(R.color.txtRed));
                binding.txtHalfDay.setBackgroundResource(R.drawable.bg_orange_corner);
                binding.txtHalfDay.setTextColor(getResources().getColor(R.color.txtOrange));

                binding.txtPaidLeave.setBackgroundResource(R.drawable.bg_purple_corner);
                binding.txtPaidLeave.setTextColor(getResources().getColor(R.color.txtPurple));
                binding.txtSickLeave.setBackgroundResource(R.drawable.bg_purple_corner);
                binding.txtSickLeave.setTextColor(getResources().getColor(R.color.txtPurple));
                binding.txtUnPaidLeave.setBackgroundResource(R.drawable.bg_purple_corner);
                binding.txtUnPaidLeave.setTextColor(getResources().getColor(R.color.txtPurple));

            } else {
                doublePresent = false;
                binding.txtDoublePresent.setBackgroundResource(R.drawable.bg_green_corner);
                binding.txtDoublePresent.setTextColor(getResources().getColor(R.color.txtGreen));
            }
        });


        binding.txtAbsent.setOnClickListener(v -> {
            if (!absent) {
                absent = true;
                doublePresent = present = halfDay = paidLeave = sickLeave = unpaidLeave = false;
                binding.txtAbsent.setBackgroundResource(R.drawable.bg_red);
                binding.txtAbsent.setTextColor(getResources().getColor(R.color.white));

                binding.txtDoublePresent.setBackgroundResource(R.drawable.bg_green_corner);
                binding.txtDoublePresent.setTextColor(getResources().getColor(R.color.txtGreen));

                binding.txtPresent.setBackgroundResource(R.drawable.bg_green_corner);
                binding.txtPresent.setTextColor(getResources().getColor(R.color.txtGreen));

                binding.txtHalfDay.setBackgroundResource(R.drawable.bg_orange_corner);
                binding.txtHalfDay.setTextColor(getResources().getColor(R.color.txtOrange));

                binding.txtPaidLeave.setBackgroundResource(R.drawable.bg_purple_corner);
                binding.txtPaidLeave.setTextColor(getResources().getColor(R.color.txtPurple));
                binding.txtSickLeave.setBackgroundResource(R.drawable.bg_purple_corner);
                binding.txtSickLeave.setTextColor(getResources().getColor(R.color.txtPurple));
                binding.txtUnPaidLeave.setBackgroundResource(R.drawable.bg_purple_corner);
                binding.txtUnPaidLeave.setTextColor(getResources().getColor(R.color.txtPurple));

            } else {
                absent = false;
                binding.txtAbsent.setBackgroundResource(R.drawable.bg_red_corner);
                binding.txtAbsent.setTextColor(getResources().getColor(R.color.txtRed));
            }

        });


        binding.txtHalfDay.setOnClickListener(v -> {
            if (!halfDay) {
                halfDay = true;
                doublePresent = present = absent = paidLeave = sickLeave = unpaidLeave = false;
                binding.txtHalfDay.setBackgroundResource(R.drawable.bg_orange);
                binding.txtHalfDay.setTextColor(getResources().getColor(R.color.white));

                binding.txtAbsent.setBackgroundResource(R.drawable.bg_red_corner);
                binding.txtAbsent.setTextColor(getResources().getColor(R.color.txtRed));

                binding.txtDoublePresent.setBackgroundResource(R.drawable.bg_green_corner);
                binding.txtDoublePresent.setTextColor(getResources().getColor(R.color.txtGreen));

                binding.txtPresent.setBackgroundResource(R.drawable.bg_green_corner);
                binding.txtPresent.setTextColor(getResources().getColor(R.color.txtGreen));


                binding.txtPaidLeave.setBackgroundResource(R.drawable.bg_purple_corner);
                binding.txtPaidLeave.setTextColor(getResources().getColor(R.color.txtPurple));
                binding.txtSickLeave.setBackgroundResource(R.drawable.bg_purple_corner);
                binding.txtSickLeave.setTextColor(getResources().getColor(R.color.txtPurple));
                binding.txtUnPaidLeave.setBackgroundResource(R.drawable.bg_purple_corner);
                binding.txtUnPaidLeave.setTextColor(getResources().getColor(R.color.txtPurple));

            } else {
                halfDay = false;
                binding.txtHalfDay.setBackgroundResource(R.drawable.bg_orange_corner);
                binding.txtHalfDay.setTextColor(getResources().getColor(R.color.txtOrange));

            }

        });

//        binding.txtOverTime.setOnClickListener(v -> {
//            binding.txtOverTime.setBackgroundResource(R.drawable.bg_green);
//            binding.txtOverTime.setTextColor(getResources().getColor(R.color.white));
//            binding.txtOverTime.setText("2 Hour");
//        });

        binding.txtPaidLeave.setOnClickListener(v -> {
            if (!paidLeave) {
                paidLeave = true;
                absent = present = halfDay = unpaidLeave = sickLeave = doublePresent = false;

                binding.txtUnPaidLeave.setBackgroundResource(R.drawable.bg_purple_corner);
                binding.txtUnPaidLeave.setTextColor(getResources().getColor(R.color.txtPurple));

                binding.txtDoublePresent.setBackgroundResource(R.drawable.bg_green_corner);
                binding.txtDoublePresent.setTextColor(getResources().getColor(R.color.txtGreen));

                binding.txtPresent.setBackgroundResource(R.drawable.bg_green_corner);
                binding.txtPresent.setTextColor(getResources().getColor(R.color.txtGreen));
                binding.txtAbsent.setBackgroundResource(R.drawable.bg_red_corner);
                binding.txtAbsent.setTextColor(getResources().getColor(R.color.txtRed));
                binding.txtHalfDay.setBackgroundResource(R.drawable.bg_orange_corner);
                binding.txtHalfDay.setTextColor(getResources().getColor(R.color.txtOrange));

                binding.txtPaidLeave.setBackgroundResource(R.drawable.bg_purple);
                binding.txtPaidLeave.setTextColor(getResources().getColor(R.color.white));
                binding.txtSickLeave.setBackgroundResource(R.drawable.bg_purple_corner);
                binding.txtSickLeave.setTextColor(getResources().getColor(R.color.txtPurple));

            } else {
                paidLeave = false;
                binding.txtPaidLeave.setBackgroundResource(R.drawable.bg_purple_corner);
                binding.txtPaidLeave.setTextColor(getResources().getColor(R.color.txtPurple));
            }
        });
        binding.txtSickLeave.setOnClickListener(v -> {
            if (!sickLeave) {
                sickLeave = true;
                absent = present = halfDay = paidLeave = unpaidLeave = doublePresent = false;

                binding.txtUnPaidLeave.setBackgroundResource(R.drawable.bg_purple_corner);
                binding.txtUnPaidLeave.setTextColor(getResources().getColor(R.color.txtPurple));

                binding.txtDoublePresent.setBackgroundResource(R.drawable.bg_green_corner);
                binding.txtDoublePresent.setTextColor(getResources().getColor(R.color.txtGreen));

                binding.txtPresent.setBackgroundResource(R.drawable.bg_green_corner);
                binding.txtPresent.setTextColor(getResources().getColor(R.color.txtGreen));
                binding.txtAbsent.setBackgroundResource(R.drawable.bg_red_corner);
                binding.txtAbsent.setTextColor(getResources().getColor(R.color.txtRed));
                binding.txtHalfDay.setBackgroundResource(R.drawable.bg_orange_corner);
                binding.txtHalfDay.setTextColor(getResources().getColor(R.color.txtOrange));

                binding.txtPaidLeave.setBackgroundResource(R.drawable.bg_purple_corner);
                binding.txtPaidLeave.setTextColor(getResources().getColor(R.color.txtPurple));
                binding.txtSickLeave.setBackgroundResource(R.drawable.bg_purple);
                binding.txtSickLeave.setTextColor(getResources().getColor(R.color.white));

            } else {
                sickLeave = false;
                binding.txtSickLeave.setBackgroundResource(R.drawable.bg_purple_corner);
                binding.txtSickLeave.setTextColor(getResources().getColor(R.color.txtPurple));
            }
        });
        binding.txtUnPaidLeave.setOnClickListener(v -> {
            if (!unpaidLeave) {
                unpaidLeave = true;
                absent = present = halfDay = paidLeave = sickLeave = doublePresent = false;

                binding.txtUnPaidLeave.setBackgroundResource(R.drawable.bg_purple);
                binding.txtUnPaidLeave.setTextColor(getResources().getColor(R.color.white));

                binding.txtDoublePresent.setBackgroundResource(R.drawable.bg_green_corner);
                binding.txtDoublePresent.setTextColor(getResources().getColor(R.color.txtGreen));

                binding.txtPresent.setBackgroundResource(R.drawable.bg_green_corner);
                binding.txtPresent.setTextColor(getResources().getColor(R.color.txtGreen));
                binding.txtAbsent.setBackgroundResource(R.drawable.bg_red_corner);
                binding.txtAbsent.setTextColor(getResources().getColor(R.color.txtRed));
                binding.txtHalfDay.setBackgroundResource(R.drawable.bg_orange_corner);
                binding.txtHalfDay.setTextColor(getResources().getColor(R.color.txtOrange));

                binding.txtPaidLeave.setBackgroundResource(R.drawable.bg_purple_corner);
                binding.txtPaidLeave.setTextColor(getResources().getColor(R.color.txtPurple));
                binding.txtSickLeave.setBackgroundResource(R.drawable.bg_purple_corner);
                binding.txtSickLeave.setTextColor(getResources().getColor(R.color.txtPurple));

            } else {
                unpaidLeave = false;
                binding.txtUnPaidLeave.setBackgroundResource(R.drawable.bg_purple_corner);
                binding.txtUnPaidLeave.setTextColor(getResources().getColor(R.color.txtPurple));
            }
        });

        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (present || absent || doublePresent || halfDay || paidLeave || unpaidLeave || sickLeave) {
                    getActivity().finish();
                    Toast.makeText(getActivity(), "Changes Saved", Toast.LENGTH_SHORT).show();
                    PresentBottomSheetFragment.this.dismiss();
                } else {
                    Toast.makeText(getContext(), "Please Select Any Status Of Attendance", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return binding.getRoot();
    }
}