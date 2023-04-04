package com.example.staffin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.staffin.R;
import com.example.staffin.Response.AllHolidays;

import java.util.List;

public class HolidayAdapter extends RecyclerView.Adapter<HolidayAdapter.HolidayViewHolder> {
    Context context;
    List<AllHolidays> holidays;

    public HolidayAdapter(Context context, List<AllHolidays> holidays) {
        this.holidays = holidays;
        this.context = context;
    }

    @NonNull
    @Override
    public HolidayAdapter.HolidayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.holiday_layout, parent, false);
        return new HolidayAdapter.HolidayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolidayAdapter.HolidayViewHolder holder, int position) {

        AllHolidays singleUnit = holidays.get(position);
        holder.titleTv.setText(singleUnit.getOccassion());
        String[] tempDate = singleUnit.getDate().split("-");
        holder.dateTv.setText(tempDate[2]);
        holder.dayTv.setText(singleUnit.getHoliday_day());
        holder.descriptionTv.setText(singleUnit.getHoliday_description());
        if (position % 3 == 0) {
            holder.ll2.setBackgroundResource(R.color.mainColor);
        } else if (position % 5 == 0) {
            holder.ll2.setBackgroundResource(R.color.yellow);
        } else if (position % 2 == 0) {
            holder.ll2.setBackgroundResource(R.color.pink);
        } else {
            holder.ll2.setBackgroundResource(R.color.green);
        }
    }

    @Override
    public int getItemCount() {
        return holidays.size();
    }

    public class HolidayViewHolder extends RecyclerView.ViewHolder {
        CardView card2;
        LinearLayout ll2;
        TextView dateTv, dayTv, titleTv, descriptionTv;

        public HolidayViewHolder(@NonNull View itemView) {
            super(itemView);
            card2 = itemView.findViewById(R.id.card2);
            ll2 = itemView.findViewById(R.id.ll2);
            dateTv = itemView.findViewById(R.id.dateTv);
            dayTv = itemView.findViewById(R.id.dayTv);
            titleTv = itemView.findViewById(R.id.titleTv);
            descriptionTv = itemView.findViewById(R.id.descriptionTv);
        }
    }
}
