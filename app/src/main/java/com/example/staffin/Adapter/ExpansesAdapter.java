package com.example.staffin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.staffin.R;
import com.example.staffin.Response.AllExpenses;
import com.example.staffin.Response.AllPayroll;
import com.example.staffin.ShowExpansesActivity;
import com.example.staffin.Response.Expenses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ExpansesAdapter extends RecyclerView.Adapter<ExpansesAdapter.MyViewHolder> {

    Context context;
//     List<AllExpenses.GetAllExpenseDetail> resp;
    List<String> images;
    List<Expenses.GetAllExpenseDetail> resp;

    public ExpansesAdapter(Context context, List<Expenses.GetAllExpenseDetail> resp) {
        this.context = context;
        this.resp = resp;
        images = new ArrayList<>();
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
        Expenses.GetAllExpenseDetail singleUnit = resp.get(position);
        Animation animationLeft = AnimationUtils.loadAnimation(holder.mainConstraint.getContext(), android.R.anim.slide_in_left);
        holder.mainConstraint.startAnimation(animationLeft);
        holder.txtEmpId.startAnimation(animationLeft);
        holder.txtItemName.startAnimation(animationLeft);
        holder.txtPrice.startAnimation(animationLeft);
        holder.btnDownload.startAnimation(animationLeft);

        holder.txtEmpId.setText("EMP ID:-" + singleUnit.getEmployeeId().get(0).getEmployeeID());
        holder.txtItemName.setText("Name:-" + singleUnit.getItemName());
        holder.txtPrice.setText("Price:-" + singleUnit.getPrice());

        holder.btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowExpansesActivity.class);
                Bundle bundle = new Bundle();

                if (!singleUnit.getImage1().substring(singleUnit.getImage1().lastIndexOf("/")).equals("0") &&
                        !singleUnit.getImage2().substring(singleUnit.getImage2().lastIndexOf("/")).equals("0") &&
                        !singleUnit.getImage3().substring(singleUnit.getImage3().lastIndexOf("/")).equals("0") &&
                        !singleUnit.getImage4().substring(singleUnit.getImage4().lastIndexOf("/")).equals("0") &&

                        !singleUnit.getImage5().substring(singleUnit.getImage5().lastIndexOf("/")).equals("0") &&

                        !singleUnit.getImage6().substring(singleUnit.getImage6().lastIndexOf("/")).equals("0") &&

                        !singleUnit.getImage7().substring(singleUnit.getImage7().lastIndexOf("/")).equals("0") &&

                        !singleUnit.getImage8().substring(singleUnit.getImage8().lastIndexOf("/")).equals("0") &&

                        !singleUnit.getImage9().substring(singleUnit.getImage9().lastIndexOf("/")).equals("0") &&

                        !singleUnit.getImage10().substring(singleUnit.getImage10().lastIndexOf("/")).equals("0")) {

                    images.add(singleUnit.getImage1());
                    images.add(singleUnit.getImage2());
                    images.add(singleUnit.getImage3());
                    images.add(singleUnit.getImage4());
                    images.add(singleUnit.getImage5());
                    images.add(singleUnit.getImage6());
                    images.add(singleUnit.getImage7());
                    images.add(singleUnit.getImage8());
                    images.add(singleUnit.getImage9());
                    images.add(singleUnit.getImage10());
                } else if (!singleUnit.getImage1().substring(singleUnit.getImage1().lastIndexOf("/")).equals("0") &&
                        !singleUnit.getImage2().substring(singleUnit.getImage2().lastIndexOf("/")).equals("0") &&
                        !singleUnit.getImage3().substring(singleUnit.getImage3().lastIndexOf("/")).equals("0") &&
                        !singleUnit.getImage4().substring(singleUnit.getImage4().lastIndexOf("/")).equals("0") &&

                        !singleUnit.getImage5().substring(singleUnit.getImage5().lastIndexOf("/")).equals("0") &&

                        !singleUnit.getImage6().substring(singleUnit.getImage6().lastIndexOf("/")).equals("0") &&

                        !singleUnit.getImage7().substring(singleUnit.getImage7().lastIndexOf("/")).equals("0") &&

                        !singleUnit.getImage8().substring(singleUnit.getImage8().lastIndexOf("/")).equals("0") &&

                        !singleUnit.getImage9().substring(singleUnit.getImage9().lastIndexOf("/")).equals("0") &&

                        singleUnit.getImage10().substring(singleUnit.getImage10().lastIndexOf("/")).equals("0")) {
                    images.add(singleUnit.getImage1());
                    images.add(singleUnit.getImage2());
                    images.add(singleUnit.getImage3());
                    images.add(singleUnit.getImage4());
                    images.add(singleUnit.getImage5());
                    images.add(singleUnit.getImage6());
                    images.add(singleUnit.getImage7());
                    images.add(singleUnit.getImage8());
                    images.add(singleUnit.getImage9());
                } else if (!singleUnit.getImage1().substring(singleUnit.getImage1().lastIndexOf("/")).equals("0") &&
                        !singleUnit.getImage2().substring(singleUnit.getImage2().lastIndexOf("/")).equals("0") &&
                        !singleUnit.getImage3().substring(singleUnit.getImage3().lastIndexOf("/")).equals("0") &&
                        !singleUnit.getImage4().substring(singleUnit.getImage4().lastIndexOf("/")).equals("0") &&

                        !singleUnit.getImage5().substring(singleUnit.getImage5().lastIndexOf("/")).equals("0") &&

                        !singleUnit.getImage6().substring(singleUnit.getImage6().lastIndexOf("/")).equals("0") &&

                        !singleUnit.getImage7().substring(singleUnit.getImage7().lastIndexOf("/")).equals("0") &&

                        !singleUnit.getImage8().substring(singleUnit.getImage8().lastIndexOf("/")).equals("0") &&

                        singleUnit.getImage9().substring(singleUnit.getImage9().lastIndexOf("/")).equals("0") &&

                        singleUnit.getImage10().substring(singleUnit.getImage10().lastIndexOf("/")).equals("0")) {
                    images.add(singleUnit.getImage1());
                    images.add(singleUnit.getImage2());
                    images.add(singleUnit.getImage3());
                    images.add(singleUnit.getImage4());
                    images.add(singleUnit.getImage5());
                    images.add(singleUnit.getImage6());
                    images.add(singleUnit.getImage7());
                    images.add(singleUnit.getImage8());
                } else if (!singleUnit.getImage1().substring(singleUnit.getImage1().lastIndexOf("/")).equals("0") &&
                        !singleUnit.getImage2().substring(singleUnit.getImage2().lastIndexOf("/")).equals("0") &&
                        !singleUnit.getImage3().substring(singleUnit.getImage3().lastIndexOf("/")).equals("0") &&
                        !singleUnit.getImage4().substring(singleUnit.getImage4().lastIndexOf("/")).equals("0") &&

                        !singleUnit.getImage5().substring(singleUnit.getImage5().lastIndexOf("/")).equals("0") &&

                        !singleUnit.getImage6().substring(singleUnit.getImage6().lastIndexOf("/")).equals("0") &&

                        !singleUnit.getImage7().substring(singleUnit.getImage7().lastIndexOf("/")).equals("0") &&

                        singleUnit.getImage8().substring(singleUnit.getImage8().lastIndexOf("/")).equals("0") &&

                        singleUnit.getImage9().substring(singleUnit.getImage9().lastIndexOf("/")).equals("0") &&

                        singleUnit.getImage10().substring(singleUnit.getImage10().lastIndexOf("/")).equals("0")) {
                    images.add(singleUnit.getImage1());
                    images.add(singleUnit.getImage2());
                    images.add(singleUnit.getImage3());
                    images.add(singleUnit.getImage4());
                    images.add(singleUnit.getImage5());
                    images.add(singleUnit.getImage6());
                    images.add(singleUnit.getImage7());
                } else if (!singleUnit.getImage1().substring(singleUnit.getImage1().lastIndexOf("/")).equals("0") &&
                        !singleUnit.getImage2().substring(singleUnit.getImage2().lastIndexOf("/")).equals("0") &&
                        !singleUnit.getImage3().substring(singleUnit.getImage3().lastIndexOf("/")).equals("0") &&
                        !singleUnit.getImage4().substring(singleUnit.getImage4().lastIndexOf("/")).equals("0") &&

                        !singleUnit.getImage5().substring(singleUnit.getImage5().lastIndexOf("/")).equals("0") &&

                        !singleUnit.getImage6().substring(singleUnit.getImage6().lastIndexOf("/")).equals("0") &&

                        singleUnit.getImage7().substring(singleUnit.getImage7().lastIndexOf("/")).equals("0") &&

                        singleUnit.getImage8().substring(singleUnit.getImage8().lastIndexOf("/")).equals("0") &&

                        singleUnit.getImage9().substring(singleUnit.getImage9().lastIndexOf("/")).equals("0") &&

                        singleUnit.getImage10().substring(singleUnit.getImage10().lastIndexOf("/")).equals("0")) {
                    images.add(singleUnit.getImage1());
                    images.add(singleUnit.getImage2());
                    images.add(singleUnit.getImage3());
                    images.add(singleUnit.getImage4());
                    images.add(singleUnit.getImage5());
                    images.add(singleUnit.getImage6());
                } else if (!singleUnit.getImage1().substring(singleUnit.getImage1().lastIndexOf("/")).equals("0") &&
                        !singleUnit.getImage2().substring(singleUnit.getImage2().lastIndexOf("/")).equals("0") &&
                        !singleUnit.getImage3().substring(singleUnit.getImage3().lastIndexOf("/")).equals("0") &&
                        !singleUnit.getImage4().substring(singleUnit.getImage4().lastIndexOf("/")).equals("0") &&

                        !singleUnit.getImage5().substring(singleUnit.getImage5().lastIndexOf("/")).equals("0") &&

                        singleUnit.getImage6().substring(singleUnit.getImage6().lastIndexOf("/")).equals("0") &&

                        singleUnit.getImage7().substring(singleUnit.getImage7().lastIndexOf("/")).equals("0") &&

                        singleUnit.getImage8().substring(singleUnit.getImage8().lastIndexOf("/")).equals("0") &&

                        singleUnit.getImage9().substring(singleUnit.getImage9().lastIndexOf("/")).equals("0") &&

                        singleUnit.getImage10().substring(singleUnit.getImage10().lastIndexOf("/")).equals("0")) {
                    images.add(singleUnit.getImage1());
                    images.add(singleUnit.getImage2());
                    images.add(singleUnit.getImage3());
                    images.add(singleUnit.getImage4());
                    images.add(singleUnit.getImage5());
                } else if (!singleUnit.getImage1().substring(singleUnit.getImage1().lastIndexOf("/")).equals("0") &&
                        !singleUnit.getImage2().substring(singleUnit.getImage2().lastIndexOf("/")).equals("0") &&
                        !singleUnit.getImage3().substring(singleUnit.getImage3().lastIndexOf("/")).equals("0") &&
                        !singleUnit.getImage4().substring(singleUnit.getImage4().lastIndexOf("/")).equals("0") &&

                        singleUnit.getImage5().substring(singleUnit.getImage5().lastIndexOf("/")).equals("0") &&

                        singleUnit.getImage6().substring(singleUnit.getImage6().lastIndexOf("/")).equals("0") &&

                        singleUnit.getImage7().substring(singleUnit.getImage7().lastIndexOf("/")).equals("0") &&

                        singleUnit.getImage8().substring(singleUnit.getImage8().lastIndexOf("/")).equals("0") &&

                        singleUnit.getImage9().substring(singleUnit.getImage9().lastIndexOf("/")).equals("0") &&

                        singleUnit.getImage10().substring(singleUnit.getImage10().lastIndexOf("/")).equals("0")) {

                    images.add(singleUnit.getImage1());
                    images.add(singleUnit.getImage2());
                    images.add(singleUnit.getImage3());
                    images.add(singleUnit.getImage4());
                } else if (!singleUnit.getImage1().substring(singleUnit.getImage1().lastIndexOf("/")).equals("0") &&
                        !singleUnit.getImage2().substring(singleUnit.getImage2().lastIndexOf("/")).equals("0") &&
                        !singleUnit.getImage3().substring(singleUnit.getImage3().lastIndexOf("/")).equals("0") &&
                        singleUnit.getImage4().substring(singleUnit.getImage4().lastIndexOf("/")).equals("0") &&

                        singleUnit.getImage5().substring(singleUnit.getImage5().lastIndexOf("/")).equals("0") &&

                        singleUnit.getImage6().substring(singleUnit.getImage6().lastIndexOf("/")).equals("0") &&

                        singleUnit.getImage7().substring(singleUnit.getImage7().lastIndexOf("/")).equals("0") &&

                        singleUnit.getImage8().substring(singleUnit.getImage8().lastIndexOf("/")).equals("0") &&

                        singleUnit.getImage9().substring(singleUnit.getImage9().lastIndexOf("/")).equals("0") &&

                        singleUnit.getImage10().substring(singleUnit.getImage10().lastIndexOf("/")).equals("0")) {
                    images.add(singleUnit.getImage1());
                    images.add(singleUnit.getImage2());
                    images.add(singleUnit.getImage3());
                } else if (!singleUnit.getImage1().substring(singleUnit.getImage1().lastIndexOf("/")).equals("0") &&
                        !singleUnit.getImage2().substring(singleUnit.getImage2().lastIndexOf("/")).equals("0") &&
                        singleUnit.getImage3().substring(singleUnit.getImage3().lastIndexOf("/")).equals("0") &&
                        singleUnit.getImage4().substring(singleUnit.getImage4().lastIndexOf("/")).equals("0") &&

                        singleUnit.getImage5().substring(singleUnit.getImage5().lastIndexOf("/")).equals("0") &&

                        singleUnit.getImage6().substring(singleUnit.getImage6().lastIndexOf("/")).equals("0") &&

                        singleUnit.getImage7().substring(singleUnit.getImage7().lastIndexOf("/")).equals("0") &&

                        singleUnit.getImage8().substring(singleUnit.getImage8().lastIndexOf("/")).equals("0") &&

                        singleUnit.getImage9().substring(singleUnit.getImage9().lastIndexOf("/")).equals("0") &&

                        singleUnit.getImage10().substring(singleUnit.getImage10().lastIndexOf("/")).equals("0")) {
                    images.add(singleUnit.getImage1());
                    images.add(singleUnit.getImage2());
                } else if (!singleUnit.getImage1().substring(singleUnit.getImage1().lastIndexOf("/")).equals("0") &&
                        singleUnit.getImage2().substring(singleUnit.getImage2().lastIndexOf("/")).equals("0") &&
                        singleUnit.getImage3().substring(singleUnit.getImage3().lastIndexOf("/")).equals("0") &&
                        singleUnit.getImage4().substring(singleUnit.getImage4().lastIndexOf("/")).equals("0") &&

                        singleUnit.getImage5().substring(singleUnit.getImage5().lastIndexOf("/")).equals("0") &&

                        singleUnit.getImage6().substring(singleUnit.getImage6().lastIndexOf("/")).equals("0") &&

                        singleUnit.getImage7().substring(singleUnit.getImage7().lastIndexOf("/")).equals("0") &&

                        singleUnit.getImage8().substring(singleUnit.getImage8().lastIndexOf("/")).equals("0") &&

                        singleUnit.getImage9().substring(singleUnit.getImage9().lastIndexOf("/")).equals("0") &&

                        singleUnit.getImage10().substring(singleUnit.getImage10().lastIndexOf("/")).equals("0")) {
                    images.add(singleUnit.getImage1());
                }

                bundle.putStringArrayList("img", (ArrayList<String>) images);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return resp.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
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
