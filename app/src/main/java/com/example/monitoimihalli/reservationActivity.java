package com.example.monitoimihalli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class reservationActivity extends AppCompatActivity {
    Button makeReservationButton;
    Button checkReservationsButton;
    Button editButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        makeReservationButton = (Button) findViewById(R.id.makeReservation);
        editButton = (Button) findViewById(R.id.editButton);
        makeReservationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMakeActivity();
            }

        });
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetailsActivity();
            }
        });
    }

    public void openDetailsActivity() {
        startActivity(new Intent(reservationActivity.this, AccountDetailsActivity.class));
    }

    public void openMakeActivity() {
        startActivity(new Intent(reservationActivity.this, makeActivity.class));
    }
}