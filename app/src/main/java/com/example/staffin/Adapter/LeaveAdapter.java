package com.example.staffin.Adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.staffin.Interface.ApiInterface;
import com.example.staffin.R;
import com.example.staffin.Response.EmployeeLeaveResult;
import com.example.staffin.Response.LeaveAcceptRejectResponse;
import com.example.staffin.Response.LeaveResponse;
import com.example.staffin.Retrofit.RetrofitServices;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaveAdapter extends RecyclerView.Adapter<LeaveAdapter.MyViewHolder> {
    Context context;
    List<LeaveResponse.EmployeeRes> leaveResultList;
    Dialog adDialog;
    ApiInterface apiInterface;

    public LeaveAdapter(Context context, List<LeaveResponse.EmployeeRes> leaveResultList) {
        this.context = context;
        this.leaveResultList = leaveResultList;
        adDialog = new Dialog(this.context);
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
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
        Animation animationLeft = AnimationUtils.loadAnimation(holder.leaveCard.getContext(), android.R.anim.slide_in_left);
        holder.leaveCard.startAnimation(animationLeft);
        holder.userDp.startAnimation(animationLeft);
        holder.txtStartEmpId.startAnimation(animationLeft);
        holder.txtPending.startAnimation(animationLeft);
        holder.txtEmpId.startAnimation(animationLeft);
        holder.txtStartReason.startAnimation(animationLeft);
        holder.txtReason.startAnimation(animationLeft);

        LeaveResponse.EmployeeRes singleUnit = leaveResultList.get(position);
        holder.txtEmpId.setText(singleUnit.getEmployeeId().get(0).getEmployeeID());
        Glide.with(context).load(singleUnit.getEmployeeId().get(0).getProfileImageUrl()).into(holder.userDp);
        holder.txtReason.setText(singleUnit.getReason());
//        Glide.with(context.getApplicationContext()).load(singleUnit.get).placeholder(R.drawable.img_dp).into(holder.userDp);
        holder.leaveCard.setOnClickListener(v -> {
            showPopup(singleUnit, position);
        });
    }

    @Override
    public int getItemCount() {
        return leaveResultList.size();
    }

    public void showPopup(LeaveResponse.EmployeeRes singleUnit, int position) {
        adDialog.setContentView(R.layout.leave_application_popup);
        adDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        adDialog.setCancelable(false);
        adDialog.show();
        TextView txtReason, txtLeaveType, txtSDate, txtEDate;

        AppCompatButton yesBtn = adDialog.findViewById(R.id.yesBtn);
        AppCompatButton noBtn = adDialog.findViewById(R.id.noBtn);
        txtReason = adDialog.findViewById(R.id.txtReason);
        txtLeaveType = adDialog.findViewById(R.id.txtLeaveType);
        txtSDate = adDialog.findViewById(R.id.txtSDate);
        txtEDate = adDialog.findViewById(R.id.txtEDate);

        txtReason.setText(singleUnit.getReason());
        txtLeaveType.setText("Type:-" + singleUnit.getLeaveType());
        txtSDate.setText("From:-" + singleUnit.getStartDate());
        if (singleUnit.getEndDate() == null) {
            txtEDate.setVisibility(View.GONE);
        } else {

            txtEDate.setVisibility(View.VISIBLE);
            txtEDate.setText("To:-" + (CharSequence) singleUnit.getEndDate());
        }
        yesBtn.setOnClickListener(v -> {
//            Toast toast = Toast.makeText(context.getApplicationContext(), "Employee Removed Successfully", Toast.LENGTH_SHORT);
//            View view1 = toast.getView();
//            view1.setBackgroundResource(R.drawable.bg_red);
//            view1.setPadding(70, 30, 70, 30);
//            toast.show();
            callAcceptRejectApi(singleUnit.getId(), "approved", position);


        });
        noBtn.setOnClickListener(v -> {
            callAcceptRejectApi(singleUnit.getId(), "rejected", position);
        });
//        adDialog.setOnCancelListener(dialog -> adDialog.dismiss());
    }

    private void callAcceptRejectApi(int recordId, String status, int position) {
        ProgressDialog progress = new ProgressDialog(context);
        progress.setMessage("please wait");
        progress.show();
        Call<LeaveAcceptRejectResponse> callAcceptRejectLeave = apiInterface.acceptRejectLeave(recordId, status);
        callAcceptRejectLeave.enqueue(new Callback<>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<LeaveAcceptRejectResponse> call, Response<LeaveAcceptRejectResponse> response) {
                if (response.isSuccessful()) {
                    if (status.equalsIgnoreCase("approved") && response.body().getEmployeeResult().getApplicationStatus().equalsIgnoreCase("approved")) {
                        Toast.makeText(context, "Leave Application Accepted", Toast.LENGTH_SHORT).show();
                    } else if (status.equalsIgnoreCase("rejected") && response.body().getEmployeeResult().getApplicationStatus().equalsIgnoreCase("rejected")) {
                        Toast.makeText(context, "Leave Application Rejected", Toast.LENGTH_SHORT).show();
                    }
                    progress.dismiss();
                    leaveResultList.remove(position);
                    notifyDataSetChanged();
                    adDialog.dismiss();
                } else {
                    adDialog.dismiss();
                    progress.dismiss();
                    leaveResultList.remove(position);
                    notifyDataSetChanged();
                    Log.d("kfsdf", response.message());
                    Toast.makeText(context, "some error occured", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LeaveAcceptRejectResponse> call, Throwable t) {
                Log.d("dfksdf", t.getMessage());
                progress.dismiss();
                Toast.makeText(context, "failure", Toast.LENGTH_SHORT).show();
                adDialog.dismiss();
            }
        });

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout leaveCard;
        TextView txtEmpId, txtReason, txtStartEmpId, txtStartReason, txtPending;
        ImageView userDp;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            leaveCard = itemView.findViewById(R.id.leaveCard);
            txtEmpId = itemView.findViewById(R.id.txtempId);
            txtReason = itemView.findViewById(R.id.txtReason);
            userDp = itemView.findViewById(R.id.imgUser_leave);
            txtStartEmpId = itemView.findViewById(R.id.textView2);
            txtStartReason = itemView.findViewById(R.id.txtStartDate);
            txtPending = itemView.findViewById(R.id.pending);

        }
    }
}










