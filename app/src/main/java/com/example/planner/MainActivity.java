package com.example.planner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageButton btnDaily, btnEvent, btnLogout;
    TextView name;
    SharedPrefManager spm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDaily = findViewById(R.id.btn_daily);
        btnEvent = findViewById(R.id.btn_event);
        btnLogout = findViewById(R.id.btn_logout);

        Intent bundle = getIntent();
        name = findViewById(R.id.name);

        name.setText(bundle.getStringExtra("data_name"));

        btnDaily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DailyActivity.class);
                startActivity(intent);
            }
        });

        btnEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EventActivity.class);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Logout",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                spm.clearLoggedInUser(v.getContext());
                startActivity(intent);
            }
        });
    }
}