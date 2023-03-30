package com.example.staffin.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.staffin.AddEmployeeActivity;
import com.example.staffin.R;
import com.example.staffin.Response.EmployeeResult;

import java.util.List;

public class TotalEmployeeAdapter extends RecyclerView.Adapter<TotalEmployeeAdapter.MyViewHolder> {
    Context context;
    List<EmployeeResult> employeeResultList;
    Dialog adDialog;

    public TotalEmployeeAdapter(Context context, List<EmployeeResult> employeeResultList) {
        this.context = context;
        this.employeeResultList = employeeResultList;
        adDialog = new Dialog(this.context);
    }

    public void filterList(List<EmployeeResult> filterlist) {
        employeeResultList = filterlist;
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
        EmployeeResult singleUnit = employeeResultList.get(position);
//        Glide.with(context.getApplicationContext()).load(singleUnit.getProfileImage()).placeholder(R.drawable.image_employee).into(holder.userImage);

        holder.txtName.setText(singleUnit.getFullName());
        holder.txtEmail.setText(singleUnit.getEmail());
        holder.txtDOB.setText("Date of birth -" + "Null");//singleUnit.getDateOfBirth());
        holder.txtEmpId.setText("Emp.ID -" + singleUnit.getEmployeeID());
        holder.txtDepartment.setText("Department - Null");
        holder.txtDesignation.setText("Designation - Null");
        holder.txtAtWork.setText("At work -" + singleUnit.getWorkDuration());


        holder.btnEdit.setOnClickListener(v -> {
            Intent editIntent = new Intent(context, AddEmployeeActivity.class);
            editIntent.putExtra("Id", singleUnit.getId());
            editIntent.putExtra("from", "edit");
            editIntent.putExtra("empId",singleUnit.getEmployeeID());
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
        return employeeResultList.size();
    }

    public void showPopup() {
        adDialog.setContentView(R.layout.remove_employee_popup);
        adDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        adDialog.show();

        AppCompatButton yesBtn = adDialog.findViewById(R.id.yesBtn);
        AppCompatButton noBtn = adDialog.findViewById(R.id.noBtn);

        yesBtn.setOnClickListener(v -> {
            Toast.makeText(context, "Employee Removed Successfully", Toast.LENGTH_SHORT).show();
//            Toast toast = Toast.makeText(context.getApplicationContext(), "Employee Removed Successfully", Toast.LENGTH_SHORT);
//            View view1 = toast.getView();
//            view1.setBackgroundResource(R.drawable.bg_red);
//            view1.setPadding(70, 30, 70, 30);
//            toast.show();
            adDialog.dismiss();

        });
        noBtn.setOnClickListener(v -> adDialog.dismiss());
        adDialog.setOnCancelListener(dialog -> adDialog.dismiss());
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageButton btnEdit, btnDelete;
        TextView txtName, txtEmail, txtDOB, txtEmpId, txtDepartment, txtDesignation, txtAtWork;
        ImageView userImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.nameTv);
            btnEdit = itemView.findViewById(R.id.editIcon);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            txtEmail = itemView.findViewById(R.id.txtEmail);
            txtDOB = itemView.findViewById(R.id.txtDOB);
            txtEmpId = itemView.findViewById(R.id.txtEmpId);
            txtDepartment = itemView.findViewById(R.id.txtDepartment);
            txtDesignation = itemView.findViewById(R.id.txtDesignation);
            txtAtWork = itemView.findViewById(R.id.txtAtWork);
            userImage = itemView.findViewById(R.id.userImage);
        }
    }
}