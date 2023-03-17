package com.example.staffin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.staffin.InsideAttendanceActivity;
import com.example.staffin.MainActivity;
import com.example.staffin.R;

import de.hdodenhof.circleimageview.BuildConfig;

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

        holder.btnWhatsApp.setOnClickListener(v -> {
            String phone= "+91 7000563594";
            if (!iswhatsAppInstall()){
                Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse("https://api.whatsapp.com/send?phone="+ phone));
                context.startActivity(i);
            }else {
                Toast.makeText(context, "Please Install WhatsApp...", Toast.LENGTH_SHORT).show();
            }

        });
        holder.btnCall.setOnClickListener(v -> {
//            Toast.makeText(context, "Open Call", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:9143143143"));
            context.startActivity(intent);
        });

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
        ImageButton btnWhatsApp, btnCall;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            MainCard = itemView.findViewById(R.id.MainCard);
            btnWhatsApp = itemView.findViewById(R.id.btnWhatsApp);
            btnCall = itemView.findViewById(R.id.btnCall);
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
