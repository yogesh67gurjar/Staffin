package com.example.staffin.Fragment;

import android.app.Application;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.staffin.InsideAttendanceActivity;
import com.example.staffin.Interface.ApiInterface;
import com.example.staffin.R;
import com.example.staffin.Response.Example;
import com.example.staffin.Response.LoginResponse;
import com.example.staffin.Retrofit.RetrofitServices;
import com.example.staffin.databinding.FragmentPresentBottomSheetBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Calendar;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PresentBottomSheetFragment extends BottomSheetDialogFragment {
    FragmentPresentBottomSheetBinding binding;
    private static final String TAG = "hahahahhahaha";
    boolean present;
    boolean absent;
    boolean doublePresent;
    boolean halfDay;
    boolean paidLeave;
    boolean sickLeave;
    boolean unpaidLeave;
    ApiInterface apiInterface;
    String date;
    String color = null;
    int monthNo;
    int Id;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPresentBottomSheetBinding.inflate(inflater, container, false);
        present = absent = doublePresent = halfDay = paidLeave = sickLeave = unpaidLeave = false;
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("please wait...");
        progressDialog.setCancelable(false);

        binding.txtPresent.setOnClickListener(v -> {
            presentFunc();
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
            absentFunc();
        });

        binding.txtHalfDay.setOnClickListener(v -> {
            halfDayFunc();
        });
//        binding.txtOverTime.setOnClickListener(v -> {
//            binding.txtOverTime.setBackgroundResource(R.drawable.bg_green);
//            binding.txtOverTime.setTextColor(getResources().getColor(R.color.white));
//            binding.txtOverTime.setText("2 Hour");
//        });
        binding.txtPaidLeave.setOnClickListener(v -> {
            paidLeaveFunc();
        });

        binding.txtSickLeave.setOnClickListener(v -> {
            sickLeaveFunc();
        });

        binding.txtUnPaidLeave.setOnClickListener(v -> {
           unpaidLeaveFunc();
        });


        date = this.getArguments().getString("Date");
        color = this.getArguments().getString("color");
        monthNo = this.getArguments().getInt("monthNumber");
        Log.d("ye aaya", color + date + monthNo);
        Id = this.getArguments().getInt("Id");
        if (!(Objects.equals(color, null))) {
            switch (color) {
                case "green":
                case "blue":
                    presentFunc();
                    break;
                case "greend":
                    doublePresentFunc();
                    break;
                case "red":
                    absentFunc();
                    Log.d("colorR", color);
                    break;
                case "orange":
                    halfDayFunc();
                    Log.d("colorO", color);
                    break;
                case "purple":
                    paidLeaveFunc();
                    Log.d("colorP", color);
                    break;
                case "sick":
                    sickLeaveFunc();
                    Log.d("colorsick", color);
                    break;
                case "unpaid":
                    unpaidLeaveFunc();
                    Log.d("colorsick", color);
                    break;
                case "black":
                    Log.d("colorB", color);
                    break;
                default:
                    Log.d("default", "kai ni");
                    break;
            }
        } else {
            color = " ";
        }


        Log.d("COLORCOLORCOLOR", color);
        binding.txt1.setText(date);


        binding.txtOverTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                // on below line we are getting our hour, minute.
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                // on below line we are initializing our Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                        (view, hourOfDay, minute1) -> binding.txtOverTime.setText(hourOfDay + ":" + minute1 + " hours"), hour, minute, true);
                timePickerDialog.show();

            }
        });

        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (present || absent || doublePresent || halfDay || paidLeave || unpaidLeave || sickLeave) {
                    String status = "";
                    String leaveType = "";
                    if (present) {
                        status = "present";
                    } else if (absent) {
                        status = "absent";
                    } else if (doublePresent) {
                        status = "double_present";
                    } else if (halfDay) {
                        status = "halfday";
                    }
                    if (paidLeave) {
                        status = "paid_leave";
                        leaveType = "paid_leave";
                    } else if (unpaidLeave) {
                        leaveType = "unpaid_leave";
                        status = "unpaid_leave";
                    } else if (sickLeave) {
                        leaveType = "sick_leave";
                        status = "sick_leave";
                    }

               //     Call<LoginResponse> calUpdateAttendanceById = apiInterface.updateAttendanceById(Id, date.split("-")[2] + "-" + 4 + "-" + date.split("-")[0], status, leaveType, binding.txtOverTime.getText().toString());
                    Log.d("yebhejahe", Id + "     " + date.split("-")[2] + "-" + monthNo + "-" + date.split("-")[0] + "     " + status + "     " + leaveType + "     " + binding.txtOverTime.getText().toString());
                    Call<Example> calUpdateAttendanceById = apiInterface.updateAttendanceById(Id, date.split("-")[2] + "-" + monthNo + "-" + date.split("-")[0], status, null, binding.txtOverTime.getText().toString());
                    progressDialog.show();
                    Log.d(TAG, Id + "    " + date.split("-")[2] + "-" + monthNo + "-" + date.split("-")[0] + "    " + status + "    " + leaveType + "    " + binding.txtOverTime.getText().toString());
                    calUpdateAttendanceById.enqueue(new Callback<Example>() {
                        @Override
                        public void onResponse(Call<Example> call, Response<Example> response) {
                            if (response.isSuccessful()) {
                                progressDialog.dismiss();
                                if (response.body().getMessage().equalsIgnoreCase("success")) {
                                    Log.d("API", response.message());
                                    Toast.makeText(getActivity(), "Changes saved successfully", Toast.LENGTH_SHORT).show();
                                    PresentBottomSheetFragment.this.dismiss();
                                    getActivity().finish();
                                } else {
                                    Log.d("APIkfndkfjn", response.message());
                                    Toast.makeText(getActivity(), "unable to change status", Toast.LENGTH_SHORT).show();
                                    getActivity().finish();

                                }

                            } else {
                                Log.d("APIkfndkfjn", response.message() + response.code());
                                progressDialog.dismiss();
                                Toast.makeText(getActivity(), "unable to change status", Toast.LENGTH_SHORT).show();
                                getActivity().finish();

                            }
                        }

                        @Override
                        public void onFailure(Call<Example> call, Throwable t) {
                            Log.d("APIFaIL", t.getMessage());
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "failure", Toast.LENGTH_SHORT).show();
                            getActivity().finish();

                        }
                    });

                } else {
                    Toast.makeText(getContext(), "Please Select Any Status Of Attendance", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return binding.getRoot();
    }



    private void doublePresentFunc() {
        if (!doublePresent) {
            doublePresent = true;
            Log.d("SFHKFHdfsfdfSDFSD", "nfsdfhsdgfhrghnsdf");
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
    }

    private void paidLeaveFunc() {
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
    }

    private void unpaidLeaveFunc() {
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
    }

    private void sickLeaveFunc() {
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
    }

    private void halfDayFunc() {
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

    }

    private void absentFunc() {
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
    }

    private void presentFunc() {
        if (!present) {
            present = true;
            Log.d("SFHKFHSDFSD", "nfsdfnsdf");
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
    }


}