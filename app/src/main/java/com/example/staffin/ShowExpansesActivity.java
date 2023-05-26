package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Build;
import android.os.Bundle;

import com.example.staffin.Adapter.ShowExpansesAdapter;
import com.example.staffin.Interface.ApiInterface;
import com.example.staffin.Response.AllExpenses;
import com.example.staffin.Retrofit.RetrofitServices;
import com.example.staffin.databinding.ActivityShowExpansesBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowExpansesActivity extends AppCompatActivity {
    ActivityShowExpansesBinding binding;
    List<String> img;

    AllExpenses.GetAllExpenseDetail singleUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowExpansesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        img = new ArrayList<>();
        img = getIntent().getExtras().getStringArrayList("img");
        singleUnit = (AllExpenses.GetAllExpenseDetail) getIntent().getExtras().getSerializable("listofimages");
        binding.showRecyclerView.setLayoutManager(new GridLayoutManager(ShowExpansesActivity.this, 2));
        binding.showRecyclerView.setAdapter(new ShowExpansesAdapter(ShowExpansesActivity.this, img));


    }
}