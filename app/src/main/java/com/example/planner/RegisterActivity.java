package com.example.planner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    public static final String KEY = "seekBarValue";
    int seekBarValue;
    TextView alertNama, alertEmail, alertUmur, alertGender, alertStatus, umur1, alertGreeting;
    EditText regUsername, regPassword, regName;
    SeekBar seekBar;
    RadioGroup groupRadio;
    RadioButton buttonRadio1, buttonRadio2;
    CheckBox checkBox;
    Button btnRegister, btnOk;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHelper = new DBHelper(this);

        regUsername = findViewById(R.id.reg_username);
        regPassword = findViewById(R.id.reg_password);;
        regName = findViewById(R.id.reg_name);
        seekBar = findViewById(R.id.seek_bar);
        umur1 = findViewById(R.id.text_umur1);
        groupRadio = findViewById(R.id.button_radio);
        buttonRadio1 = findViewById(R.id.button_radio1);
        buttonRadio2 = findViewById(R.id.button_radio2);
        checkBox = findViewById(R.id.check_box);
        btnRegister = findViewById(R.id.btn_register);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                umur1.setText(String.valueOf(progress));
                seekBarValue = progress;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getUsername = regUsername.getText().toString();
                String getPassword = regPassword.getText().toString();
                String getName = regName.getText().toString();
                int getUmur = seekBar.getProgress();
                int selectedGender = groupRadio.getCheckedRadioButtonId();

                if (getUsername.isEmpty() || getPassword.isEmpty() || getName.isEmpty() || seekBar.getProgress() == 0 || groupRadio.getCheckedRadioButtonId() == 0 ){
                    Toast.makeText(RegisterActivity.this, "Data Belum Lengkap", Toast.LENGTH_SHORT).show();
                }else {
                    // MEMBUAT ALERT DIALOG
                    AlertDialog.Builder dialog1 = new AlertDialog.Builder(RegisterActivity.this);
                    View view = getLayoutInflater().inflate(R.layout.custom_alert,null);

                    alertGreeting = view.findViewById(R.id.alert_greeting);
                    alertNama = view.findViewById(R.id.alert_nama);
                    alertEmail = view.findViewById(R.id.alert_email);
                    alertUmur = view.findViewById(R.id.alert_umur);
                    alertGender = view.findViewById(R.id.alert_gender);
                    alertStatus = view.findViewById(R.id.alert_status);

                    // MENGESET GREETING PADA ALERT DIALOG
                    alertGreeting.setText("Hi " + getName + " \nberikut data kamu yang akan disimpan : ");
                    alertNama.setText(getUsername);
                    alertUmur.setText(String.valueOf(getUmur));

                    // MENGESET TEXT GENDER PADA ALERT DIALOG BERDASARKAN GENDER YANG DI-SELECT
                    if (selectedGender == buttonRadio1.getId()){
                        alertGender.setText("Perempuan");
                    } else if (selectedGender == buttonRadio2.getId()){
                        alertGender.setText("Laki- Laki");
                    }
                    String getGender = alertGender.getText().toString();

                    // MENGESET TEXT KEBENARAN DATA PADA ALERT DIALOG BERDASARKAN STATUS CENTANG PADA CHECK BOX
                    if (checkBox.isChecked()) {
                        alertStatus.setText("( Data Sudah Benar )");
                    } else {
                        alertStatus.setText("( Pastikan Data Benar)");
                    }

                    view.findViewById(R.id.button_ok).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            ContentValues values = new ContentValues();
                            Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
//                            intent.putExtra("data_username", getUsername);
//                            intent.putExtra("data_password", getPassword);
                            intent.putExtra("data_name", getName);
//                            intent.putExtra("data_umur", getUmur);
//                            intent.putExtra("data_gender", getGender);
                            startActivity(intent);

                            values.put(DBHelper.row_username, getUsername);
                            values.put(DBHelper.row_password, getPassword);
                            values.put(DBHelper.row_name, getName);
                            values.put(DBHelper.row_umur, getUmur);
                            values.put(DBHelper.row_gender, getGender);
                            dbHelper.insertUser(values);

                            Toast.makeText(RegisterActivity.this, "Register Succesful", Toast.LENGTH_SHORT).show();

                        }
                    });

                    dialog1.setView(view);
                    AlertDialog dialog2 = dialog1.create();
                    dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog2.show();
                }

        }
    });}}