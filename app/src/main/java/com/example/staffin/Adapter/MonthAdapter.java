package com.example.staffin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.staffin.EventActivity;
import com.example.staffin.R;
import com.example.staffin.Response.AllEvents;
import com.example.staffin.Response.Attendance;
import com.example.staffin.Response.MyMonth;

import java.util.ArrayList;
import java.util.List;

public class MonthAdapter extends RecyclerView.Adapter<MonthAdapter.MyViewHolder> {
    Context context;
    //    List<MyMonth> my;
    List<AllEvents> allEvents;
    List<String> allMonthJoRkhneHe;

    public MonthAdapter(Context context, List<AllEvents> allEvents) {
        this.context = context;
        this.allEvents = allEvents;
        allMonthJoRkhneHe = new ArrayList<>();

        List<String> months = new ArrayList<>();
        for (AllEvents a : allEvents) {
            months.add(a.getDate().split("-")[1]);
        }

        for (String s : months) {
            switch (s) {
                case "01":
                    allMonthJoRkhneHe.add("January");
                    break;
                case "02":
                    allMonthJoRkhneHe.add("February");
                    break;
                case "03":
                    allMonthJoRkhneHe.add("March");
                    break;
                case "04":
                    allMonthJoRkhneHe.add("April");
                    break;
                case "05":
                    allMonthJoRkhneHe.add("May");
                    break;
                case "06":
                    allMonthJoRkhneHe.add("June");
                    break;
                case "07":
                    allMonthJoRkhneHe.add("July");
                    break;
                case "08":
                    allMonthJoRkhneHe.add("August");
                    break;
                case "09":
                    allMonthJoRkhneHe.add("September");
                    break;
                case "10":
                    allMonthJoRkhneHe.add("October");
                    break;
                case "11":
                    allMonthJoRkhneHe.add("November");
                    break;
                case "12":
                    allMonthJoRkhneHe.add("december");
                    break;
            }
        }
    }

    //    List<String> months;
//
//    public MonthAdapter(List<MyMonth> my, Context context) {
//        this.context = context;
//        this.my = my;
//        months = new ArrayList<>();
//        months.add("january");
//        months.add("february");
//        months.add("march");
//        months.add("april");
//        months.add("may");
//        months.add("june");
//        months.add("july");
//        months.add("august");
//        months.add("september");
//        months.add("october");
//        months.add("november");
//        months.add("december");
//    }

    public void filterList(List<AllEvents> filterlist) {
        allEvents = filterlist;
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
        holder.recyclerView.setAdapter(new EventAdapter(context, allEvents));
        String month = null;
        AllEvents singleUnit = allEvents.get(position);
        String[] dateInParts = singleUnit.getDate().split("-");
        switch (dateInParts[1]) {
            case "01":
                month = "January";
                break;
            case "02":
                month = "February";
                break;
            case "03":
                month = "March";
                break;
            case "04":
                month = "April";
                break;
            case "05":
                month = "May";
                break;
            case "06":
                month = "June";
                break;
            case "07":
                month = "July";
                break;
            case "08":
                month = "August";
                break;
            case "09":
                month = "September";
                break;
            case "10":
                month = "October";
                break;
            case "11":
                month = "November";
                break;
            case "12":
                month = "december";
                break;
        }

//        holder.monthTv.setText(String.valueOf( allEvents.get(position)));
        holder.monthTv.setText(allMonthJoRkhneHe.get(position));
    }

    @Override
    public int getItemCount() {
        return allMonthJoRkhneHe.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recyclerView;
        TextView monthTv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            monthTv = itemView.findViewById(R.id.monthTv);
            recyclerView = itemView.findViewById(R.id.EventRv);

        }
    }
}
