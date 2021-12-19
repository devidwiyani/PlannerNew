package com.example.planner;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ArrayList<DailyPlaner> dailyPlanerArrayList;
    DBHelper myDB;
    RecyclerViewAdapter customAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        dailyPlanerArrayList = new ArrayList<>();
        myDB = new DBHelper(ListActivity.this);

        //dailyPlanerArrayList = myDB.read();


        customAdapter = new RecyclerViewAdapter(dailyPlanerArrayList, ListActivity.this);
        recyclerView = findViewById(R.id.recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListActivity.this, RecyclerView.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(customAdapter);


    }

}