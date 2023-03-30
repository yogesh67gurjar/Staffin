package com.example.staffin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.staffin.PaySlipActivity;
import com.example.staffin.R;
import com.example.staffin.SalaryInfoActivity;

public class PayRollAdapter extends RecyclerView.Adapter<PayRollAdapter.MyViewHolder> {

    Context context;

    public PayRollAdapter(Context context) {
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
        return 10;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageButton editIcon, viewIcon;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            editIcon = itemView.findViewById(R.id.editIcon);
            viewIcon = itemView.findViewById(R.id.viewIcon);
        }
    }
}
