package com.example.planner;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder>{

    ArrayList<Event> eventArrayList;
    private Context context;

    //konstruktor
    EventAdapter(ArrayList<Event> assignmentArrayList, Context context){
        this.eventArrayList = assignmentArrayList;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //mengambil data assignmentArrayList berdsarkan posisi
        Event list = eventArrayList.get(position);
        //setText sesuai data pada list
        holder.eventPlanName.setText(list.getEventPlan());
        holder.eventPlanLocation.setText(list.getEventLocation());
        holder.eventPlanDate.setText(list.getEventDate());
        holder.eventPlanTime.setText(list.getEventTime());

        //ke update
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, CreateEventActivity.class);

                i.putExtra("id", String.valueOf(list.getId()));
                i.putExtra("eventName", list.getEventPlan());
                i.putExtra("eventLocation", list.getEventLocation());
                i.putExtra("eventDate", list.getEventDate());
                i.putExtra("eventTime", list.getEventTime());

                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return eventArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView eventPlanName, eventPlanLocation, eventPlanDate, eventPlanTime;

        ViewHolder(@NonNull View itemView) {

            //menginisialisasi view untuk diguanakn pada recycler view
            super(itemView);
            eventPlanName = itemView.findViewById(R.id.event_plan_name);
            eventPlanLocation = itemView.findViewById(R.id.event_plan_location);
            eventPlanDate = itemView.findViewById(R.id.event_plan_date);
            eventPlanTime = itemView.findViewById(R.id.event_plan_time);


        }

    }
}
