package com.example.staffin;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
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
import com.example.staffin.Response.EmployeeResult;
import com.example.staffin.Response.OverTimeResponse;
import com.example.staffin.Response.SingleEmployeeResponse;
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
    int desigId = 100012, DepId = 100012;
    String empId;
    ProgressDialog progressDialog;

    String fotStart, fotEnd, fotAmount, sotStart, sotEnd, sotAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCompanyDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        clickListeners();
        //progressBar


    }

    private void clickListeners() {

        progressDialog = new ProgressDialog(CompanyDetailsActivity.this);
        progressDialog.setMessage("Please Wait.....");

        progressDialog.show();

        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        empId = getIntent().getStringExtra("empId");
        Id = getIntent().getIntExtra("Id", 0);

        binding.employeeIdEt.setText(empId);
        adDialog = new Dialog(CompanyDetailsActivity.this);
        sharedPreferences = getSharedPreferences("staffin", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        from = getIntent().getStringExtra("from");

//        Department and Designation Spinner Code
        Call<DepartmentResponse> callGetDepartment = apiInterface.getDepartment();
        if (isNetworkAvailable()) {

            callGetDepartment.enqueue(new Callback<DepartmentResponse>() {
                @Override
                public void onResponse(Call<DepartmentResponse> call, Response<DepartmentResponse> response) {
                    if (response.isSuccessful()) {
                        assert response.body() != null;
                        List<Department> departments = response.body().getDepartment();
                        progressDialog.dismiss();
                        String tamp = "";
                        for (int i = 0; i < departments.size(); i++) {
                            tamp = tamp.concat(response.body().getDepartment().get(i).getName() + ",,,");
                            Log.i("sahgdusahdsa", response.body().getDepartment().get(i).getId().toString());
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
                                progressDialog.show();
                                DepId = response.body().getDepartment().get(position).getId();
                                Log.i("dusahdu", String.valueOf(DepId));
                                //Designation Spinner Code
                                Call<DesignationResponse> call1 = apiInterface.getDesignation(DepId);
                                call1.enqueue(new Callback<DesignationResponse>() {
                                    @Override
                                    public void onResponse(Call<DesignationResponse> call, Response<DesignationResponse> response) {
                                        if (response.isSuccessful()) {
                                            progressDialog.dismiss();
                                            List<DesignationDetail> designationDetails = response.body().getDesignationDetails();
                                            String tamp = "";
                                            for (int i = 0; i < designationDetails.size(); i++) {
                                                tamp = tamp.concat(designationDetails.get(i).getDesignation() + ",,,");
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
                                                    desigId = response.body().getDesignationDetails().get(position).getId();
                                                    Log.i("dusahdu", String.valueOf(desigId));
                                                }

                                                @Override
                                                public void onNothingSelected(AdapterView<?> parent) {
                                                }
                                            });
//                                        Log.i("departmentSelected",departmentSelected);
//                                        Log.i("designationSelected",designationSelected);
                                        } else {
                                            progressDialog.dismiss();
                                            Toast.makeText(CompanyDetailsActivity.this, "On Response Fail", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<DesignationResponse> call, Throwable t) {
                                        progressDialog.dismiss();
                                        Toast.makeText(CompanyDetailsActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(CompanyDetailsActivity.this, "no Response", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<DepartmentResponse> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(CompanyDetailsActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                }
            });

            if (from.equalsIgnoreCase("edit")) {
                Call<SingleEmployeeResponse> callGetSingleEmployee = apiInterface.getSingleEmployee(Id);
                callGetSingleEmployee.enqueue(new Callback<SingleEmployeeResponse>() {
                    @Override
                    public void onResponse(Call<SingleEmployeeResponse> call, Response<SingleEmployeeResponse> response) {
                        if (response.isSuccessful()) {
                            EmployeeResult result = response.body().getEmployeeResult().get(0);
                            int depPos = 0;
                            int desigPos = 0;


                        } else {
                            Toast.makeText(CompanyDetailsActivity.this, "Some error occured", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<SingleEmployeeResponse> call, Throwable t) {
                        Toast.makeText(CompanyDetailsActivity.this, "some failure occured", Toast.LENGTH_SHORT).show();
                        Log.d("kgndf", t.getMessage());
                    }
                });
            } else {

            }
        } else {
            Toast.makeText(this, "Internet Not Available", Toast.LENGTH_SHORT).show();
        }


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
                // update wali api

                // edit wala flow

            } else {
                intent.putExtra("from", "add");
                if (binding.annualLeaveEt.getText().toString().isEmpty()) {
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
//                    String employeeId = binding.employeeIdEt.getText().toString();
//                    String spinner = binding.departmentEt.getSelectedItem().toString();
//                    String spinner2 = binding.designationEt.getSelectedItem().toString();


                    String annualLeave = binding.annualLeaveEt.getText().toString();
                    String medicalLeave = binding.medicalLeaveEt.getText().toString();
                    String jdate = binding.jDateEt.getText().toString();
                    String rdate = null;
                    if (!binding.rDateEt.getText().toString().isEmpty()) {
                        rdate = binding.rDateEt.getText().toString();
                    }
                    Status = "";
                    if (binding.rbActive.isChecked()) {
                        binding.rbActive.getText().toString();
                        Status = "active";
                    } else {
                        binding.rbInactive.getText().toString();
                        Status = "inActive";
                    }
                    finalStatus = Status;
                    String depIdStr = String.valueOf(DepId);
                    String desigIdStr = String.valueOf(desigId);
                    String basicSalary = binding.basicEt.getText().toString();
                    String hourlyRate = binding.hourlyEt.getText().toString();
                    Call<CompanyDetailsResponse> callPostSingleCompanyDetailsEmployee = apiInterface.postSingleCompanyDetail(Id, depIdStr, desigIdStr, annualLeave, medicalLeave, finalStatus, jdate, rdate, basicSalary, hourlyRate);
                    if (isNetworkAvailable()) {
                        progressDialog.show();
                        callPostSingleCompanyDetailsEmployee.enqueue(new Callback<CompanyDetailsResponse>() {
                            @Override
                            public void onResponse(Call<CompanyDetailsResponse> call, Response<CompanyDetailsResponse> response) {
                                if (response.isSuccessful()) {
                                    progressDialog.dismiss();
                                    startActivity(intent);
                                } else {
                                    Log.d("jkbfjksdf", response.message());
                                    progressDialog.dismiss();
                                    Toast.makeText(CompanyDetailsActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<CompanyDetailsResponse> call, Throwable t) {
                                progressDialog.dismiss();
                                Log.d("sdknf", t.getMessage());
                                Toast.makeText(CompanyDetailsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(CompanyDetailsActivity.this, "some error occured", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        Toast.makeText(this, "Internet Not Available", Toast.LENGTH_SHORT).show();
                    }
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

                    fotStart = firstTv.getText().toString();
                    fotEnd = secondTv.getText().toString();
                    fotAmount = firstAmountEt.getText().toString();
                    sotStart = thirdTv.getText().toString();
                    sotEnd = fourthTv.getText().toString();
                    sotAmount = secondAmountEt.getText().toString();

                    progressDialog.show();
                    Call<OverTimeResponse> overTimeResponseCall = apiInterface.postOverTime(Id, fotStart, fotEnd, fotAmount, sotStart, sotEnd, sotAmount);
                    overTimeResponseCall.enqueue(new Callback<OverTimeResponse>() {
                        @Override
                        public void onResponse(Call<OverTimeResponse> call, Response<OverTimeResponse> response) {
                            if (response.isSuccessful()) {
                                progressDialog.dismiss();
                                editor.putString("firstOt", firstAmountEt.getText().toString());
                                editor.putString("secondOt", secondAmountEt.getText().toString());
                                editor.putString("firstTv", firstTv.getText().toString());
                                editor.putString("secondTv", secondTv.getText().toString());
                                editor.putString("thirdTv", thirdTv.getText().toString());
                                editor.putString("fourthTv", fourthTv.getText().toString());
                                Log.e("dfiudsghf", response.message());
                                editor.apply();
                                adDialog.dismiss();

                            } else {
                                progressDialog.dismiss();
                                Log.e("sfhuidshf", response.message());
                                Toast.makeText(CompanyDetailsActivity.this, "On Response Fail", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<OverTimeResponse> call, Throwable t) {
                            progressDialog.dismiss();
                            Log.e("fdjhufiudhf", t.getMessage());
                            Toast.makeText(CompanyDetailsActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                        }
                    });

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

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}