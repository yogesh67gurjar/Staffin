package com.example.staffin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.staffin.InsideEvent;
import com.example.staffin.R;
import com.example.staffin.Response.AllEvents;
import com.example.staffin.Response.EventsByYearResponse;
import com.example.staffin.Response.EventsMix;

import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {

    Context context;
    int month;
    List<EventsMix> eventsMixList;
    List<EventsMix> currentMonthEventsList;
    String image, image1, image2, image3, title, desc, date, location;
    String[] membersArray;

//    List<EventsByYearResponse.EventDetails.January> januaries;
//    List<EventsByYearResponse.EventDetails.February> februaries;
//    List<EventsByYearResponse.EventDetails.March> marches;
//    List<EventsByYearResponse.EventDetails.April> aprils;
//    List<EventsByYearResponse.EventDetails.May> mays;
//    List<EventsByYearResponse.EventDetails.June> junes;
//    List<EventsByYearResponse.EventDetails.July> julies;
//    List<EventsByYearResponse.EventDetails.August> augusts;
//    List<EventsByYearResponse.EventDetails.September> septembers;
//    List<EventsByYearResponse.EventDetails.October> octobers;
//    List<EventsByYearResponse.EventDetails.November> novembers;
//    List<EventsByYearResponse.EventDetails.December> decembers;
//    int size;


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
    }
