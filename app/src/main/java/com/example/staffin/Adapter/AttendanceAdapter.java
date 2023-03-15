package com.example.staffin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.staffin.InsideAttendanceActivity;
import com.example.staffin.R;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.MyViewHolder> {
    Context context;

    public AttendanceAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public AttendanceAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_attendance_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttendanceAdapter.MyViewHolder holder, int position) {

        holder.MainCard.setOnClickListener(v -> {
            Intent intent = new Intent(context, InsideAttendanceActivity.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout MainCard;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            MainCard = itemView.findViewById(R.id.MainCard);
        }
    }
}
