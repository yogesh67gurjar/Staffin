package com.example.staffin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.staffin.R;
import com.example.staffin.Response.AllExpenses;
import com.example.staffin.Response.AllPayroll;

import java.util.List;

public class ExpansesAdapter extends RecyclerView.Adapter<ExpansesAdapter.MyViewHolder> {

    Context context;
    List<AllExpenses.GetAllExpenseDetail> resp;

    public ExpansesAdapter(Context context, List<AllExpenses.GetAllExpenseDetail> resp) {
        this.context = context;
        this.resp = resp;
    }

    @NonNull
    @Override
    public ExpansesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.rv_expanses_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpansesAdapter.MyViewHolder holder, int position) {
        AllExpenses.GetAllExpenseDetail singleUnit = resp.get(position);
        Animation animationLeft = AnimationUtils.loadAnimation(holder.mainConstraint.getContext(), android.R.anim.slide_in_left);
        holder.mainConstraint.startAnimation(animationLeft);
        holder.txtEmpId.startAnimation(animationLeft);
        holder.txtItemName.startAnimation(animationLeft);
        holder.txtPrice.startAnimation(animationLeft);
        holder.btnDownload.startAnimation(animationLeft);

        holder.txtEmpId.setText("EMP ID:-" + singleUnit.getEmployeeId().get(0).getEmployeeID());
        holder.txtItemName.setText("Name:-" + singleUnit.getItemName());
        holder.txtPrice.setText("Price:-" + singleUnit.getPrice());


    }

    @Override
    public int getItemCount() {
        return resp.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout mainConstraint;
        TextView txtEmpId, txtItemName, txtPrice;
        ImageButton btnDownload;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mainConstraint = itemView.findViewById(R.id.mainConstraint);
            txtEmpId = itemView.findViewById(R.id.txtEmpId);
            txtItemName = itemView.findViewById(R.id.txtItemName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            btnDownload = itemView.findViewById(R.id.btnDownload);
        }
    }
}
