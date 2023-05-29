package com.example.staffin.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.staffin.R;
import com.example.staffin.Response.AllExpenses;
import com.example.staffin.Response.Hao;

import java.util.ArrayList;
import java.util.List;

public class ShowExpansesAdapter extends RecyclerView.Adapter<ShowExpansesAdapter.MyViewHolder> {
    Context context;
    //    AllExpenses.GetAllExpenseDetail listOfExpanses;
//
//    List<Hao.GetAllExpenseDetail> res;
    List<String> images;

    public ShowExpansesAdapter(Context context, List<String> images) {
        this.context = context;
        this.images = images;
    }

    @NonNull
    @Override
    public ShowExpansesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.show_expanses_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowExpansesAdapter.MyViewHolder holder, int position) {
        String img = images.get(position);
        if (!img.endsWith("0")) {
            Glide.with(context).load(img).into(holder.showImage);
        }

    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (!images.get(0).endsWith("0")) {
            ++count;
        }
        if (!images.get(1).endsWith("0")) {
            ++count;
        }
        if (!images.get(2).endsWith("0")) {
            ++count;
        }
        if (!images.get(3).endsWith("0")) {
            ++count;
        }
        if (!images.get(4).endsWith("0")) {
            ++count;
        }
        if (!images.get(5).endsWith("0")) {
            ++count;
        }
        if (!images.get(6).endsWith("0")) {
            ++count;
        }
        if (!images.get(7).endsWith("0")) {
            ++count;
        }
        if (!images.get(8).endsWith("0")) {
            ++count;
        }
        if (!images.get(9).endsWith("0")) {
            ++count;
        }

        return (count);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView showImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            showImage = itemView.findViewById(R.id.showImage);
        }
    }
}
