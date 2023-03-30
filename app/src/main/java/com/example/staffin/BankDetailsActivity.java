package com.example.staffin;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.staffin.Interface.ApiInterface;
import com.example.staffin.databinding.ActivityBankDetailsBinding;

public class BankDetailsActivity extends AppCompatActivity {
    ActivityBankDetailsBinding binding;

    String from;
    int Id;
    String empId;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBankDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        from = getIntent().getStringExtra("from");
        empId = getIntent().getStringExtra("empId");
        Id = getIntent().getIntExtra("Id", 0);

//        if (from.equalsIgnoreCase("edit")) {
//            Call<SingleEmployeeResponse> call = apiInterface.getSingleEmployee(Id);
//            call.enqueue(new Callback<SingleEmployeeResponse>() {
//                @Override
//                public void onResponse(Call<SingleEmployeeResponse> call, Response<SingleEmployeeResponse> response) {
//
//                }
//
//                @Override
//                public void onFailure(Call<SingleEmployeeResponse> call, Throwable t) {
//
//                }
//            });
//        }

        binding.btnBack.setOnClickListener(v -> {
            onBackPressed();
        });

        binding.nextBtn.setOnClickListener(v -> {
            if (binding.holderEt.getText().toString().isEmpty()) {
                binding.holderEt.setError("Enter Holder Name");
                binding.holderEt.requestFocus();
            } else if (binding.accNoEt.getText().toString().trim().isEmpty()) {
                binding.accNoEt.setError("Enter Account Number");
                binding.accNoEt.requestFocus();
//            } else if (binding.ifscEt.getText().toString().trim().isEmpty()) {
//                binding.ifscEt.setError("Enter IFSC Code");
//                binding.ifscEt.requestFocus();
            } else if (binding.bankEt.getText().toString().trim().isEmpty()) {
                binding.bankEt.setError("Enter Bank Name");
                binding.bankEt.requestFocus();
            } else {

                if (from.equalsIgnoreCase("add")) {

//                    Call<BankDetailsResponse> call=apiInterface.postSingleBankDetails(Id,)
//                    Call<BankDetailsResponse> postSingleBankDetails(@Path("id") int id,
//                    @Field("employee_id") String employee_id,
//                    @Field("account_name") String account_name,
//                    @Field("account_number") String account_number,
//                    @Field("bank") String bank,
//                    @Field("branch") String branch);

                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                    Toast.makeText(this, "New Employee Added...", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                    Toast.makeText(this, "Employee Details Updated...", Toast.LENGTH_SHORT).show();
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