package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.staffin.Interface.ApiInterface;
import com.example.staffin.Response.HolidayResponse;
import com.example.staffin.Retrofit.RetrofitServices;
import com.example.staffin.databinding.ActivityHolidayBinding;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HolidayActivity extends AppCompatActivity {

    ActivityHolidayBinding binding;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHolidayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        binding.btnNext.setOnClickListener(v -> {

            if (binding.occasionEt.getText().toString().isEmpty()) {
                binding.occasionEt.setError("Enter Occasion Name");
                binding.occasionEt.requestFocus();
            } else if (binding.jDateEt.getText().toString().isEmpty()) {
                Toast.makeText(this, "Enter Date Of Occasion", Toast.LENGTH_SHORT).show();
            } else {
                String date = binding.jDateEt.getText().toString();
                Log.i("Date Kya hai", date);
                String occasion = binding.occasionEt.getText().toString();
                Log.i("occasion Kya hai", occasion);
                Call<HolidayResponse> call = apiInterface.postHoliday(date, occasion);
                call.enqueue(new Callback<HolidayResponse>() {
                    @Override
                    public void onResponse(Call<HolidayResponse> call, Response<HolidayResponse> response) {
                        if (response.isSuccessful()) {
                            startActivity(new Intent(getApplicationContext(), CalendarSettingActivity.class));
                            Toast.makeText(HolidayActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(HolidayActivity.this, "Try again", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<HolidayResponse> call, Throwable t) {
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