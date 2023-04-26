package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.staffin.Interface.ApiInterface;
import com.example.staffin.Response.AllPayroll;
import com.example.staffin.Response.PaySlipResponse;
import com.example.staffin.Response.PayslipDetail;
import com.example.staffin.Retrofit.RetrofitServices;
import com.example.staffin.databinding.ActivitySalaryInfoBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalaryInfoActivity extends AppCompatActivity {
    ActivitySalaryInfoBinding binding;
    ApiInterface apiInterface;
    int IdI = 0;

    String[] Status = {"Paid", "Unpaid"};
    String statusSelectedPaid, statusSelectedUnpaid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySalaryInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        IdI = getIntent().getIntExtra("IdI", 0);

        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        setOnClickListener();
        getApi();
    }

    private void getApi() {

        Call<PaySlipResponse> callProfileDetails = apiInterface.getPaySlip(IdI);
        callProfileDetails.enqueue(new Callback<PaySlipResponse>() {
            @Override
            public void onResponse(Call<PaySlipResponse> call, Response<PaySlipResponse> response) {
                if (response.isSuccessful()) {
                    List<AllPayroll.AllPayslipDetail.EmployeeId> resp = response.body().getPayslipDetails().get(0).getEmployeeId();
                    List<PayslipDetail> resp2 = response.body().getPayslipDetails();
                    Glide.with(getApplicationContext()).load(resp.get(0).getProfileImageUrl()).placeholder(R.drawable.img_dp).into(binding.dpImg);
                    binding.nameTv.setText(resp.get(0).getFullName());
                    binding.empId.setText("Emp Id:-" + resp.get(0).getEmployeeID());
                    binding.employeeIdEt.setText(resp2.get(0).getDaily_rate());
                    binding.departmentEt.setText(resp2.get(0).getTotal_working_day());
                    binding.jDateEt.setText(resp2.get(0).getOvertimeHours());
                    binding.rDateEt.setText(resp2.get(0).getOvertimePay());
                    binding.EtBasicSalary.setText(resp2.get(0).getBasic());
                    binding.EtExpenseClaim.setText(resp2.get(0).getExpense());
                    binding.EtBonus.setText(resp2.get(0).getBonus());
                    binding.EtDuration.setText(resp2.get(0).getTotalDeduction());
                    binding.EtNetSalary.setText(resp2.get(0).getNetSalary());

                    ArrayAdapter aa = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, Status);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.spinner.setAdapter(aa);
                    binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (resp2.get(0).getStatus() == "paid") {
                                statusSelectedPaid = Status[0];
                            } else {
                                statusSelectedUnpaid = Status[1];
                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                } else {
                    Toast.makeText(SalaryInfoActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PaySlipResponse> call, Throwable t) {
                Toast.makeText(SalaryInfoActivity.this, "Server Error", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void setOnClickListener() {
        binding.btnHome.setOnClickListener(v -> {
            finish();
        });

        binding.nextBtn.setOnClickListener(v -> {
            finish();
        });
    }
}