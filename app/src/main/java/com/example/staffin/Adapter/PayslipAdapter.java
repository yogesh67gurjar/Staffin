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

import com.bumptech.glide.Glide;
import com.example.staffin.PaySlipActivity;
import com.example.staffin.R;
import com.example.staffin.Response.AllPayroll;
import com.example.staffin.Response.PayslipDetail;
import com.example.staffin.Response.SearchPayslip;
import com.example.staffin.SalaryInfoActivity;

import java.util.List;

public class PayslipAdapter extends RecyclerView.Adapter<PayslipAdapter.MyViewHolder> {


    List<SearchPayslip.PayslipDetail> payslipDetails;
    Context context;


    public PayslipAdapter(List<SearchPayslip.PayslipDetail> payslipDetails, Context context) {
        this.payslipDetails = payslipDetails;
        this.context = context;
    }

    @NonNull
    @Override
    public PayslipAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_payroll_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PayslipAdapter.MyViewHolder holder, int position) {
        SearchPayslip.PayslipDetail singleUnit = payslipDetails.get(position);
        Animation animationLeft = AnimationUtils.loadAnimation(holder.firstConstraint.getContext(), android.R.anim.slide_in_left);
        holder.firstConstraint.startAnimation(animationLeft);
        holder.imgUser.startAnimation(animationLeft);

        Glide.with(context.getApplicationContext()).load(singleUnit.getEmployeeId().get(0).getProfileImageUrl()).placeholder(R.drawable.img_dp).into(holder.imgUser);
        holder.txtMoney.setText("RM " + singleUnit.getNetSalary());
        holder.txtUserName.setText(singleUnit.getEmployeeId().get(0).getFullName());
        holder.reasonTv.setText("Month:-" + singleUnit.getMonth() + " Year:-" + singleUnit.getYear());
        holder.viewIcon.setOnClickListener(v -> {
            Intent startIntent = new Intent(context.getApplicationContext(), PaySlipActivity.class);
            startIntent.putExtra("Id", singleUnit.getEmployeeId().get(0).getId());
            startIntent.putExtra("IdI", singleUnit.getId());
            startIntent.putExtra("empId", singleUnit.getEmployeeId().get(0).getEmployeeID());
            context.startActivity(startIntent);
        });

        holder.editIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(context.getApplicationContext(), SalaryInfoActivity.class);
                startIntent.putExtra("Id", singleUnit.getEmployeeId().get(0).getId());
                startIntent.putExtra("IdI", singleUnit.getId());
                startIntent.putExtra("empId", singleUnit.getEmployeeId().get(0).getEmployeeID());
                context.startActivity(startIntent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return payslipDetails.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageButton editIcon, viewIcon;
        ConstraintLayout firstConstraint;
        ImageView imgUser;
        TextView txtUserName, reasonTv, txtEndDate, txtSalary, txtMoney;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            firstConstraint = itemView.findViewById(R.id.firstConstraint);
            editIcon = itemView.findViewById(R.id.editIcon);
            viewIcon = itemView.findViewById(R.id.viewIcon);
            imgUser = itemView.findViewById(R.id.imgUser);

            txtMoney = itemView.findViewById(R.id.txtMoney);
            txtUserName = itemView.findViewById(R.id.textView6);
            reasonTv = itemView.findViewById(R.id.reasonTv);
        }
    }
}
