package com.example.planner;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class CreateDailyActivity extends AppCompatActivity {

    private EditText inputDailyPlan, inputStartTime, inputEndTime;
    private TimePickerDialog timePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_daily);

        inputDailyPlan = findViewById(R.id.input_daily_plan);
        inputStartTime = findViewById(R.id.input_start_time);
        inputEndTime = findViewById(R.id.input_end_time);

        inputStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog(0);
            }
        });

        inputEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog(1);
            }
        });

    }

    private void showTimeDialog(int i) {
        Calendar calendar = Calendar.getInstance();

        /**
         * Initialize TimePicker Dialog
         */
        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                /**
                 * Method ini dipanggil saat kita selesai memilih waktu di DatePicker
                 */

                if (i == 0){
                    inputStartTime.setText(hourOfDay+":"+minute);
                }else{
                    inputEndTime.setText(hourOfDay+":"+minute);
                }

            }
        },
                /**
                 * Tampilkan jam saat ini ketika TimePicker pertama kali dibuka
                 */
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),

                /**
                 * Cek apakah format waktu menggunakan 24-hour format
                 */
                DateFormat.is24HourFormat(this));

        timePickerDialog.show();
    }
}