package com.example.staffin.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.staffin.InsideEvent;
import com.example.staffin.Interface.ApiInterface;
import com.example.staffin.R;
import com.example.staffin.Response.AllEvents;
import com.example.staffin.Response.EventsByYearResponse;
import com.example.staffin.Response.EventsMix;
import com.example.staffin.Retrofit.RetrofitServices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {

    Context context;
    int month;
    List<EventsMix> eventsMixList;
    List<EventsMix> currentMonthEventsList;
    String image, image1, image2, image3, title, desc, date, location;
    String[] FirstImage;
    int count;
    ArrayList<String> interestedMembers;


    public EventAdapter(Context context, int month, List<EventsMix> eventsMixList) {
        this.context = context;
        this.month = month;
        this.eventsMixList = eventsMixList;
        currentMonthEventsList = new ArrayList<>();
        for (EventsMix e : eventsMixList) {
            if (e.getMonth() == month) {
                currentMonthEventsList.add(e);
            }
        }
        interestedMembers = new ArrayList<>();
    }

    @NonNull
    @Override
    public EventAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_event_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventAdapter.MyViewHolder holder, int position) {

        EventsMix singleUnit = currentMonthEventsList.get(position);
        holder.txtDate.setText(singleUnit.getDate());
        holder.txtEventName.setText(singleUnit.getTitleName());
        Glide.with(context.getApplicationContext()).load(singleUnit.getImage()).placeholder(R.drawable.img_birthday).into(holder.imageView);


        FirstImage = (singleUnit.getAdd_member_images().split(",,,,,,,,,,"));
        Log.e("onBindViewHolder: ", "Images");
        if (Integer.parseInt(singleUnit.getAdd_member_count()) == 1) {
            Log.e("onBindViewHolder: ", "FirstImages");
            String image1 = FirstImage[0];
            holder.el1.setVisibility(View.VISIBLE);
            holder.el2.setVisibility(View.INVISIBLE);
            holder.el3.setVisibility(View.INVISIBLE);
            holder.el4.setVisibility(View.INVISIBLE);
            Glide.with(context.getApplicationContext()).load(image1).placeholder(R.drawable.img_user).into(holder.el1);
        } else if (Integer.parseInt(singleUnit.getAdd_member_count()) == 2) {
            Log.e("onBindViewHolder: ", "SecondImages");
            String image1 = FirstImage[0];
            String image2 = FirstImage[1];
            holder.el1.setVisibility(View.VISIBLE);
            holder.el2.setVisibility(View.VISIBLE);
            holder.el3.setVisibility(View.INVISIBLE);
            holder.el4.setVisibility(View.INVISIBLE);
            Glide.with(context.getApplicationContext()).load(image1).placeholder(R.drawable.img_user).into(holder.el1);
            Glide.with(context.getApplicationContext()).load(image2).placeholder(R.drawable.img_user).into(holder.el2);
        } else if (Integer.parseInt(singleUnit.getAdd_member_count()) == 3) {
            Log.e("onBindViewHolder: ", "ThirdImages");
            String image1 = FirstImage[0];
            String image2 = FirstImage[1];
            String image3 = FirstImage[2];
            holder.el1.setVisibility(View.VISIBLE);
            holder.el2.setVisibility(View.VISIBLE);
            holder.el3.setVisibility(View.VISIBLE);
            holder.el4.setVisibility(View.INVISIBLE);
            Glide.with(context.getApplicationContext()).load(image1).placeholder(R.drawable.img_user).into(holder.el1);
            Glide.with(context.getApplicationContext()).load(image2).placeholder(R.drawable.img_user).into(holder.el2);
            Glide.with(context.getApplicationContext()).load(image3).placeholder(R.drawable.img_user).into(holder.el3);
        } else if (Integer.parseInt(singleUnit.getAdd_member_count()) >= 4) {
            String image1 = FirstImage[0];
            String image2 = FirstImage[1];
            String image3 = FirstImage[2];
            holder.el1.setVisibility(View.VISIBLE);
            holder.el2.setVisibility(View.VISIBLE);
            holder.el3.setVisibility(View.VISIBLE);
            Log.e("onBindViewHolder: ", "CountImages");
            Glide.with(context.getApplicationContext()).load(image1).placeholder(R.drawable.img_user).into(holder.el1);
            Glide.with(context.getApplicationContext()).load(image2).placeholder(R.drawable.img_user).into(holder.el2);
            Glide.with(context.getApplicationContext()).load(image3).placeholder(R.drawable.img_user).into(holder.el3);
            count = Integer.parseInt(singleUnit.getAdd_member_count());
            count -= 3;
            if (count == 0) {
                holder.el4.setVisibility(View.INVISIBLE);
            } else {
                holder.el4.setVisibility(View.VISIBLE);
                holder.el4.setText(count + "+");
            }
        } else {
            Log.e("onBindViewHolder: ", "else me aagya");
            holder.el1.setVisibility(View.INVISIBLE);
            holder.el2.setVisibility(View.INVISIBLE);
            holder.el3.setVisibility(View.INVISIBLE);
            holder.el4.setVisibility(View.INVISIBLE);
        }


        List<String> membersArray = List.of(singleUnit.getAddMember().split(","));

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InsideEvent.class);
                image = singleUnit.getImage();
                image1 = singleUnit.getImg1();
                image2 = singleUnit.getImg2();
                image3 = singleUnit.getImg3();
                title = singleUnit.getTitleName();
                desc = singleUnit.getDescription();
                date = singleUnit.getDate();
                location = singleUnit.getLocation();

                interestedMembers.clear();

                if (singleUnit.getAdd_intruted_member() != null) {
                    Log.d("fsdfsdfasd", singleUnit.getAdd_intruted_member());

                    if (singleUnit.getAdd_intruted_member().contains(",,,,,,,,,")) {
                        String[] names = new String[singleUnit.getAdd_intruted_member().split(",,,,,,,,,").length];
                        names = singleUnit.getAdd_intruted_member().split(",,,,,,,,,");

                        for (String name : names) {
                            if (!Objects.equals(name, "0")) {
                                interestedMembers.add(name);
                            }
                        }

                        intent.putStringArrayListExtra("interested", interestedMembers);
                    } else {
                        interestedMembers.add(singleUnit.getAdd_intruted_member());
                        intent.putStringArrayListExtra("interested", interestedMembers);
                    }
                }


                intent.putExtra("image", image);
                intent.putExtra("image1", image1);
                intent.putExtra("image2", image2);
                intent.putExtra("image3", image3);
                intent.putExtra("title", title);
                intent.putExtra("desc", desc);
                intent.putExtra("date", date);
                intent.putExtra("location", location);


                intent.putStringArrayListExtra("members", new ArrayList<>(membersArray));

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return currentMonthEventsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtDate, txtEventName, interested, location, el4;
        ConstraintLayout card;
        ImageView imageView, el1, el2, el3;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.cardEvent);
            imageView = itemView.findViewById(R.id.imageView);
            el4 = itemView.findViewById(R.id.el4);
//            interested = itemView.findViewById(R.id.interested);
            location = itemView.findViewById(R.id.otp3);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtEventName = itemView.findViewById(R.id.txtEventName);
            el1 = itemView.findViewById(R.id.el1);
            el2 = itemView.findViewById(R.id.el2);
            el3 = itemView.findViewById(R.id.el3);
        }
    }
}
