package com.example.staffin;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.staffin.Interface.ApiInterface;
import com.example.staffin.Response.CompanyDetailsResponse;
import com.example.staffin.Response.Department;
import com.example.staffin.Response.DepartmentResponse;
import com.example.staffin.Response.DesignationDetail;
import com.example.staffin.Response.DesignationResponse;
import com.example.staffin.Retrofit.RetrofitServices;
import com.example.staffin.databinding.ActivityCompanyDetailsBinding;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyDetailsActivity extends AppCompatActivity {
    ActivityCompanyDetailsBinding binding;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Dialog adDialog;
    int hour, minute = 0;
    ApiInterface apiInterface;
    String departmentSelected, designationSelected;
    String Status, finalStatus;
    String from;
    int Id;
    String empId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCompanyDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        Id = getIntent().getIntExtra("Id", 0);
        empId = getIntent().getStringExtra("empId");

        binding.employeeIdEt.setText(empId);
        adDialog = new Dialog(CompanyDetailsActivity.this);
        sharedPreferences = getSharedPreferences("staffin", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        from = getIntent().getStringExtra("from");

//        Department Spinner Code
        Call<DepartmentResponse> call = apiInterface.getDepartment();
        call.enqueue(new Callback<DepartmentResponse>() {
            @Override
            public void onResponse(Call<DepartmentResponse> call, Response<DepartmentResponse> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    List<Department> departments = response.body().getDepartment();
                    String tamp = "Please select Department,,,";
                    for (int i = 0; i < departments.size(); i++) {
                        tamp = tamp.concat(response.body().getDepartment().get(i).getName() + ",,,");
                    }
                    String[] strArray = tamp.split(",,,");
                    ArrayAdapter aa = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, strArray);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    //Setting the ArrayAdapter data on the Spinner
                    binding.departmentEt.setAdapter(aa);
                    binding.departmentEt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            departmentSelected = strArray[position];

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


                    //Designation Spinner Code
                    Call<DesignationResponse> call1 = apiInterface.getDesignation();
                    call1.enqueue(new Callback<DesignationResponse>() {
                        @Override
                        public void onResponse(Call<DesignationResponse> call, Response<DesignationResponse> response) {
                            if (response.isSuccessful()) {
                                List<DesignationDetail> designationDetails = response.body().getDesignationDetails();
                                String tamp = "Please select Designation,,,";
                                for (int i = 0; i < designationDetails.size(); i++) {
                                    tamp = tamp.concat(response.body().getDesignationDetails().get(i).getDesignation() + ",,,");
                                }
                                String[] strArray = tamp.split(",,,");
                                ArrayAdapter aa = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, strArray);
                                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                //Setting the ArrayAdapter data on the Spinner
                                binding.designationEt.setAdapter(aa);
                                binding.designationEt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        designationSelected = strArray[position];
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {

                                    }
                                });


                            } else {
                                Toast.makeText(CompanyDetailsActivity.this, "On Response Fail", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<DesignationResponse> call, Throwable t) {
                            Toast.makeText(CompanyDetailsActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {
                    Toast.makeText(CompanyDetailsActivity.this, "no Response", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DepartmentResponse> call, Throwable t) {
                Toast.makeText(CompanyDetailsActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });


//        if (from.equalsIgnoreCase("add")) {
//
//        } else {
//            binding.designationEt.setSelection(5);
//            binding.departmentEt.setSelection(5);
//        }


//

        binding.btnBack.setOnClickListener(v -> {
            onBackPressed();
        });

        binding.addOvertimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup();
            }
        });

        binding.rbActive.setOnClickListener(v -> {
            binding.rDateEt.setVisibility(View.GONE);
            binding.rDateTv.setVisibility(View.GONE);
        });
        binding.rbInactive.setOnClickListener(v -> {
            binding.rDateEt.setVisibility(View.VISIBLE);
            binding.rDateTv.setVisibility(View.VISIBLE);

        });
        binding.btnNext.setOnClickListener(v -> {

            Intent intent = new Intent(getApplicationContext(), BankDetailsActivity.class);
            intent.putExtra("Id", Id);
            intent.putExtra("empId", empId);
            if (from.equalsIgnoreCase("edit")) {
                intent.putExtra("from", "edit");
                // edit wali api
                // edit wala flow

            } else {
                intent.putExtra("from", "add");

                if (binding.employeeIdEt.getText().toString().isEmpty()) {
                    binding.employeeIdEt.setError("Enter Id");
                    binding.employeeIdEt.requestFocus();
                }
//            else if (binding.departmentEt.getSelectedItem() != "Please select Department") {
//                Toast.makeText(this, "Please select Department", Toast.LENGTH_SHORT).show();
//            } else if (binding.designationEt.getSelectedItem() != "Please select Designation") {
//                Toast.makeText(this, "Please select Department", Toast.LENGTH_SHORT).show();
//            }
                else if (binding.annualLeaveEt.getText().toString().isEmpty()) {
                    binding.annualLeaveEt.setError("Enter Annual Leaves");
                    binding.annualLeaveEt.requestFocus();
                } else if (binding.medicalLeaveEt.getText().toString().isEmpty()) {
                    binding.medicalLeaveEt.setError("Enter Medical Leaves");
                    binding.medicalLeaveEt.requestFocus();
                } else if (binding.jDateEt.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Enter Joining Date", Toast.LENGTH_SHORT).show();
//            } else if (binding.rDateEt.getText().toString().isEmpty()) {
//                Toast.makeText(this, "Enter Relieving Date", Toast.LENGTH_SHORT).show();
                } else if (!binding.rbActive.isChecked() && !binding.rbInactive.isChecked()) {
                    Toast.makeText(this, "Please Select Status", Toast.LENGTH_SHORT).show();
//            }else  if (binding.rbActive.isChecked() && binding.jDateEt.getText().toString().isEmpty()) {
//                Toast.makeText(this, "Enter Joining Date", Toast.LENGTH_SHORT).show();
                } else if (binding.rbInactive.isChecked() && binding.rDateEt.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Enter Relieving Date", Toast.LENGTH_SHORT).show();
                } else if (binding.basicEt.getText().toString().isEmpty()) {
                    binding.basicEt.setError("Enter Basic Salary");
                    binding.basicEt.requestFocus();
                } else if (binding.hourlyEt.getText().toString().isEmpty()) {
                    binding.hourlyEt.setError("Enter Hourly Rate");
                    binding.hourlyEt.requestFocus();
                } else {
//
                    String employeeId = binding.employeeIdEt.getText().toString();
                    String spinner = binding.departmentEt.getSelectedItem().toString();
                    String spinner2 = binding.designationEt.getSelectedItem().toString();
                    String annualLeave = binding.annualLeaveEt.getText().toString();
                    String medicalLeave = binding.medicalLeaveEt.getText().toString();
                    String jdate = binding.jDateEt.getText().toString();
                    String rdate = binding.rDateEt.getText().toString();
                    Status = "";
                    if (binding.rbActive.isChecked()) {
                        binding.rbActive.getText().toString();
                        Status = "Active";
                    } else {
                        binding.rbInactive.getText().toString();
                        Status = "InActive";
                    }
                    finalStatus = Status;
                    String basicSalary = binding.basicEt.getText().toString();
                    String hourlyRate = binding.hourlyEt.getText().toString();

//
////                RequestBody xemployeeId = RequestBody.create(MediaType.parse("text/plain"), employeeId);
//                RequestBody xspinner = RequestBody.create(MediaType.parse("text/plain"), spinner);
//                RequestBody xspinner2 = RequestBody.create(MediaType.parse("text/plain"), spinner2);
//                RequestBody xannualLeave = RequestBody.create(MediaType.parse("text/plain"), annualLeave);
//                RequestBody xmedicalLeave = RequestBody.create(MediaType.parse("text/plain"), medicalLeave);
//                RequestBody xjdate = RequestBody.create(MediaType.parse("text/plain"), jdate);
//                RequestBody xrdate = RequestBody.create(MediaType.parse("text/plain"), rdate);
//                RequestBody xfinalStatus = RequestBody.create(MediaType.parse("text/plain"), finalStatus);
//                RequestBody xbasicSalary = RequestBody.create(MediaType.parse("text/plain"), basicSalary);
//                RequestBody xhourlyRate = RequestBody.create(MediaType.parse("text/plain"), hourlyRate);
//
//
                    Call<CompanyDetailsResponse> call2 = apiInterface.postSingleCompanyDetailsEmployee(spinner, spinner2, annualLeave, medicalLeave, finalStatus, jdate, rdate, basicSalary, hourlyRate, Id);
                    call2.enqueue(new Callback<CompanyDetailsResponse>() {
                        @Override
                        public void onResponse(Call<CompanyDetailsResponse> call, Response<CompanyDetailsResponse> response) {
                            if (response.isSuccessful()) {

                                startActivity(intent);
                            } else {
                                Toast.makeText(CompanyDetailsActivity.this, "onResponse Fail", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<CompanyDetailsResponse> call, Throwable t) {
                            Toast.makeText(CompanyDetailsActivity.this, "OnFailure", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
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
                } else if (firstAmountEt.getText().toString().isEmpty()) {
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