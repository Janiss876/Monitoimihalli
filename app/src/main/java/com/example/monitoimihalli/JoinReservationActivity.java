package com.example.monitoimihalli;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class JoinReservationActivity extends AppCompatActivity {
    Button chooseDate2;
    Spinner spinner_pickRoom;
    DatePickerDialog dtp2;
    TextView chosenDate2;
    String pickRoom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_reservation);
        chooseDate2= (Button) findViewById(R.id.chooseDate2);
        chosenDate2 = (TextView) findViewById(R.id.chosenDate2);

        Spinner spinner_pickRoom = findViewById(R.id.spinner_pickRoom);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.room, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_pickRoom.setAdapter(adapter1);
        spinner_pickRoom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pickRoom = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        chooseDate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                dtp2 = new DatePickerDialog(JoinReservationActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        chosenDate2.setText(day + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, day);
                dtp2.show();

            }
        });
            }
}


