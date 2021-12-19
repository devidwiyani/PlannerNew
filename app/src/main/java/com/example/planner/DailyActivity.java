package com.example.planner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DailyActivity extends AppCompatActivity {

    Button btnCretaeDaily;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);

        btnCretaeDaily = findViewById(R.id.btn_create_daily);
        btnCretaeDaily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DailyActivity.this, CreateDailyActivity.class);
                startActivity(intent);
            }
        });
    }
}