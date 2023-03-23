package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.staffin.databinding.ActivityCompanyDetailsBinding;
import com.example.staffin.databinding.ActivityLoginBinding;

import java.util.Calendar;

public class CompanyDetailsActivity extends AppCompatActivity {
    ActivityCompanyDetailsBinding binding;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Dialog adDialog;
    int hour, minute = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCompanyDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        adDialog = new Dialog(CompanyDetailsActivity.this);
        sharedPreferences = getSharedPreferences("staffin", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        binding.btnBack.setOnClickListener(v -> {
            onBackPressed();
        });

        binding.addOvertimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup();
            }
        });

        binding.btnNext.setOnClickListener(v -> {

            if (binding.employeeIdEt.getText().toString().isEmpty()) {
                binding.employeeIdEt.setError("Enter Id");
                binding.employeeIdEt.requestFocus();
            } else if (binding.departmentEt.getText().toString().isEmpty()) {
                binding.departmentEt.setError("Enter Department Name");
                binding.departmentEt.requestFocus();
            } else if (binding.designationEt.getText().toString().isEmpty()) {
                binding.designationEt.setError("Enter Designation Name");
                binding.designationEt.requestFocus();
            } else if (binding.annualLeaveEt.getText().toString().isEmpty()) {
                binding.annualLeaveEt.setError("Enter Annual Leaves");
                binding.annualLeaveEt.requestFocus();
            } else if (binding.medicalLeaveEt.getText().toString().isEmpty()) {
                binding.medicalLeaveEt.setError("Enter Medical Leaves");
                binding.medicalLeaveEt.requestFocus();
            } else if (binding.jDateEt.getText().toString().isEmpty()) {
                Toast.makeText(this, "Enter Joining Date", Toast.LENGTH_SHORT).show();
            } else if (binding.rDateEt.getText().toString().isEmpty()) {
                Toast.makeText(this, "Enter Relieving Date", Toast.LENGTH_SHORT).show();
            } else if (!binding.rbActive.isChecked() && !binding.rbInactive.isChecked()) {
                Toast.makeText(this, "Please Select Status", Toast.LENGTH_SHORT).show();
            } else if (binding.basicEt.getText().toString().isEmpty()) {
                binding.basicEt.setError("Enter Basic Salary");
                binding.basicEt.requestFocus();
            } else if (binding.hourlyEt.getText().toString().isEmpty()) {
                binding.hourlyEt.setError("Enter Hourly Rate");
                binding.hourlyEt.requestFocus();
            } else {


                startActivity(new Intent(getApplicationContext(), BankDetailsActivity.class));
            }
        });

        binding.jDateEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(CompanyDetailsActivity.this,
                        (view, year1, monthOfYear, dayOfMonth) -> {
                            //
                            binding.jDateEt.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year1);
                        },
                        year, month, day);
                datePickerDialog.show();
            }
        });

        binding.rDateEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(CompanyDetailsActivity.this,
                        (view, year1, monthOfYear, dayOfMonth) -> {
                            //
                            binding.rDateEt.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year1);
                        },
                        year, month, day);
                datePickerDialog.show();
            }
        });
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            super.onKeyDown(keyCode, event);
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Can't Go Back", Toast.LENGTH_SHORT).show();
    }

    private void showPopup() {


        adDialog.setContentView(R.layout.popup_overtime);
        adDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        adDialog.show();

        EditText firstAmountEt = adDialog.findViewById(R.id.firstAmountEt);
        EditText secondAmountEt = adDialog.findViewById(R.id.secondAmountEt);
        AppCompatButton submitBtn = adDialog.findViewById(R.id.submitBtn);
        TextView firstTv = adDialog.findViewById(R.id.firstTv);
        TextView secondTv = adDialog.findViewById(R.id.secondTv);
        TextView thirdTv = adDialog.findViewById(R.id.thirdTv);
        TextView fourthTv = adDialog.findViewById(R.id.fourthTv);

        if (sharedPreferences.getAll().containsKey("firstOt") && sharedPreferences.getAll().containsKey("secondOt") && sharedPreferences.getAll().containsKey("firstTv") && sharedPreferences.getAll().containsKey("secondTv") && sharedPreferences.getAll().containsKey("thirdTv") && sharedPreferences.getAll().containsKey("fourthTv")) {
            firstAmountEt.setText(sharedPreferences.getAll().get("firstOt").toString());
            secondAmountEt.setText(sharedPreferences.getAll().get("secondOt").toString());
            firstTv.setText(sharedPreferences.getAll().get("firstTv").toString());
            secondTv.setText(sharedPreferences.getAll().get("secondTv").toString());
            thirdTv.setText(sharedPreferences.getAll().get("thirdTv").toString());
            fourthTv.setText(sharedPreferences.getAll().get("fourthTv").toString());
        }

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstTv.getText().toString().isEmpty()) {
                    Toast.makeText(CompanyDetailsActivity.this, "Enter Start Time For 1st Overtime", Toast.LENGTH_SHORT).show();
                } else if (secondTv.getText().toString().isEmpty()) {
                    Toast.makeText(CompanyDetailsActivity.this, "Enter End Time For 1st Overtime", Toast.LENGTH_SHORT).show();
                } else if (thirdTv.getText().toString().isEmpty()) {
                    Toast.makeText(CompanyDetailsActivity.this, "Enter Start Time For 2nd Overtime", Toast.LENGTH_SHORT).show();
                } else if (fourthTv.getText().toString().isEmpty()) {
                    Toast.makeText(CompanyDetailsActivity.this, "Enter End Time For 2nd Overtime", Toast.LENGTH_SHORT).show();
                }
                else if (firstAmountEt.getText().toString().isEmpty()) {
                    Toast.makeText(CompanyDetailsActivity.this, "Enter Amount For 1st Overtime", Toast.LENGTH_SHORT).show();
                } else if (secondAmountEt.getText().toString().isEmpty()) {
                    Toast.makeText(CompanyDetailsActivity.this, "Enter Amount For 2nd Overtime", Toast.LENGTH_SHORT).show();
                } else {

                    editor.putString("firstOt", firstAmountEt.getText().toString());
                    editor.putString("secondOt", secondAmountEt.getText().toString());
                    editor.putString("firstTv", firstTv.getText().toString());
                    editor.putString("secondTv", secondTv.getText().toString());
                    editor.putString("thirdTv", thirdTv.getText().toString());
                    editor.putString("fourthTv", fourthTv.getText().toString());
                    editor.apply();
                    adDialog.dismiss();
                }


            }
        });


        firstTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                // on below line we are getting our hour, minute.
                hour = c.get(Calendar.HOUR_OF_DAY);
                minute = c.get(Calendar.MINUTE);

                // on below line we are initializing our Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(CompanyDetailsActivity.this,
                        (view, hourOfDay, minute1) -> firstTv.setText(hourOfDay + ":" + minute1), hour, minute, false);

                timePickerDialog.show();

            }
        });
        secondTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                hour = c.get(Calendar.HOUR_OF_DAY);
                minute = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(CompanyDetailsActivity.this,
                        (view, hourOfDay, minute1) -> secondTv.setText(hourOfDay + ":" + minute1), hour, minute, false);

                timePickerDialog.show();
            }
        });

        thirdTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                hour = c.get(Calendar.HOUR_OF_DAY);
                minute = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(CompanyDetailsActivity.this,
                        (view, hourOfDay, minute1) -> thirdTv.setText(hourOfDay + ":" + minute1), hour, minute, false);

                timePickerDialog.show();
            }
        });

        fourthTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                hour = c.get(Calendar.HOUR_OF_DAY);
                minute = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(CompanyDetailsActivity.this,
                        (view, hourOfDay, minute1) -> fourthTv.setText(hourOfDay + ":" + minute1), hour, minute, false);

                timePickerDialog.show();
            }
        });


    }

    @Override
    protected void onStop() {
        super.onStop();
        if (sharedPreferences.getAll().containsKey("firstOt") && sharedPreferences.getAll().containsKey("secondOt") && sharedPreferences.getAll().containsKey("firstTv") && sharedPreferences.getAll().containsKey("secondTv") && sharedPreferences.getAll().containsKey("thirdTv") && sharedPreferences.getAll().containsKey("fourthTv")) {
            editor.remove("firstOt");
            editor.remove("secondOt");
            editor.remove("firstTv");
            editor.remove("secondTv");
            editor.remove("thirdTv");
            editor.remove("fourthTv");
            editor.apply();
        }
    }
}