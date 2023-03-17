package com.example.staffin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.staffin.R;

public class TotalEmployeeAdapter extends RecyclerView.Adapter<TotalEmployeeAdapter.MyViewHolder> {
    Context context;

    public TotalEmployeeAdapter(Context context) {
        this.context = context;
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
        holder.btnEdit.setOnClickListener(v -> {
            Toast.makeText(context, "Edit Button...", Toast.LENGTH_SHORT).show();
        });
        holder.btnDelete.setOnClickListener(v -> {
            Toast.makeText(context, "Delete Button...", Toast.LENGTH_SHORT).show();

        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageButton btnEdit, btnDelete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
