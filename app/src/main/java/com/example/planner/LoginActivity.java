package com.example.planner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {


    EditText logUsername, logPassword;
    Button btnLogin;
    DBHelper dbHelper;
    SharedPrefManager spm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(spm.getSPId(this) > 0)
        {
            Intent toDashboard = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(toDashboard);
        }

        logUsername = findViewById(R.id.log_username);
        logPassword = findViewById(R.id.log_password);
        btnLogin = findViewById(R.id.btn_login);
        dbHelper = new DBHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String getUsername = logUsername.getText().toString().trim();
                String getPassword = logPassword.getText().toString().trim();

                String getName = dbHelper.selectUser(getUsername);
                Toast.makeText(LoginActivity.this, "username :" +getUsername, Toast.LENGTH_SHORT).show();

                Boolean res = dbHelper.checkUser(getUsername, getPassword);
                if (res){
                    Toast.makeText(LoginActivity.this, "Login Success!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    spm.saveSPInt(getBaseContext(), spm.SP_ID, dbHelper.checkUserId(getUsername, getPassword));
                    intent.putExtra("data_name", getName);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "Login Failed!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void klikRegister(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Exit Application?");
        alertDialogBuilder
                .setMessage("Click yes to exit!")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}