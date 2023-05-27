package com.example.staffin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.staffin.Adapter.ShowExpansesAdapter;
import com.example.staffin.Interface.ApiInterface;
import com.example.staffin.Response.AllExpenses;
import com.example.staffin.Response.Hao;
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
    List<String> images;

    AllExpenses.GetAllExpenseDetail singleUnit1;
    String ID;

    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowExpansesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        img = new ArrayList<>();
        images = new ArrayList<>();
        img = getIntent().getExtras().getStringArrayList("img");
        ID = getIntent().getStringExtra("ID");
        Log.e("Idddddd", ID);
        singleUnit1 = (AllExpenses.GetAllExpenseDetail) getIntent().getExtras().getSerializable("listofimages");
        Call<Hao> haoCall = apiInterface.getExpenses(Integer.parseInt(ID));
        haoCall.enqueue(new Callback<Hao>() {
            @Override
            public void onResponse(Call<Hao> call, Response<Hao> response) {
                if (response.isSuccessful()) {
                    List<Hao.GetAllExpenseDetail> singleUnit = response.body().getGetAllExpenseDetails();
                    images.clear();

                    if (!singleUnit.get(0).getImage1().substring(singleUnit.get(0).getImage1().lastIndexOf("/")).equals("0") &&
                            !singleUnit.get(0).getImage2().substring(singleUnit.get(0).getImage2().lastIndexOf("/")).equals("0") &&
                            !singleUnit.get(0).getImage3().substring(singleUnit.get(0).getImage3().lastIndexOf("/")).equals("0") &&
                            !singleUnit.get(0).getImage4().substring(singleUnit.get(0).getImage4().lastIndexOf("/")).equals("0") &&

                            !singleUnit.get(0).getImage5().substring(singleUnit.get(0).getImage5().lastIndexOf("/")).equals("0") &&

                            !singleUnit.get(0).getImage6().substring(singleUnit.get(0).getImage6().lastIndexOf("/")).equals("0") &&

                            !singleUnit.get(0).getImage7().substring(singleUnit.get(0).getImage7().lastIndexOf("/")).equals("0") &&

                            !singleUnit.get(0).getImage8().substring(singleUnit.get(0).getImage8().lastIndexOf("/")).equals("0") &&

                            !singleUnit.get(0).getImage9().substring(singleUnit.get(0).getImage9().lastIndexOf("/")).equals("0") &&

                            !singleUnit.get(0).getImage10().substring(singleUnit.get(0).getImage10().lastIndexOf("/")).equals("0")) {
                        images.clear();
                        images.add(singleUnit.get(0).getImage1());
                        images.add(singleUnit.get(0).getImage2());
                        images.add(singleUnit.get(0).getImage3());
                        images.add(singleUnit.get(0).getImage4());
                        images.add(singleUnit.get(0).getImage5());
                        images.add(singleUnit.get(0).getImage6());
                        images.add(singleUnit.get(0).getImage7());
                        images.add(singleUnit.get(0).getImage8());
                        images.add(singleUnit.get(0).getImage9());
                        images.add(singleUnit.get(0).getImage10());
                    } else if (!singleUnit.get(0).getImage1().substring(singleUnit.get(0).getImage1().lastIndexOf("/")).equals("0") &&
                            !singleUnit.get(0).getImage2().substring(singleUnit.get(0).getImage2().lastIndexOf("/")).equals("0") &&
                            !singleUnit.get(0).getImage3().substring(singleUnit.get(0).getImage3().lastIndexOf("/")).equals("0") &&
                            !singleUnit.get(0).getImage4().substring(singleUnit.get(0).getImage4().lastIndexOf("/")).equals("0") &&

                            !singleUnit.get(0).getImage5().substring(singleUnit.get(0).getImage5().lastIndexOf("/")).equals("0") &&

                            !singleUnit.get(0).getImage6().substring(singleUnit.get(0).getImage6().lastIndexOf("/")).equals("0") &&

                            !singleUnit.get(0).getImage7().substring(singleUnit.get(0).getImage7().lastIndexOf("/")).equals("0") &&

                            !singleUnit.get(0).getImage8().substring(singleUnit.get(0).getImage8().lastIndexOf("/")).equals("0") &&

                            !singleUnit.get(0).getImage9().substring(singleUnit.get(0).getImage9().lastIndexOf("/")).equals("0") &&

                            singleUnit.get(0).getImage10().substring(singleUnit.get(0).getImage10().lastIndexOf("/")).equals("0")) {
                        images.clear();
                        images.add(singleUnit.get(0).getImage1());
                        images.add(singleUnit.get(0).getImage2());
                        images.add(singleUnit.get(0).getImage3());
                        images.add(singleUnit.get(0).getImage4());
                        images.add(singleUnit.get(0).getImage5());
                        images.add(singleUnit.get(0).getImage6());
                        images.add(singleUnit.get(0).getImage7());
                        images.add(singleUnit.get(0).getImage8());
                        images.add(singleUnit.get(0).getImage9());
                    } else if (!singleUnit.get(0).getImage1().substring(singleUnit.get(0).getImage1().lastIndexOf("/")).equals("0") &&
                            !singleUnit.get(0).getImage2().substring(singleUnit.get(0).getImage2().lastIndexOf("/")).equals("0") &&
                            !singleUnit.get(0).getImage3().substring(singleUnit.get(0).getImage3().lastIndexOf("/")).equals("0") &&
                            !singleUnit.get(0).getImage4().substring(singleUnit.get(0).getImage4().lastIndexOf("/")).equals("0") &&

                            !singleUnit.get(0).getImage5().substring(singleUnit.get(0).getImage5().lastIndexOf("/")).equals("0") &&

                            !singleUnit.get(0).getImage6().substring(singleUnit.get(0).getImage6().lastIndexOf("/")).equals("0") &&

                            !singleUnit.get(0).getImage7().substring(singleUnit.get(0).getImage7().lastIndexOf("/")).equals("0") &&

                            !singleUnit.get(0).getImage8().substring(singleUnit.get(0).getImage8().lastIndexOf("/")).equals("0") &&

                            singleUnit.get(0).getImage9().substring(singleUnit.get(0).getImage9().lastIndexOf("/")).equals("0") &&

                            singleUnit.get(0).getImage10().substring(singleUnit.get(0).getImage10().lastIndexOf("/")).equals("0")) {
                        images.clear();
                        images.add(singleUnit.get(0).getImage1());
                        images.add(singleUnit.get(0).getImage2());
                        images.add(singleUnit.get(0).getImage3());
                        images.add(singleUnit.get(0).getImage4());
                        images.add(singleUnit.get(0).getImage5());
                        images.add(singleUnit.get(0).getImage6());
                        images.add(singleUnit.get(0).getImage7());
                        images.add(singleUnit.get(0).getImage8());
                    } else if (!singleUnit.get(0).getImage1().substring(singleUnit.get(0).getImage1().lastIndexOf("/")).equals("0") &&
                            !singleUnit.get(0).getImage2().substring(singleUnit.get(0).getImage2().lastIndexOf("/")).equals("0") &&
                            !singleUnit.get(0).getImage3().substring(singleUnit.get(0).getImage3().lastIndexOf("/")).equals("0") &&
                            !singleUnit.get(0).getImage4().substring(singleUnit.get(0).getImage4().lastIndexOf("/")).equals("0") &&

                            !singleUnit.get(0).getImage5().substring(singleUnit.get(0).getImage5().lastIndexOf("/")).equals("0") &&

                            !singleUnit.get(0).getImage6().substring(singleUnit.get(0).getImage6().lastIndexOf("/")).equals("0") &&

                            !singleUnit.get(0).getImage7().substring(singleUnit.get(0).getImage7().lastIndexOf("/")).equals("0") &&

                            singleUnit.get(0).getImage8().substring(singleUnit.get(0).getImage8().lastIndexOf("/")).equals("0") &&

                            singleUnit.get(0).getImage9().substring(singleUnit.get(0).getImage9().lastIndexOf("/")).equals("0") &&

                            singleUnit.get(0).getImage10().substring(singleUnit.get(0).getImage10().lastIndexOf("/")).equals("0")) {
                        images.clear();
                        images.add(singleUnit.get(0).getImage1());
                        images.add(singleUnit.get(0).getImage2());
                        images.add(singleUnit.get(0).getImage3());
                        images.add(singleUnit.get(0).getImage4());
                        images.add(singleUnit.get(0).getImage5());
                        images.add(singleUnit.get(0).getImage6());
                        images.add(singleUnit.get(0).getImage7());
                    } else if (!singleUnit.get(0).getImage1().substring(singleUnit.get(0).getImage1().lastIndexOf("/")).equals("0") &&
                            !singleUnit.get(0).getImage2().substring(singleUnit.get(0).getImage2().lastIndexOf("/")).equals("0") &&
                            !singleUnit.get(0).getImage3().substring(singleUnit.get(0).getImage3().lastIndexOf("/")).equals("0") &&
                            !singleUnit.get(0).getImage4().substring(singleUnit.get(0).getImage4().lastIndexOf("/")).equals("0") &&

                            !singleUnit.get(0).getImage5().substring(singleUnit.get(0).getImage5().lastIndexOf("/")).equals("0") &&

                            !singleUnit.get(0).getImage6().substring(singleUnit.get(0).getImage6().lastIndexOf("/")).equals("0") &&

                            singleUnit.get(0).getImage7().substring(singleUnit.get(0).getImage7().lastIndexOf("/")).equals("0") &&

                            singleUnit.get(0).getImage8().substring(singleUnit.get(0).getImage8().lastIndexOf("/")).equals("0") &&

                            singleUnit.get(0).getImage9().substring(singleUnit.get(0).getImage9().lastIndexOf("/")).equals("0") &&

                            singleUnit.get(0).getImage10().substring(singleUnit.get(0).getImage10().lastIndexOf("/")).equals("0")) {
                        images.clear();
                        images.add(singleUnit.get(0).getImage1());
                        images.add(singleUnit.get(0).getImage2());
                        images.add(singleUnit.get(0).getImage3());
                        images.add(singleUnit.get(0).getImage4());
                        images.add(singleUnit.get(0).getImage5());
                        images.add(singleUnit.get(0).getImage6());
                    } else if (!singleUnit.get(0).getImage1().substring(singleUnit.get(0).getImage1().lastIndexOf("/")).equals("0") &&
                            !singleUnit.get(0).getImage2().substring(singleUnit.get(0).getImage2().lastIndexOf("/")).equals("0") &&
                            !singleUnit.get(0).getImage3().substring(singleUnit.get(0).getImage3().lastIndexOf("/")).equals("0") &&
                            !singleUnit.get(0).getImage4().substring(singleUnit.get(0).getImage4().lastIndexOf("/")).equals("0") &&

                            !singleUnit.get(0).getImage5().substring(singleUnit.get(0).getImage5().lastIndexOf("/")).equals("0") &&

                            singleUnit.get(0).getImage6().substring(singleUnit.get(0).getImage6().lastIndexOf("/")).equals("0") &&

                            singleUnit.get(0).getImage7().substring(singleUnit.get(0).getImage7().lastIndexOf("/")).equals("0") &&

                            singleUnit.get(0).getImage8().substring(singleUnit.get(0).getImage8().lastIndexOf("/")).equals("0") &&

                            singleUnit.get(0).getImage9().substring(singleUnit.get(0).getImage9().lastIndexOf("/")).equals("0") &&

                            singleUnit.get(0).getImage10().substring(singleUnit.get(0).getImage10().lastIndexOf("/")).equals("0")) {
                        images.clear();
                        images.add(singleUnit.get(0).getImage1());
                        images.add(singleUnit.get(0).getImage2());
                        images.add(singleUnit.get(0).getImage3());
                        images.add(singleUnit.get(0).getImage4());
                        images.add(singleUnit.get(0).getImage5());
                    } else if (!singleUnit.get(0).getImage1().substring(singleUnit.get(0).getImage1().lastIndexOf("/")).equals("0") &&
                            !singleUnit.get(0).getImage2().substring(singleUnit.get(0).getImage2().lastIndexOf("/")).equals("0") &&
                            !singleUnit.get(0).getImage3().substring(singleUnit.get(0).getImage3().lastIndexOf("/")).equals("0") &&
                            !singleUnit.get(0).getImage4().substring(singleUnit.get(0).getImage4().lastIndexOf("/")).equals("0") &&

                            singleUnit.get(0).getImage5().substring(singleUnit.get(0).getImage5().lastIndexOf("/")).equals("0") &&

                            singleUnit.get(0).getImage6().substring(singleUnit.get(0).getImage6().lastIndexOf("/")).equals("0") &&

                            singleUnit.get(0).getImage7().substring(singleUnit.get(0).getImage7().lastIndexOf("/")).equals("0") &&

                            singleUnit.get(0).getImage8().substring(singleUnit.get(0).getImage8().lastIndexOf("/")).equals("0") &&

                            singleUnit.get(0).getImage9().substring(singleUnit.get(0).getImage9().lastIndexOf("/")).equals("0") &&

                            singleUnit.get(0).getImage10().substring(singleUnit.get(0).getImage10().lastIndexOf("/")).equals("0")) {
                        images.clear();
                        images.add(singleUnit.get(0).getImage1());
                        images.add(singleUnit.get(0).getImage2());
                        images.add(singleUnit.get(0).getImage3());
                        images.add(singleUnit.get(0).getImage4());
                    } else if (!singleUnit.get(0).getImage1().substring(singleUnit.get(0).getImage1().lastIndexOf("/")).equals("0") &&
                            !singleUnit.get(0).getImage2().substring(singleUnit.get(0).getImage2().lastIndexOf("/")).equals("0") &&
                            !singleUnit.get(0).getImage3().substring(singleUnit.get(0).getImage3().lastIndexOf("/")).equals("0") &&
                            singleUnit.get(0).getImage4().substring(singleUnit.get(0).getImage4().lastIndexOf("/")).equals("0") &&

                            singleUnit.get(0).getImage5().substring(singleUnit.get(0).getImage5().lastIndexOf("/")).equals("0") &&

                            singleUnit.get(0).getImage6().substring(singleUnit.get(0).getImage6().lastIndexOf("/")).equals("0") &&

                            singleUnit.get(0).getImage7().substring(singleUnit.get(0).getImage7().lastIndexOf("/")).equals("0") &&

                            singleUnit.get(0).getImage8().substring(singleUnit.get(0).getImage8().lastIndexOf("/")).equals("0") &&

                            singleUnit.get(0).getImage9().substring(singleUnit.get(0).getImage9().lastIndexOf("/")).equals("0") &&

                            singleUnit.get(0).getImage10().substring(singleUnit.get(0).getImage10().lastIndexOf("/")).equals("0")) {
                        images.clear();
                        images.add(singleUnit.get(0).getImage1());
                        images.add(singleUnit.get(0).getImage2());
                        images.add(singleUnit.get(0).getImage3());
                    } else if (!singleUnit.get(0).getImage1().substring(singleUnit.get(0).getImage1().lastIndexOf("/")).equals("0") &&
                            !singleUnit.get(0).getImage2().substring(singleUnit.get(0).getImage2().lastIndexOf("/")).equals("0") &&
                            singleUnit.get(0).getImage3().substring(singleUnit.get(0).getImage3().lastIndexOf("/")).equals("0") &&
                            singleUnit.get(0).getImage4().substring(singleUnit.get(0).getImage4().lastIndexOf("/")).equals("0") &&

                            singleUnit.get(0).getImage5().substring(singleUnit.get(0).getImage5().lastIndexOf("/")).equals("0") &&

                            singleUnit.get(0).getImage6().substring(singleUnit.get(0).getImage6().lastIndexOf("/")).equals("0") &&

                            singleUnit.get(0).getImage7().substring(singleUnit.get(0).getImage7().lastIndexOf("/")).equals("0") &&

                            singleUnit.get(0).getImage8().substring(singleUnit.get(0).getImage8().lastIndexOf("/")).equals("0") &&

                            singleUnit.get(0).getImage9().substring(singleUnit.get(0).getImage9().lastIndexOf("/")).equals("0") &&

                            singleUnit.get(0).getImage10().substring(singleUnit.get(0).getImage10().lastIndexOf("/")).equals("0")) {
                        images.clear();
                        images.add(singleUnit.get(0).getImage1());
                        images.add(singleUnit.get(0).getImage2());
                    } else if (!singleUnit.get(0).getImage1().substring(singleUnit.get(0).getImage1().lastIndexOf("/")).equals("0") &&
                            singleUnit.get(0).getImage2().substring(singleUnit.get(0).getImage2().lastIndexOf("/")).equals("0") &&
                            singleUnit.get(0).getImage3().substring(singleUnit.get(0).getImage3().lastIndexOf("/")).equals("0") &&
                            singleUnit.get(0).getImage4().substring(singleUnit.get(0).getImage4().lastIndexOf("/")).equals("0") &&

                            singleUnit.get(0).getImage5().substring(singleUnit.get(0).getImage5().lastIndexOf("/")).equals("0") &&

                            singleUnit.get(0).getImage6().substring(singleUnit.get(0).getImage6().lastIndexOf("/")).equals("0") &&

                            singleUnit.get(0).getImage7().substring(singleUnit.get(0).getImage7().lastIndexOf("/")).equals("0") &&

                            singleUnit.get(0).getImage8().substring(singleUnit.get(0).getImage8().lastIndexOf("/")).equals("0") &&

                            singleUnit.get(0).getImage9().substring(singleUnit.get(0).getImage9().lastIndexOf("/")).equals("0") &&

                            singleUnit.get(0).getImage10().substring(singleUnit.get(0).getImage10().lastIndexOf("/")).equals("0")) {
                        images.clear();
                        images.add(singleUnit.get(0).getImage1());
                    }



                    binding.showRecyclerView.setLayoutManager(new GridLayoutManager(ShowExpansesActivity.this, 2));
                    binding.showRecyclerView.setAdapter(new ShowExpansesAdapter(ShowExpansesActivity.this, images));
                } else {
                    Toast.makeText(ShowExpansesActivity.this, "OnResponseElse", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Hao> call, Throwable t) {
                Toast.makeText(ShowExpansesActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnBack.setOnClickListener(v -> {
            finish();
        });
    }
}