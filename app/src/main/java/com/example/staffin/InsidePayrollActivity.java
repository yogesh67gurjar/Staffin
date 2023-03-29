package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.staffin.databinding.ActivityInsidePayrollBinding;

import java.util.ArrayList;
import java.util.Calendar;

public class InsidePayrollActivity extends AppCompatActivity {
    ActivityInsidePayrollBinding binding;
    boolean[] selectedCourses;
    ArrayList<Integer> courseList = new ArrayList<>();
    String[] courseArray = {"Biology", "English", "Maths", "Physics", "Chemistry", "Computer"
            , "Biology", "English", "Maths", "Physics", "Chemistry", "Computer"
            , "Biology", "English", "Maths", "Physics", "Chemistry", "Computer"
            , "Biology", "English", "Maths", "Physics", "Chemistry", "Computer"
            , "Biology", "English", "Maths", "Physics", "Chemistry", "Computer"
            , "Biology", "English", "Maths", "Physics", "Chemistry", "Computer"
            , "Biology", "English", "Maths", "Physics", "Chemistry", "Computer"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsidePayrollBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        selectedCourses = new boolean[courseArray.length];
        binding.employeeIdEt.setOnClickListener(v -> {

            showCoursedialog();

        });

        binding.btnHome.setOnClickListener(v -> {
            finish();
        });
        binding.nextBtn.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), SalaryInfoActivity.class));
        });


        binding.dateEt.setOnClickListener(v -> {
//            final Calendar c = Calendar.getInstance();
//
//            int year = c.get(Calendar.YEAR);
//            int month = c.get(Calendar.MONTH);
//            int day = c.get(Calendar.DAY_OF_MONTH);
//
//            DatePickerDialog datePickerDialog = new DatePickerDialog(InsidePayrollActivity.this,
//                    (view, year1, monthOfYear, dayOfMonth) -> {
//                        //
//                        binding.dateEt.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year1);
//                    },
//                    year, month, day);
//            datePickerDialog.show();
        });

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
                }
//                else {
//                    courseList.remove(which);
//                }
            }
        }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //creating String Builder
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < courseList.size(); i++) {
                    stringBuilder.append(courseArray[courseList.get(i)]);
                    //Check Condition
                    if (i != courseList.size()) {
                        //when i value no equal of course list
                        //then add a comma
                        stringBuilder.append(", ");
                    }
                    //String selected courses to textview


                    binding.employeeIdEt.setText(stringBuilder.toString());
                }
//                courseList.clear();
            }
        });
//                .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        .setPositiveButton("Clear All", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                for (int i = 0; i < selectedCourses.length; i++) {
//                selectedCourses[i] = false;
//                courseList.clear();
//                binding.employeeIdEt.setText("");
//                }
//            }
//        });
        builder.show();


    }
}