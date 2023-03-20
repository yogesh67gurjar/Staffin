package com.example.staffin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.staffin.InsideAttendanceActivity;
import com.example.staffin.R;
import com.example.staffin.Response.Attendance;

import java.util.List;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.MyViewHolder> {
    Context context;
    List<Attendance> attendanceList;
    String issueSelected;
    String[] shift = {"Attendance", "Present", "Absent"};

    public AttendanceAdapter(Context context,List<Attendance> attendanceList) {

        this.context = context;
        this.attendanceList=attendanceList;
    }

    public void filterList(List<Attendance> filterlist) {
        attendanceList = filterlist;
        notifyDataSetChanged();
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
        Attendance singleUnit = attendanceList.get(position);
        holder.txtName.setText(singleUnit.getName());
        holder.txtMail.setText(singleUnit.getEmail());
        holder.empIdTv.setText("Emp. ID - "+ singleUnit.getEmpId());
        holder.btnWhatsApp.setOnClickListener(v -> {
            String phone = singleUnit.getPhone();
            if (!iswhatsAppInstall()) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=" + phone));
                context.startActivity(i);
            } else {
                Toast.makeText(context, "Please Install WhatsApp...", Toast.LENGTH_SHORT).show();
            }

        });
        holder.btnCall.setOnClickListener(v -> {
//            Toast.makeText(context, "Open Call", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Intent.ACTION_DIAL);
            String phoneCall = singleUnit.getPhone();
            intent.setData(Uri.parse("tel:" + phoneCall));
            context.startActivity(intent);
        });
        holder.MainCard.setOnClickListener(v -> {
            Intent intent = new Intent(context, InsideAttendanceActivity.class);
            context.startActivity(intent);
        });


        ArrayAdapter aa = new ArrayAdapter(context.getApplicationContext(), android.R.layout.simple_spinner_item, shift);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.spinner.setAdapter(aa);


        holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                holder.spinnerConstraint.setBackgroundResource(R.drawable.bg_back_btn);
                holder.spinner.setBackgroundResource(R.color.white);
                issueSelected = shift[position];
                if (issueSelected.equalsIgnoreCase("Present")) {
//                    holder.spinnerConstraint.setBackgroundColor(context.getResources().getColor(R.color.txtGreen));
                    holder.spinnerConstraint.setBackgroundResource(R.drawable.bg_green);
                    holder.spinner.setBackgroundResource(R.color.txtGreen);
                    ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
//                    ((TextView) parent.getChildAt(0)).setTextSize(5);
                } else if (issueSelected.equalsIgnoreCase("Absent")) {
//                    holder.spinnerConstraint.setBackgroundColor(context.getResources().getColor(R.color.txtRed));
                    holder.spinnerConstraint.setBackgroundResource(R.drawable.bg_red);
                    holder.spinner.setBackgroundResource(R.color.txtRed);
                    ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return attendanceList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout MainCard, spinnerConstraint;
        ImageButton btnWhatsApp, btnCall;
        TextView txtMail,txtName,empIdTv;
        Spinner spinner;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            empIdTv=itemView.findViewById(R.id.empIdTv);
            txtName=itemView.findViewById(R.id.txtName);
            MainCard = itemView.findViewById(R.id.MainCard);
            btnWhatsApp = itemView.findViewById(R.id.btnWhatsApp);
            btnCall = itemView.findViewById(R.id.btnCall);
            txtMail = itemView.findViewById(R.id.txtMail);
            spinner = itemView.findViewById(R.id.spinner);
            spinnerConstraint = itemView.findViewById(R.id.spinnerConstraint);
        }
    }

    private boolean iswhatsAppInstall() {

        PackageManager packageManager = context.getPackageManager();

        boolean whatsAppInstall;
        try {
            packageManager.getPackageInfo("com.whatsapp", packageManager.GET_ACTIVITIES);
            whatsAppInstall = true;
        } catch (PackageManager.NameNotFoundException e) {

            whatsAppInstall = false;

        }
        return whatsAppInstall;
    }

}
