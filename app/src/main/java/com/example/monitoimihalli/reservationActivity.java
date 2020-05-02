package com.example.monitoimihalli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class reservationActivity extends AppCompatActivity {
    Button makeReservationButton;
    Button checkReservationsButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        makeReservationButton = (Button) findViewById(R.id.makeReservation);
        makeReservationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMakeActivity();
            }

        });
    }


    public void openMakeActivity() {
        startActivity(new Intent(reservationActivity.this, makeActivity.class));
    }
}