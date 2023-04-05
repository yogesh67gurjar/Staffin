package com.example.staffin;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.staffin.Interface.ApiInterface;
import com.example.staffin.Response.AddPasswordForEmployee;
import com.example.staffin.Retrofit.RetrofitServices;
import com.example.staffin.databinding.ActivityEmployeeIdBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeIdActivity extends AppCompatActivity {
    ActivityEmployeeIdBinding binding;
    ApiInterface apiInterface;
    String from;
    String empId;
    int Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEmployeeIdBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        from = getIntent().getStringExtra("from");

        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        empId = getIntent().getStringExtra("empId");
        Id = getIntent().getIntExtra("Id", 0);
        binding.userIdEt.setText(empId);

        if (from.equalsIgnoreCase("edit")) {
            // progress
            // password me settext password
        }

        binding.btnBack.setOnClickListener(v -> {
            onBackPressed();
        });

        binding.btnNext.setOnClickListener(v -> {
//            if (binding.userIdEt.getText().toString().trim().isEmpty()) {
//                binding.userIdEt.setError("Enter Your Id");
//                binding.userIdEt.requestFocus();
//            } else
            if (binding.passwordEt.getText().toString().trim().isEmpty()) {
                binding.passwordEt.setError("Enter Your Password");
                binding.passwordEt.requestFocus();
            } else {
                if (isNetworkAvailable()) {
                    if (from.equalsIgnoreCase("add")) {
                        String password = binding.passwordEt.getText().toString();
                        Call<AddPasswordForEmployee> callPostSinglePasswordEmployee = apiInterface.postSinglePasswordEmployee(password, empId);
                        callPostSinglePasswordEmployee.enqueue(new Callback<AddPasswordForEmployee>() {
                            @Override
                            public void onResponse(Call<AddPasswordForEmployee> call, Response<AddPasswordForEmployee> response) {
                                if (response.isSuccessful()) {
                                    Intent intent = new Intent(getApplicationContext(), CompanyDetailsActivity.class);
                                    intent.putExtra("empId", empId);
                                    intent.putExtra("Id", Id);
                                    intent.putExtra("from", "add");
                                    startActivity(intent);

                                } else {
                                    Toast.makeText(EmployeeIdActivity.this, "OnResponse Fail", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<AddPasswordForEmployee> call, Throwable t) {
                                Toast.makeText(EmployeeIdActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                            }
                        });


                    } else {
                        // yha pe already sb edittext filled milenge qki get api lgegi user ki
                        // edit employee id ki api lgegi
                        // progress dialog
                        Intent intent = new Intent(getApplicationContext(), CompanyDetailsActivity.class);
                        intent.putExtra("empId", empId);
                        intent.putExtra("Id", Id);
                        intent.putExtra("from", "edit");
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(this, "Internet Not Available", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
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
}