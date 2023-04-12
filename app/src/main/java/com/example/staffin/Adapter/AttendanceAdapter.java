package com.example.staffin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.staffin.InsideAttendanceActivity;
import com.example.staffin.R;
import com.example.staffin.Response.Attendance;
import com.example.staffin.Response.TodayAttendance;

import java.util.List;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.MyViewHolder> {
    Context context;
    List<TodayAttendance> attendanceList;
    String issueSelected;
    String[] shift = {"Attendance", "Present", "Absent"};
    Intent intent;


    public AttendanceAdapter(Context context, List<TodayAttendance> attendanceList) {

        this.context = context;
        this.attendanceList = attendanceList;
        intent = new Intent(context, InsideAttendanceActivity.class);
    }

    public void filterList(List<TodayAttendance> filterlist) {
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
        TodayAttendance singleUnit = attendanceList.get(position);
        holder.txtName.setText(singleUnit.getFullName());
        holder.txtMail.setText(singleUnit.getEmail());
        holder.empIdTv.setText("Emp. ID - " + singleUnit.getEmployeeID());
        holder.dobTv.setText("Date Of Birth - " + singleUnit.getDateOfBirth().split("T")[0]);

        Log.d("STATUS", singleUnit.getStatus());

        if (singleUnit.getAttendanceData().size() < 1) {
            holder.spinnerConstraint.setBackgroundResource(R.drawable.bg_red);
            holder.statusTv.setText("Absent");
            holder.statusTv.setTextColor(Color.WHITE);
            holder.statusTv.setBackgroundResource(R.drawable.bg_red);

        } else {
            holder.spinnerConstraint.setBackgroundResource(R.drawable.bg_green);
            holder.statusTv.setText("Present");
            holder.statusTv.setTextColor(Color.WHITE);
            holder.statusTv.setBackgroundResource(R.drawable.bg_green);

            holder.txtPunchIn.setText(singleUnit.getAttendanceData().get(0).getClockIn().split("T")[1].split("\\.")[0]);
            if (!(singleUnit.getAttendanceData().get(0).getClockOut() == null)) {
                holder.txtPunchOut.setText(singleUnit.getAttendanceData().get(0).getClockOut().split("T")[1].split("\\.")[0]);
            }

        }


        holder.btnWhatsApp.setOnClickListener(v -> {
            String phone = singleUnit.getMobileNumber();
            if (!iswhatsAppInstall()) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=" + phone));
                context.startActivity(i);
            } else {
                Toast.makeText(context, "Please Install WhatsApp...", Toast.LENGTH_SHORT).show();
            }

        });
        holder.btnCall.setOnClickListener(v -> {
//            Toast.makeText(context, "Open Call", Toast.LENGTH_SHORT).show();
            Intent intentCall = new Intent(Intent.ACTION_DIAL);
            String phoneCall = singleUnit.getMobileNumber();
            intentCall.setData(Uri.parse("tel:" + phoneCall));
            context.startActivity(intentCall);
        });
        holder.MainCard.setOnClickListener(v -> {
            intent.putExtra("name", singleUnit.getFullName());
            intent.putExtra("empId", singleUnit.getEmployeeID());
            intent.putExtra("dpImg", singleUnit.getProfileImage());
            if(singleUnit.getAttendanceData().size() < 1)
            {
                intent.putExtra("status", "Absent");
            }
            else
            {
                intent.putExtra("status", "Present");
            }
            context.startActivity(intent);
        });


//        holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                issueSelected = shift[position];
//                if (issueSelected.equalsIgnoreCase("Present")) {
////                    holder.spinnerConstraint.setBackgroundColor(context.getResources().getColor(R.color.txtGreen));
//                    holder.spinnerConstraint.setBackgroundResource(R.drawable.bg_green);
//                    holder.spinner.setBackgroundColor(context.getResources().getColor(R.color.txtGreen));
//                    ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
////                    ((TextView) parent.getChildAt(0)).setTextSize(5);
//                } else if (issueSelected.equalsIgnoreCase("Absent")) {
////                    holder.spinnerConstraint.setBackgroundColor(context.getResources().getColor(R.color.txtRed));
//                    holder.spinnerConstraint.setBackgroundResource(R.drawable.bg_red);
//                    holder.spinner.setBackgroundColor(context.getResources().getColor(R.color.txtRed));
//                    ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
//                } else {
//                    ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
//                    holder.spinnerConstraint.setBackgroundResource(R.drawable.bg_back_btn);
//                    holder.spinner.setBackgroundResource(R.color.white);
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return attendanceList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout MainCard, spinnerConstraint;
        ImageButton btnWhatsApp, btnCall;
        TextView txtMail, txtName, empIdTv, dobTv;
        TextView statusTv, txtPunchIn, txtPunchOut;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            empIdTv = itemView.findViewById(R.id.empIdTv);
            txtName = itemView.findViewById(R.id.nameTv);
            MainCard = itemView.findViewById(R.id.MainCard);
            btnWhatsApp = itemView.findViewById(R.id.btnWhatsApp);
            btnCall = itemView.findViewById(R.id.btnCall);
            txtMail = itemView.findViewById(R.id.txtEmail);
            statusTv = itemView.findViewById(R.id.statusTv);
            spinnerConstraint = itemView.findViewById(R.id.spinnerConstraint);
            dobTv = itemView.findViewById(R.id.dobTv);
            txtPunchIn = itemView.findViewById(R.id.txtPunchIn);
            txtPunchOut = itemView.findViewById(R.id.tctPunchOut);
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
