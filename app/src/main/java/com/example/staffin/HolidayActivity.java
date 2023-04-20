package com.example.staffin;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.staffin.Interface.ApiInterface;
import com.example.staffin.Response.HolidayResponse;
import com.example.staffin.Retrofit.RetrofitServices;
import com.example.staffin.databinding.ActivityHolidayBinding;

import java.util.Arrays;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HolidayActivity extends AppCompatActivity{

    ActivityHolidayBinding binding;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHolidayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        clickListeners();
    }

    private void clickListeners() {
        binding.btnBack.setOnClickListener(v1 -> {
            finish();
        });

        binding.btnNext.setOnClickListener(v -> {

            if (binding.occasionEt.getText().toString().isEmpty()) {
                binding.occasionEt.setError("Enter Occasion Name");
                binding.occasionEt.requestFocus();
            } else if (binding.jDateEt.getText().toString().isEmpty()) {
                Toast.makeText(this, "Enter Date Of Occasion", Toast.LENGTH_SHORT).show();
            } else if (binding.holidayDesEt.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please Enter Description", Toast.LENGTH_SHORT).show();
            } else {
                String date = binding.jDateEt.getText().toString();
                Log.i("Date Kya hai", date);
                String[] dateSplit = date.split("-");
                for (int i = 0; i < dateSplit.length / 2; i++) {
                    String datex = dateSplit[dateSplit.length - 1 - i];
                    String yearx = dateSplit[i];
                    dateSplit[i] = datex;
                    dateSplit[dateSplit.length - 1 - i] = yearx;
                }
                StringBuilder finalDate = new StringBuilder();
                for (String da : dateSplit) {
                    finalDate.append(da).append("-");
                }
                Log.i("rev date Kya hai", Arrays.toString(dateSplit));
                String occasion = binding.occasionEt.getText().toString();
                Log.i("occasion Kya hai", occasion);
                String desc = binding.holidayDesEt.getText().toString();
                Log.i("desc Kya hai", desc);
                Call<HolidayResponse> callPostHoliday = apiInterface.postHoliday(String.valueOf(finalDate), occasion, desc);
                ProgressDialog dialog = new ProgressDialog(HolidayActivity.this);
                dialog.setMessage("Loading....");
                dialog.show();
                callPostHoliday.enqueue(new Callback<HolidayResponse>() {
                    @Override
                    public void onResponse(Call<HolidayResponse> call, Response<HolidayResponse> response) {
                        if (response.isSuccessful()) {
                            dialog.dismiss();
                            startActivity(new Intent(getApplicationContext(), CalendarSettingActivity.class));
                            finish();
                            Toast.makeText(HolidayActivity.this, "Holiday Created Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            dialog.dismiss();

                            // date ka format galat he
                            Log.d("gnkdg", String.valueOf(response.code()));
                            Log.d("gnkdg", response.message());
                            Toast.makeText(HolidayActivity.this, "Try again", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<HolidayResponse> call, Throwable t) {
                        Log.d("dfsdkfns", t.getMessage());
                        dialog.dismiss();

                        Toast.makeText(HolidayActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                    }
                });

            }

        });


        binding.jDateEt.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();

            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(HolidayActivity.this,
                    (view, year1, monthOfYear, dayOfMonth) -> {
                        //
                        binding.jDateEt.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year1);
                    },
                    year, month, day);
            datePickerDialog.show();
        });

    }

}