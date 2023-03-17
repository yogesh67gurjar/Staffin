package com.example.staffin.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.staffin.AddEmployeeActivity;
import com.example.staffin.R;

import java.util.List;

public class TotalEmployeeAdapter extends RecyclerView.Adapter<TotalEmployeeAdapter.MyViewHolder> {
    Context context;
    List<String> employeesList;
    Dialog adDialog;

    public TotalEmployeeAdapter(List<String> employeesList, Context context) {
        this.context = context;
        this.employeesList = employeesList;
        adDialog = new Dialog(this.context);
    }

    public void filterList(List<String> filterlist) {
        employeesList = filterlist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TotalEmployeeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_total_employee_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TotalEmployeeAdapter.MyViewHolder holder, int position) {
        String singleUnit = employeesList.get(position);

        holder.txtName.setText(singleUnit);

        holder.btnEdit.setOnClickListener(v -> {
            Intent editIntent = new Intent(context, AddEmployeeActivity.class);
            editIntent.putExtra("from", "edit");
            context.startActivity(editIntent);
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup();
            }
        });

    }

    @Override
    public int getItemCount() {
        return employeesList.size();
    }

    public void showPopup() {
        adDialog.setContentView(R.layout.remove_employee_popup);
        adDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        adDialog.show();

        AppCompatButton yesBtn = adDialog.findViewById(R.id.yesBtn);
        AppCompatButton noBtn = adDialog.findViewById(R.id.noBtn);

        yesBtn.setOnClickListener(v -> {
            Toast.makeText(context, "Employee Removed Successfully", Toast.LENGTH_SHORT).show();
            adDialog.dismiss();
        });
        noBtn.setOnClickListener(v -> adDialog.dismiss());
        adDialog.setOnCancelListener(dialog -> adDialog.dismiss());
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageButton btnEdit, btnDelete;
        TextView txtName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}