//    public EventAdapter(Context context, List<EventsByYearResponse.EventDetails.January> januaries, List<EventsByYearResponse.EventDetails.February> februaries, List<EventsByYearResponse.EventDetails.March> marches, List<EventsByYearResponse.EventDetails.April> aprils, List<EventsByYearResponse.EventDetails.May> mays, List<EventsByYearResponse.EventDetails.June> junes, List<EventsByYearResponse.EventDetails.July> julies, List<EventsByYearResponse.EventDetails.August> augusts, List<EventsByYearResponse.EventDetails.September> septembers, List<EventsByYearResponse.EventDetails.October> octobers, List<EventsByYearResponse.EventDetails.November> novembers, List<EventsByYearResponse.EventDetails.December> decembers) {
//        this.context = context;
//        this.januaries = januaries;
//        this.februaries = februaries;
//        this.marches = marches;
//        this.aprils = aprils;
//        this.mays = mays;
//        this.junes = junes;
//        this.julies = julies;
//        this.augusts = augusts;
//        this.septembers = septembers;
//        this.octobers = octobers;
//        this.novembers = novembers;
//        this.decembers = decembers;
//
//        if (januaries != null) {
//            size = januaries.size();
//        } else if (februaries != null) {
//            size = februaries.size();
//        } else if (marches != null) {
//            size = marches.size();
//        } else if (aprils != null) {
//            size = aprils.size();
//        } else if (mays != null) {
//            size = mays.size();
//        } else if (junes != null) {
//            size = junes.size();
//        } else if (julies != null) {
//            size = julies.size();
//        } else if (augusts != null) {
//            size = augusts.size();
//        } else if (septembers != null) {
//            size = septembers.size();
//        } else if (octobers != null) {
//            size = octobers.size();
//        } else if (novembers != null) {
//            size = novembers.size();
//        } else if (decembers != null) {
//            size = decembers.size();
//        }
//    }

    @NonNull
    @Override
    public EventAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_event_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventAdapter.MyViewHolder holder, int position) {
//
//        if (januaries != null) {
//            EventsByYearResponse.EventDetails.January singleUnit = januaries.get(position);
//            holder.txtDate.setText(singleUnit.getDate());
//            holder.txtEventName.setText(singleUnit.getTitleName());
//        } else if (februaries != null) {
//            EventsByYearResponse.EventDetails.February singleUnit = februaries.get(position);
//            holder.txtDate.setText(singleUnit.getDate());
//            holder.txtEventName.setText(singleUnit.getTitleName());
//        } else if (marches != null) {
//            EventsByYearResponse.EventDetails.March singleUnit = marches.get(position);
//            holder.txtDate.setText(singleUnit.getDate());
//            holder.txtEventName.setText(singleUnit.getTitleName());
//        } else if (aprils != null) {
//            EventsByYearResponse.EventDetails.April singleUnit = aprils.get(position);
//            holder.txtDate.setText(singleUnit.getDate());
//            holder.txtEventName.setText(singleUnit.getTitleName());
//        } else if (mays != null) {
//            EventsByYearResponse.EventDetails.May singleUnit = mays.get(position);
//            holder.txtDate.setText(singleUnit.getDate());
//            holder.txtEventName.setText(singleUnit.getTitleName());
//        } else if (junes != null) {
//            EventsByYearResponse.EventDetails.June singleUnit = junes.get(position);
//            holder.txtDate.setText(singleUnit.getDate());
//            holder.txtEventName.setText(singleUnit.getTitleName());
//        } else if (julies != null) {
//            EventsByYearResponse.EventDetails.July singleUnit = julies.get(position);
//            holder.txtDate.setText(singleUnit.getDate());
//            holder.txtEventName.setText(singleUnit.getTitleName());
//        } else if (augusts != null) {
//            EventsByYearResponse.EventDetails.August singleUnit = augusts.get(position);
//            holder.txtDate.setText(singleUnit.getDate());
//            holder.txtEventName.setText(singleUnit.getTitleName());
//        } else if (septembers != null) {
//            EventsByYearResponse.EventDetails.September singleUnit = septembers.get(position);
//            holder.txtDate.setText(singleUnit.getDate());
//            holder.txtEventName.setText(singleUnit.getTitleName());
//        } else if (octobers != null) {
//            EventsByYearResponse.EventDetails.October singleUnit = octobers.get(position);
//            holder.txtDate.setText(singleUnit.getDate());
//            holder.txtEventName.setText(singleUnit.getTitleName());
//        } else if (novembers != null) {
//            EventsByYearResponse.EventDetails.November singleUnit = novembers.get(position);
//            holder.txtDate.setText(singleUnit.getDate());
//            holder.txtEventName.setText(singleUnit.getTitleName());
//        } else if (decembers != null) {
//            EventsByYearResponse.EventDetails.December singleUnit = decembers.get(position);
//            holder.txtDate.setText(singleUnit.getDate());
//            holder.txtEventName.setText(singleUnit.getTitleName());
//        }
//
        EventsMix singleUnit = currentMonthEventsList.get(position);
        holder.txtDate.setText(singleUnit.getDate());
        holder.txtEventName.setText(singleUnit.getTitleName());
//        Glide.with(context.getApplicationContext()).load(singleUnit.getImg1()).placeholder(R.drawable.eventimg).into(holder.imageView);
        holder.location.setText(singleUnit.getLocation());
        Glide.with(context.getApplicationContext()).load(singleUnit.getImg1()).into(holder.imageView);
//        holder.interested.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "You are interested in this event", Toast.LENGTH_SHORT).show();
//            }
//        });
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                membersArray = new String[singleUnit.getAddMember().split(",").length];
                List<String> membersArray = List.of(singleUnit.getAddMember().split(","));

                Intent intent = new Intent(context, InsideEvent.class);
                image = singleUnit.getImage();
                image1 = singleUnit.getImg1();
                image2 = singleUnit.getImg2();
                image3 = singleUnit.getImg3();
                title = singleUnit.getTitleName();
                desc = singleUnit.getDescription();
                date = singleUnit.getDate();
                location = singleUnit.getLocation();

                intent.putExtra("image", image);
                intent.putExtra("image1", image1);
                intent.putExtra("image2", image2);
                intent.putExtra("image3", image3);
                intent.putExtra("title", title);
                intent.putExtra("desc", desc);
                intent.putExtra("date", date);
                intent.putExtra("location", location);
                intent.putStringArrayListExtra("members", new ArrayList<>(membersArray));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);


//                context.startActivity(new Intent(context, InsideEvent.class));
//                Toast.makeText(context, singleUnit.getTitleName() + singleUnit.getDate(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return currentMonthEventsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtDate, txtEventName, interested, location;
        ConstraintLayout card;
        ImageView imageView, el1, el2, el3;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.cardEvent);
            imageView = itemView.findViewById(R.id.imageView);
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
