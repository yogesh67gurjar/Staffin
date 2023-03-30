package com.example.staffin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.staffin.databinding.ActivityInsidePayrollBinding;

import java.util.ArrayList;

public class InsidePayrollActivity extends AppCompatActivity {
    ActivityInsidePayrollBinding binding;
    boolean[] selectedCourses;
    String monthSelected;
    ArrayList<Integer> courseList = new ArrayList<>();
    String[] courseArray = {"Yogesh", "Saakshi", "Shubham Sharma", "Shubham Raikwar", "Pragati", "Shubhi"
            , "ShreeOm", "Madhur", "Ashok Sir", "Shiksha", "Soniya", "Ashutosh"
            , "Umesh", "Vishnu", "Shoabi", "Rahul", "Shailja", "Rajat"
            , "Shivani", "Dhruv"};
    String[] months = {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsidePayrollBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        binding.monthEt.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        ArrayAdapter ss = new ArrayAdapter(this, android.R.layout.simple_spinner_item, months);
        ss.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.monthEt.setAdapter(ss);

        binding.monthEt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                monthSelected = months[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        selectedCourses = new boolean[courseArray.length];
        binding.selectEmployeesEt.setOnClickListener(v -> {
            showCoursedialog();
        });

        binding.btnHome.setOnClickListener(v -> {
            finish();
        });
        binding.nextBtn.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), PayrollActivity.class));
            finish();
        });


//        binding.monthEt.setOnClickListener(v -> {
//            final Calendar c = Calendar.getInstance();
//
//            int year = c.get(Calendar.YEAR);
//            int month = c.get(Calendar.MONTH);
//            int day = c.get(Calendar.DAY_OF_MONTH);
//
//            DatePickerDialog datePickerDialog = new DatePickerDialog(InsidePayrollActivity.this,
//                    (view, year1, monthOfYear, dayOfMonth) -> {
//                        //
////                        binding.monthEt.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year1);
//                    },
//                    year, month, day);
//            datePickerDialog.show();
//        });

    }

    private void showCoursedialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(InsidePayrollActivity.this);
        builder.setTitle("Select Courses");
        builder.setCancelable(false);
        builder.setMultiChoiceItems(courseArray, selectedCourses, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            courseList.add(which);
//                        } else {
//                            courseList.remove(which);
                        }
                    }
                }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //creating String Builder
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i = 0; i < courseList.size(); i++) {
                            stringBuilder.append(courseArray[courseList.get(i)]);
                            //Check Condition
                            if (i != courseList.size() - 1) {
                                //when i value no equal of course list
                                //then add a comma
                                stringBuilder.append(", ");
                            }
                            //String selected courses to textview


                            binding.selectEmployeesEt.setText(stringBuilder.toString());
                        }
//                courseList.clear();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < selectedCourses.length; i++) {
                            selectedCourses[i] = false;
                            courseList.clear();
                            binding.selectEmployeesEt.setText("");
                        }
                    }
                });
        builder.show();

    }

}