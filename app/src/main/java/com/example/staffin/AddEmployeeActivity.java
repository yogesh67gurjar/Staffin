package com.example.staffin;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.staffin.Interface.ApiInterface;
import com.example.staffin.Response.AddEmployeeDetails;
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
import retrofit2.http.Field;
import retrofit2.http.Path;

public class AddEmployeeActivity extends AppCompatActivity {

    private static final int REQUEST_STORAGE_PERMISSION = 200;
    ActivityAddEmployeeBinding binding;
    ApiInterface apiInterface;

    Boolean dpImageBoolean = false;
    String from = "";
    int Id = 0;
    String empId = "";

    Uri profileImage, editProfileImage;
 
    String uripi = " ";
    File PImg;
    String name, fName, dob, gender, email, localAddress, permanentAddress, finalGender, number;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 101) {
            binding.dpImg.setImageURI(data.getData());
            profileImage = data.getData();
            uripi = getRealPathFromURI(profileImage);
            dpImageBoolean = true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddEmployeeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        clickListeners();
        final ProgressDialog progressDialog = new ProgressDialog(AddEmployeeActivity.this);
        progressDialog.setMessage("Loading...");

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            // Permission already granted, access the storage here
        } else {
            // Permission not granted, request it
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_STORAGE_PERMISSION);
        }

        if (from.equalsIgnoreCase("edit")) {
            binding.textView.setText("Edit Employee");
            binding.nextBtn.setText("Next");
            progressDialog.show();
            Id = getIntent().getIntExtra("Id", 0);
            empId = getIntent().getStringExtra("empId");
            if (Id == 0) {
                Toast.makeText(this, "Some Error while fetching Id", Toast.LENGTH_SHORT).show();
            } else {
                if (isNetworkAvailable()) {

                    Call<SingleEmployeeResponse> callGetSingleEmployee = apiInterface.getSingleEmployee(Id);
                    callGetSingleEmployee.enqueue(new Callback<SingleEmployeeResponse>() {
                        @Override
                        public void onResponse(Call<SingleEmployeeResponse> call, Response<SingleEmployeeResponse> response) {
                            if (response.isSuccessful()) {
                                progressDialog.dismiss();
                                EmployeeResult user = response.body().getEmployeeResult().get(0);
                                Glide.with(getApplicationContext()).load(user.getProfileImage()).placeholder(R.drawable.img_dp).into(binding.dpImg);
                                binding.employeeIdEt.setText(user.getFullName());
                                binding.departmentEt.setText(user.getFatherName());

                                String[] DOB = user.getDateOfBirth().split("T");
                                binding.dobEt.setText(DOB[0]);

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
                                progressDialog.dismiss();
                                Log.d("dkfnsdf", response.message());
                                Toast.makeText(AddEmployeeActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<SingleEmployeeResponse> call, Throwable t) {
                            Log.d("nsdfsdf", t.getMessage());
                            progressDialog.dismiss();
                            Toast.makeText(AddEmployeeActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(this, "Internet Not Available", Toast.LENGTH_SHORT).show();
                }
            }


        } else if (from.equalsIgnoreCase("add")) {
            binding.textView.setText("Add Employee");
            binding.nextBtn.setText("Next");
        }
    }

    private void clickListeners() {
        from = getIntent().getStringExtra("from");
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);

        binding.btnHome.setOnClickListener(v -> {
            finish();
        });

        binding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.employeeIdEt.getText().toString().isEmpty()) {
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

                    if (from.equalsIgnoreCase("edit")) {
                        updateDetails(Id, uripi, name, fName, dob, finalGender, number, email, localAddress, permanentAddress);
                    } else {
                        if (!dpImageBoolean) {
                            Toast.makeText(getApplicationContext(), "Please Upload your Profile Image", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            sendDetails(uripi, name, fName, dob, finalGender, number, email, localAddress, permanentAddress);
                        }
                    }
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
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_STORAGE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("PERMS1", "GRANTEd");
            } else {
                Log.d("PERMS1", "Denied");

                // Permission denied, handle the case where the user denies the permission
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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

    private void updateDetails(int id, String uripi, String name, String fName, String xdob, String finalGender, String number, String email, String localAddress, String permanentAddress) {
        PImg = new File(uripi);
        Log.d("fdlskfsdf", PImg.toString());
        RequestBody proImg = RequestBody.create(MediaType.parse("image/*"), PImg);
        MultipartBody.Part profile_image = MultipartBody.Part.createFormData("profile_image", PImg.getName(), proImg);

        RequestBody fullname = RequestBody.create(MediaType.parse("text/plain"), name);
        RequestBody father = RequestBody.create(MediaType.parse("text/plain"), fName);
        RequestBody dob = RequestBody.create(MediaType.parse("text/plain"), xdob);
        RequestBody mobile = RequestBody.create(MediaType.parse("text/plain"), number);
        RequestBody gender = RequestBody.create(MediaType.parse("text/plain"), finalGender);
        RequestBody mail = RequestBody.create(MediaType.parse("text/plain"), email);
        RequestBody lAddress = RequestBody.create(MediaType.parse("text/plain"), localAddress);
        RequestBody pAddress = RequestBody.create(MediaType.parse("text/plain"), permanentAddress);

        final ProgressDialog progressDialog = new ProgressDialog(AddEmployeeActivity.this);
        progressDialog.setMessage("Loading...");


        Call<AddEmployeeResponse> callUpdateEmployee = apiInterface.postUpdateEmployee(id, profile_image, fullname, father, dob, mobile, gender, mail, lAddress, pAddress);

        if (dpImageBoolean) {
            PImg = new File(uripi);


            RequestBody proImg = RequestBody.create(MediaType.parse("image/*"), PImg);
            MultipartBody.Part profile_img = MultipartBody.Part.createFormData("profile_image", PImg.getName(), proImg);

            Call<AddEmployeeResponse> callUpdateEmployee = apiInterface.postUpdateEmployee(id, profile_img, fullname, father, dob, mobile, gender, mail, lAddress, pAddress);
            if (isNetworkAvailable()) {
                progressDialog.show();
                callUpdateEmployee.enqueue(new Callback<AddEmployeeResponse>() {
                    @Override
                    public void onResponse(Call<AddEmployeeResponse> call, Response<AddEmployeeResponse> response) {
                        if (response.isSuccessful()) {

                            progressDialog.dismiss();
                            Intent intent = new Intent(getApplicationContext(), EmployeeIdActivity.class);
                            intent.putExtra("empId", empId);
                            intent.putExtra("Id", Id);
                            intent.putExtra("from", "edit");

                            startActivity(intent);
                        } else {
                            Log.d("fkdjfnsdf", response.message());
                            progressDialog.dismiss();
                            Toast.makeText(AddEmployeeActivity.this, "Try again", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AddEmployeeResponse> call, Throwable t) {
                        Log.d("Pdkjfnsdf", t.getMessage());
                        progressDialog.dismiss();
                        Toast.makeText(AddEmployeeActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(this, "Internet Not Available", Toast.LENGTH_SHORT).show();
            }
//           image le k api chlegi
        } else {

            Call<AddEmployeeResponse> callPostUpdateEmployeeWithoutImage = apiInterface.postUpdateEmployeeWithoutImage(id, name, fName, xdob, number, finalGender, email, localAddress, permanentAddress);
            if (isNetworkAvailable()) {
                progressDialog.show();
                callPostUpdateEmployeeWithoutImage.enqueue(new Callback<AddEmployeeResponse>() {
                    @Override
                    public void onResponse(Call<AddEmployeeResponse> call, Response<AddEmployeeResponse> response) {
                        if (response.isSuccessful()) {
                            progressDialog.dismiss();
                            Intent intent = new Intent(getApplicationContext(), EmployeeIdActivity.class);
                            intent.putExtra("empId", empId);
                            intent.putExtra("Id", Id);
                            intent.putExtra("from", "edit");

                            startActivity(intent);
                        } else {
                            Log.d("fkgfdgddjfnsdf", response.message());
                            progressDialog.dismiss();
                            Toast.makeText(AddEmployeeActivity.this, "Try again", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AddEmployeeResponse> call, Throwable t) {
                        Log.d("dfgdfgd", t.getMessage());
                        progressDialog.dismiss();
                        Toast.makeText(AddEmployeeActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(this, "Internet Not Available", Toast.LENGTH_SHORT).show();
            }
//           image k bina api chlegi
        }

    }

    private void sendDetails(String profile_image, String xName, String xFather, String xDOB,
                             String xMobile, String xGender, String xMail, String xLAddress, String xPAddress) {

        PImg = new File(profile_image);
        Log.d("fdlskfsdf", PImg.toString());
        RequestBody proImg = RequestBody.create(MediaType.parse("image/*"), PImg);
        MultipartBody.Part profile_img = MultipartBody.Part.createFormData("profile_image", PImg.getName(), proImg);

        Log.d("1", PImg.toString());
        Log.d("2", profile_image);
        Log.d("3", proImg.toString());
        Log.d("4", profile_img.toString());


        RequestBody fullname = RequestBody.create(MediaType.parse("text/plain"), xName);
        RequestBody father = RequestBody.create(MediaType.parse("text/plain"), xFather);
        RequestBody dob = RequestBody.create(MediaType.parse("text/plain"), xDOB);
        RequestBody mobile = RequestBody.create(MediaType.parse("text/plain"), xMobile);
        RequestBody gender = RequestBody.create(MediaType.parse("text/plain"), xGender);
        RequestBody mail = RequestBody.create(MediaType.parse("text/plain"), xMail);
        RequestBody lAddress = RequestBody.create(MediaType.parse("text/plain"), xLAddress);
        RequestBody pAddress = RequestBody.create(MediaType.parse("text/plain"), xPAddress);

        final ProgressDialog progressDialog = new ProgressDialog(AddEmployeeActivity.this);
        progressDialog.setMessage("Loading...");


        Log.d("2222", profile_img.body().toString());
        Log.d("1111", profile_img.toString());
        Log.d("1111", fullname.toString());
        Log.d("1111", dob.toString());
        Log.d("1111", gender.toString());
        Log.d("1111", mobile.toString());
        Log.d("1111", mail.toString());
        Log.d("1111", lAddress.toString());
        Log.d("1111", pAddress.toString());
        Call<AddEmployeeResponse> callPostAddEmployee = apiInterface.postAddEmployee(profile_img, fullname, father, dob, gender, mobile, mail, lAddress, pAddress);

        if (isNetworkAvailable()) {
            progressDialog.show();
            callPostAddEmployee.enqueue(new Callback<AddEmployeeResponse>() {
                @Override
                public void onResponse(Call<AddEmployeeResponse> call, Response<AddEmployeeResponse> response) {
                    if (response.isSuccessful()) {
                        AddEmployeeDetails resp = response.body().getEmployeeID().get(0);


                        int Id = resp.getId();

                        progressDialog.dismiss();
                        String empID = resp.getEmployeeID();
                        Intent intent = new Intent(getApplicationContext(), EmployeeIdActivity.class);
                        intent.putExtra("empId", empID);
                        intent.putExtra("Id", Id);
                        intent.putExtra("from", "add");

                        startActivity(intent);
                    } else {
                        Log.d("fkdjfnsdf", response.message());
                        progressDialog.dismiss();
                        Toast.makeText(AddEmployeeActivity.this, "Try again", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<AddEmployeeResponse> call, Throwable t) {
                    Log.d("Pdkjfnsdf", t.getMessage());
                    progressDialog.dismiss();
                    Toast.makeText(AddEmployeeActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Internet Not Available", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}