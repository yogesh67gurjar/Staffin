package com.example.staffin.Adapter;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.staffin.AddEmployeeActivity;
import com.example.staffin.Interface.ApiInterface;
import com.example.staffin.R;
import com.example.staffin.Response.EmployeeResult;
import com.example.staffin.Response.LoginResponse;
import com.example.staffin.Retrofit.RetrofitServices;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TotalEmployeeAdapter extends RecyclerView.Adapter<TotalEmployeeAdapter.MyViewHolder> {
    Context context;
    List<EmployeeResult> employeeResultList;
    Dialog adDialog;
    ProgressDialog progress;
    ApiInterface apiInterface;


    public TotalEmployeeAdapter(Context context, List<EmployeeResult> employeeResultList) {
        this.context = context;
        this.employeeResultList = employeeResultList;
        adDialog = new Dialog(this.context);
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        progress = new ProgressDialog(this.context);
        progress.setMessage("please wait....");
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
        Animation animationLeft = AnimationUtils.loadAnimation(holder.mainConstraint.getContext(), android.R.anim.slide_in_left);
        holder.mainConstraint.startAnimation(animationLeft);
        holder.userImage.startAnimation(animationLeft);
        holder.txtName.startAnimation(animationLeft);
        holder.txtEmail.startAnimation(animationLeft);
        holder.txtDepartment.startAnimation(animationLeft);
        holder.txtDOB.startAnimation(animationLeft);
        holder.txtAtWork.startAnimation(animationLeft);
        holder.btnEdit.startAnimation(animationLeft);
        holder.btnDelete.startAnimation(animationLeft);
        holder.txtEmpId.startAnimation(animationLeft);
        holder.txtDesignation.startAnimation(animationLeft);

        EmployeeResult singleUnit = employeeResultList.get(position);

//        Glide.with(context.getApplicationContext()).load(singleUnit.getProfileImage()).placeholder(R.drawable.img_dp).into(holder.userImage);
        Log.e("image aa rahi hai", singleUnit.getProfileImage());
        Glide.with(context.getApplicationContext()).load(singleUnit.getProfileImage()).placeholder(R.drawable.img_dp).into(holder.userImage);

        holder.txtName.setText(singleUnit.getFullName());
        holder.txtEmail.setText(singleUnit.getEmail());

        String[] DOB = singleUnit.getDateOfBirth().split("T");
        holder.txtDOB.setText("Dob -" + DOB[0]);//singleUnit.getDateOfBirth());


        holder.txtEmpId.setText("Emp.ID -" + singleUnit.getEmployeeID());
        if (singleUnit.getDepartmentId().size() == 0) {
            holder.txtDepartment.setText("not specified");
            holder.txtDesignation.setText("not specified");
        } else {
            holder.txtDepartment.setText("Department -" + singleUnit.getDepartmentId().get(0).getName());
            holder.txtDesignation.setText("Designation -" + singleUnit.getDesignation().get(0).getDesignation());
        }

        holder.txtAtWork.setText("At work -" + singleUnit.getWorkDuration());


        holder.btnEdit.setOnClickListener(v -> {
            Intent editIntent = new Intent(context, AddEmployeeActivity.class);
            editIntent.putExtra("Id", singleUnit.getId());
            editIntent.putExtra("from", "edit");
            editIntent.putExtra("empId", singleUnit.getEmployeeID());
            context.startActivity(editIntent);
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(singleUnit.getId(), holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return employeeResultList.size();
    }

    public void showPopup(int Id, int position) {
        adDialog.setContentView(R.layout.remove_employee_popup);
        adDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        adDialog.show();

        AppCompatButton yesBtn = adDialog.findViewById(R.id.yesBtn);
        AppCompatButton noBtn = adDialog.findViewById(R.id.noBtn);

        yesBtn.setOnClickListener(v -> {
            progress.show();
            Call<LoginResponse> callDeleteEmployeeById = apiInterface.deleteEmployeeById(Id);
            callDeleteEmployeeById.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(context, "Employee Removed Successfully", Toast.LENGTH_SHORT).show();
                        adDialog.dismiss();
                        progress.dismiss();
                        employeeResultList.remove(position);
                        notifyDataSetChanged();
                    } else {
                        Log.d("kdfnsd", response.message());
                        Toast.makeText(context, "some error occured", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(context, "some failure occured", Toast.LENGTH_SHORT).show();
                    Log.d("dkfsdn", t.getMessage());
                }
            });


        });
        noBtn.setOnClickListener(v -> adDialog.dismiss());
        adDialog.setOnCancelListener(dialog -> adDialog.dismiss());
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageButton btnEdit, btnDelete;
        TextView txtName, txtEmail, txtDOB, txtEmpId, txtDepartment, txtDesignation, txtAtWork;
        ImageView userImage;
        ConstraintLayout mainConstraint;

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
            userImage = itemView.findViewById(R.id.userImage_total);
            mainConstraint = itemView.findViewById(R.id.mainConstraint);
        }
    }
}