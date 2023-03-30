package com.example.staffin;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.staffin.Interface.ApiInterface;
import com.example.staffin.Retrofit.RetrofitServices;
import com.example.staffin.databinding.ActivityEmployeeIdBinding;

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
                if (from.equalsIgnoreCase("add")) {
                    String password = binding.passwordEt.getText().toString();

                    //$$$$$$$$$

//                    Call<AddPasswordForEmployee> call = apiInterface.postSinglePasswordEmployee(password, empId);
//                    call.enqueue(new Callback<AddPasswordForEmployee>() {
//                        @Override
//                        public void onResponse(Call<AddPasswordForEmployee> call, Response<AddPasswordForEmployee> response) {
//                            if (response.isSuccessful()) {
//                                Intent intent = new Intent(getApplicationContext(), CompanyDetailsActivity.class);
//                                intent.putExtra("empId", empId);
//                                intent.putExtra("Id", Id);
//                                intent.putExtra("from", "add");
//                                startActivity(intent);
//
//                            } else {
//                                Toast.makeText(EmployeeIdActivity.this, "OnResponse Fail", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<AddPasswordForEmployee> call, Throwable t) {
//                            Toast.makeText(EmployeeIdActivity.this, "Failure", Toast.LENGTH_SHORT).show();
//                        }
//                    });
                    Intent intent = new Intent(getApplicationContext(), CompanyDetailsActivity.class);
                    intent.putExtra("empId", empId);
                    intent.putExtra("Id", Id);
                    intent.putExtra("from", "add");
                    startActivity(intent);
                }
                else
                {
                    // edit employee id ki api lgegi
                    // progress dialog
                    Intent intent = new Intent(getApplicationContext(), CompanyDetailsActivity.class);
                    intent.putExtra("empId", empId);
                    intent.putExtra("Id", Id);
                    intent.putExtra("from", "edit");
                    startActivity(intent);
                }
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
}