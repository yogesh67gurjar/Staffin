package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.staffin.Interface.ApiInterface;
import com.example.staffin.Response.AddPasswordForEmployee;
import com.example.staffin.Retrofit.RetrofitServices;
import com.example.staffin.databinding.ActivityAddEmployeeBinding;
import com.example.staffin.databinding.ActivityEmployeeIdBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeIdActivity extends AppCompatActivity {
    ActivityEmployeeIdBinding binding;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEmployeeIdBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        String employeeID = getIntent().getStringExtra("empid");
        String Id = getIntent().getStringExtra("id");

        binding.userIdEt.setText(employeeID);

        binding.btnBack.setOnClickListener(v -> {
            onBackPressed();
        });

        binding.btnNext.setOnClickListener(v -> {
            if (binding.userIdEt.getText().toString().trim().isEmpty()) {
                binding.userIdEt.setError("Enter Your Id");
                binding.userIdEt.requestFocus();
            } else if (binding.passwordEt.getText().toString().trim().isEmpty()) {
                binding.passwordEt.setError("Enter Your Password");
                binding.passwordEt.requestFocus();
            } else {
                String password = binding.passwordEt.getText().toString();

//                Call<AddPasswordForEmployee> call = apiInterface.postSinglePasswordEmployee(password, employeeID);
//                call.enqueue(new Callback<AddPasswordForEmployee>() {
//                    @Override
//                    public void onResponse(Call<AddPasswordForEmployee> call, Response<AddPasswordForEmployee> response) {
//                        if (response.isSuccessful()) {
//                            Intent intent = new Intent(getApplicationContext(), CompanyDetailsActivity.class);
//                            intent.putExtra("empid", employeeID);
//                            intent.putExtra("id", Id);
//                            startActivity(intent);
//
//                        } else {
//                            Toast.makeText(EmployeeIdActivity.this, "OnResponse Fail", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<AddPasswordForEmployee> call, Throwable t) {
//                        Toast.makeText(EmployeeIdActivity.this, "Failure", Toast.LENGTH_SHORT).show();
//                    }
//                });
                Intent intent = new Intent(getApplicationContext(), CompanyDetailsActivity.class);
                startActivity(intent);
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