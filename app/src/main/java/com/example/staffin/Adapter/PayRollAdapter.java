package com.example.staffin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.staffin.PaySlipActivity;
import com.example.staffin.PayrollActivity;
import com.example.staffin.R;
import com.example.staffin.Response.AllPayroll;
import com.example.staffin.SalaryInfoActivity;

import java.util.List;

public class PayRollAdapter extends RecyclerView.Adapter<PayRollAdapter.MyViewHolder> {

    Context context;
    List<AllPayroll.AllPayslipDetail> resp;

    public PayRollAdapter(Context context, List<AllPayroll.AllPayslipDetail> resp) {
        this.resp = resp;
        this.context = context;
    }

    @NonNull
    @Override
    public PayRollAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_payroll_layout, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PayRollAdapter.MyViewHolder holder, int position) {
        AllPayroll.AllPayslipDetail singleUnit = resp.get(position);
        Animation animationLeft = AnimationUtils.loadAnimation(holder.firstConstraint.getContext(), android.R.anim.slide_in_left);
        holder.firstConstraint.startAnimation(animationLeft);
        holder.imgUser.startAnimation(animationLeft);


        holder.viewIcon.setOnClickListener(v -> {
            context.startActivity(new Intent(context.getApplicationContext(), PaySlipActivity.class));
        });

        holder.editIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context.getApplicationContext(), SalaryInfoActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return resp.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageButton editIcon, viewIcon;
        ConstraintLayout firstConstraint;
        ImageView imgUser;
        TextView textView6, reasonTv, txtEndDate, txtSalary, txtMoney;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            firstConstraint = itemView.findViewById(R.id.firstConstraint);
            editIcon = itemView.findViewById(R.id.editIcon);
            viewIcon = itemView.findViewById(R.id.viewIcon);
            imgUser = itemView.findViewById(R.id.imgUser);
        }
    }
}
