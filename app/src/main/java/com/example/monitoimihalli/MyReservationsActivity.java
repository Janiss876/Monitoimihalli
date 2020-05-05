package com.example.monitoimihalli;

import android.app.AppComponentFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MyReservationsActivity extends AppCompatActivity {
    Button edit;
    TextView myReservationsText;
    Spinner spinnermyreservation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myreservations);
        edit = (Button) findViewById(R.id.editMyReservation);
        myReservationsText = (TextView) findViewById(R.id.myReservationsText);
        spinnermyreservation =(Spinner) findViewById(R.id.spinnermyreservation);
    }
}
