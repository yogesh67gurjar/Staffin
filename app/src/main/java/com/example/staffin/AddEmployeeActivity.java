package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.example.staffin.Interface.ApiInterface;
import com.example.staffin.Response.AddEmployeeResponse;
import com.example.staffin.Response.EmployeeResult;
import com.example.staffin.Response.SingleEmployeeResponse;
import com.example.staffin.Retrofit.RetrofitServices;
import com.example.staffin.databinding.ActivityAddEmployeeBinding;

import java.io.File;
import java.util.Calendar;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEmployeeActivity extends AppCompatActivity {
    ActivityAddEmployeeBinding binding;
    ApiInterface apiInterface;

    static Boolean dpImageBoolean = false;
    String from = "";
    Uri profileImage;
    String uripi;
    File PImg;
    String name, fName, dob, gender, email, localAddress, permanentAddress, finalGender, number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddEmployeeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        final ProgressDialog progressDialog = new ProgressDialog(AddEmployeeActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        from = getIntent().getStringExtra("from");

        if (from.equalsIgnoreCase("edit")) {
            binding.textView.setText("Edit Employee");
            binding.nextBtn.setText("Save");
            int Id = getIntent().getIntExtra("id", 0);

            if (Id == 0) {
                Toast.makeText(this, "Some Error", Toast.LENGTH_SHORT).show();
            } else {

                Call<SingleEmployeeResponse> call = apiInterface.getSingleEmployee(Id);
                call.enqueue(new Callback<SingleEmployeeResponse>() {
                    @Override
                    public void onResponse(Call<SingleEmployeeResponse> call, Response<SingleEmployeeResponse> response) {
                        if (response.isSuccessful()) {
                            progressDialog.dismiss();
                            EmployeeResult user = response.body().getEmployeeResult().get(0);
//                            Glide.with(getApplicationContext()).load(user.getProfileImage()).placeholder(R.drawable.img_add_employee).into(binding.dpImg);

                            binding.employeeIdEt.setText(user.getFullName());
                            binding.departmentEt.setText(user.getFatherName());
                            binding.dobEt.setText(user.getDateOfBirth());
                            if (user.getGender().equalsIgnoreCase("male")) {
                                binding.rbMale.setChecked(true);
                            } else if (user.getGender().equalsIgnoreCase("female")) {
                                binding.rbFemale.setChecked(true);
                            } else {
                                binding.rbOther.setChecked(true);
                            }
                            binding.mobileEt.setText(user.getMobileNumber());
                            binding.emailEt.setText(user.getEmail());
                            binding.localAddEt.setText(user.getLocalAddress());
                            binding.permAddEt.setText(user.getLocalAddress());
                        } else {
                            Toast.makeText(AddEmployeeActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<SingleEmployeeResponse> call, Throwable t) {
                        Toast.makeText(AddEmployeeActivity.this, "Not In Response", Toast.LENGTH_SHORT).show();
                    }
                });


            }


        } else if (from.equalsIgnoreCase("add")) {
            binding.textView.setText("Add Employee");
            binding.nextBtn.setText("Next");
        }
        binding.btnHome.setOnClickListener(v -> {
            finish();
        });

        binding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dpImageBoolean) {
                    Toast.makeText(getApplicationContext(), "Please Upload your Profile Image", Toast.LENGTH_SHORT).show();
                } else if (binding.employeeIdEt.getText().toString().isEmpty()) {
                    binding.employeeIdEt.setError("Enter Your Name");
                    binding.employeeIdEt.requestFocus();
                } else if (binding.departmentEt.getText().toString().isEmpty()) {
                    binding.departmentEt.setError("Enter Your Father's Name");
                    binding.departmentEt.requestFocus();
                } else if (binding.dobEt.getText().toString().isEmpty()) {
                    Toast.makeText(AddEmployeeActivity.this, "Select Your DOB", Toast.LENGTH_SHORT).show();
                } else if (!binding.rbMale.isChecked() && !binding.rbFemale.isChecked() && !binding.rbOther.isChecked()) {
                    Toast.makeText(AddEmployeeActivity.this, "Please Select Your Gender", Toast.LENGTH_SHORT).show();
                } else if (binding.mobileEt.getText().toString().trim().isEmpty()) {
                    binding.mobileEt.setError("Enter Mobile Number");
                    binding.mobileEt.requestFocus();
                } else if (binding.mobileEt.getText().toString().trim().length() < 10) {
                    binding.mobileEt.setError("Enter Correct Mobile Number");
                    binding.mobileEt.requestFocus();
                } else if (binding.emailEt.getText().toString().trim().isEmpty() ||
                        !binding.emailEt.getText().toString().trim().contains("@") ||
                        !binding.emailEt.getText().toString().trim().contains(".")) {
                    binding.emailEt.setError("Enter Correct Email");
                    binding.emailEt.requestFocus();
                } else if (binding.localAddEt.getText().toString().isEmpty()) {
                    binding.localAddEt.setError("Enter Your Address");
                    binding.localAddEt.requestFocus();
                } else if (binding.permAddEt.getText().toString().isEmpty()) {
                    binding.permAddEt.setError("Enter Your Permanent Local Address");
                    binding.permAddEt.requestFocus();
                } else {

                    startActivity(new Intent(AddEmployeeActivity.this, EmployeeIdActivity.class));

//                    PImg = new File(uripi);
                    name = binding.employeeIdEt.getText().toString();
                    fName = binding.departmentEt.getText().toString();
                    dob = binding.dobEt.getText().toString();
                    gender = "";
                    if (binding.rbMale.isChecked()) {
                        binding.rbMale.getText().toString();
                        gender = "Male";
                    } else if (binding.rbFemale.isChecked()) {
                        binding.rbFemale.getText().toString();
                        gender = "Female";
                    } else {
                        binding.rbOther.getText().toString();
                        gender = "Other";
                    }
                    finalGender = gender;
                    number = binding.mobileEt.getText().toString();
                    email = binding.emailEt.getText().toString();
                    localAddress = binding.localAddEt.getText().toString();
                    permanentAddress = binding.permAddEt.getText().toString();
//
//
//                    RequestBody proImg = RequestBody.create(MediaType.parse("image/*"), PImg);
//                    MultipartBody.Part profile_img = MultipartBody.Part.createFormData("profile_image", PImg.getName(), proImg);
//                    RequestBody xName = RequestBody.create(MediaType.parse("text/plain"), name);
//                    RequestBody xFName = RequestBody.create(MediaType.parse("text/plain"), fName);
//                    RequestBody XDOB = RequestBody.create(MediaType.parse("text/plain"), dob);
//                    RequestBody xGender = RequestBody.create(MediaType.parse("text/plain"), finalGender);
//                    RequestBody xNumber = RequestBody.create(MediaType.parse("text/plain"), number);
//                    RequestBody xEmail = RequestBody.create(MediaType.parse("text/plain"), email);
//                    RequestBody xLAddress = RequestBody.create(MediaType.parse("text/plain"), localAddress);
//                    RequestBody xPAddress = RequestBody.create(MediaType.parse("text/plain"), permanentAddress);

//
//                    Call<AddEmployeeResponse> call = apiInterface.postAddEmployee(profile_img, xName, xFName, XDOB, xGender, xNumber, xEmail, xLAddress, xPAddress);
//                    call.enqueue(new Callback<AddEmployeeResponse>() {
//                        @Override
//                        public void onResponse(Call<AddEmployeeResponse> call, Response<AddEmployeeResponse> response) {
//                            if (response.isSuccessful()) {
//                                startActivity(new Intent(AddEmployeeActivity.this, EmployeeIdActivity.class));
//                            } else {
//                                Toast.makeText(AddEmployeeActivity.this, response.message(), Toast.LENGTH_SHORT).show();
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<AddEmployeeResponse> call, Throwable t) {
//                            Toast.makeText(AddEmployeeActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                    sendDetails(uripi, name, fName, dob, finalGender, number, email, localAddress, permanentAddress);

                }
            }
        });

        binding.dobEt.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();

            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(AddEmployeeActivity.this,
                    (view, year1, monthOfYear, dayOfMonth) -> {
                        //
                        binding.dobEt.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year1);
                    },
                    year, month, day);
            datePickerDialog.show();
        });

        binding.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent imgIntent1 = new Intent(Intent.ACTION_PICK);
                imgIntent1.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(imgIntent1, 101);
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 100);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
//            if (data != null) {
//                String img;
////                binding.dpImg.setImageURI(data.getData());
//                Uri pi = Uri.parse(data.getData().toString());
//                profileImage = data.getData();
//                uripi = getRealPathFromURI(pi);
//                dpImageBoolean = true;

        if (resultCode == RESULT_OK && requestCode == 101) {
            binding.dpImg.setImageURI(data.getData());
            profileImage = data.getData();
            uripi = getRealPathFromURI(profileImage);
            dpImageBoolean = true;


        }
    }

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    private void sendDetails(String uripfs, String xName, String xFather, String xDOB,
                             String xGender, String xMobile, String xMail, String xLAddress, String xPAddress) {
        File panF = new File(uripfs);


        RequestBody pImage = RequestBody.create(MediaType.parse("image/*"), panF);
        MultipartBody.Part profile_image = MultipartBody.Part.createFormData("profile_image", panF.getName(), pImage);


        RequestBody fullname = RequestBody.create(MediaType.parse("text/plain"), xName);
        RequestBody father = RequestBody.create(MediaType.parse("text/plain"), xFather);
        RequestBody dob = RequestBody.create(MediaType.parse("text/plain"), xDOB);
        RequestBody gender = RequestBody.create(MediaType.parse("text/plain"), xGender);
        RequestBody mobile = RequestBody.create(MediaType.parse("text/plain"), xMobile);
        RequestBody mail = RequestBody.create(MediaType.parse("text/plain"), xMail);
        RequestBody lAddress = RequestBody.create(MediaType.parse("text/plain"), xLAddress);
        RequestBody pAddress = RequestBody.create(MediaType.parse("text/plain"), xPAddress);


        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);


        Call<AddEmployeeResponse> call = apiInterface.postAddEmployee(profile_image, fullname, father, dob, gender, mobile, mail, lAddress, pAddress);
        call.enqueue(new Callback<AddEmployeeResponse>() {
            @Override
            public void onResponse(Call<AddEmployeeResponse> call, Response<AddEmployeeResponse> response) {
                if (response.isSuccessful()) {
                    startActivity(new Intent(AddEmployeeActivity.this, EmployeeIdActivity.class));
                } else {
                    Toast.makeText(AddEmployeeActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddEmployeeResponse> call, Throwable t) {
                Toast.makeText(AddEmployeeActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}