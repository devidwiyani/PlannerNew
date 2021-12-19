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

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    ArrayList<DailyPlaner> ArrayList;
    private Context context;

    //konstruktor
    RecyclerViewAdapter(ArrayList<DailyPlaner> dailyPlanerArrayList, Context context){
        this.assignmentArrayList = dailyPlanerArrayList;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_assignment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //mengambil data assignmentArrayList berdsarkan posisi
        DailyPlaner list = assignmentArrayList.get(position);
        //setText sesuai data pada list
        holder.title.setText(list.getAssignmentTitle());
        holder.course.setText(list.getAssignmentCourse());
        holder.date.setText(list.getAssignmentDate());

        //ke update
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, UpdateActivity.class);

                i.putExtra("id", String.valueOf(list.getId()));
                i.putExtra("title", list.getAssignmentTitle());
                i.putExtra("course", list.getAssignmentCourse());
                i.putExtra("date", list.getAssignmentDate());
                i.putExtra("note", list.getAssignmentNote());

                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return assignmentArrayList.size();
    }

    //ViewHolder Digunakan Untuk Menyimpan Referensi Dari View-View
    class ViewHolder extends RecyclerView.ViewHolder{

        TextView title, course, date;

         ViewHolder(@NonNull View itemView) {

             //menginisialisasi view untuk diguanakn pada recycler view
            super(itemView);
            title = itemView.findViewById(R.id.title);
            course = itemView.findViewById(R.id.course);
            date = itemView.findViewById(R.id.date);

        }


    }


}
