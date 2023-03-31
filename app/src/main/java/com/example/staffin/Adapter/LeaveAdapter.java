package com.example.staffin.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.staffin.R;

public class LeaveAdapter extends RecyclerView.Adapter<LeaveAdapter.MyViewHolder> {
    Context context;
    Dialog adDialog;

    public LeaveAdapter(Context context) {
        this.context = context;
        adDialog = new Dialog(this.context);


    }

    @NonNull
    @Override
    public LeaveAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_leave_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaveAdapter.MyViewHolder holder, int position) {
        holder.leaveCard.setOnClickListener(v -> {
            showPopup();
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public void showPopup() {
        adDialog.setContentView(R.layout.leave_application_popup);
        adDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        adDialog.setCancelable(false);
        adDialog.show();

        AppCompatButton yesBtn = adDialog.findViewById(R.id.yesBtn);
        AppCompatButton noBtn = adDialog.findViewById(R.id.noBtn);

        yesBtn.setOnClickListener(v -> {
            Toast.makeText(context, "Leave Application Accepted", Toast.LENGTH_SHORT).show();
//            Toast toast = Toast.makeText(context.getApplicationContext(), "Employee Removed Successfully", Toast.LENGTH_SHORT);
//            View view1 = toast.getView();
//            view1.setBackgroundResource(R.drawable.bg_red);
//            view1.setPadding(70, 30, 70, 30);
//            toast.show();
            adDialog.dismiss();

        });
        noBtn.setOnClickListener(v -> {
            adDialog.dismiss();
            Toast.makeText(context, "Leave Application Rejected", Toast.LENGTH_SHORT).show();

        });
//        adDialog.setOnCancelListener(dialog -> adDialog.dismiss());
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout leaveCard;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            leaveCard = itemView.findViewById(R.id.leaveCard);
        }
    }
}
