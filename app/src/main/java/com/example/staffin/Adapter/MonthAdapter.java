package com.example.staffin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.staffin.EventActivity;
import com.example.staffin.R;
import com.example.staffin.Response.Attendance;
import com.example.staffin.Response.MyMonth;

import java.util.List;

public class MonthAdapter extends RecyclerView.Adapter<MonthAdapter.MyViewHolder> {
    Context context;
    List<MyMonth> my;

    public MonthAdapter(List<MyMonth>my,Context context) {
        this.context = context;
        this.my=my;
    }

    public void filterList(List<MyMonth> filterlist) {
        my = filterlist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MonthAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_month_event_layout, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MonthAdapter.MyViewHolder holder, int position) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        holder.recyclerView.setLayoutManager(layoutManager);
        holder.recyclerView.setAdapter(new EventAdapter(context));

    }

    @Override
    public int getItemCount() {
        return my.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recyclerView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.EventRv);

        }
    }
}
