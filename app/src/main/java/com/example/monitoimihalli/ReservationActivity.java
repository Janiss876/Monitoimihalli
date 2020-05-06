package com.example.monitoimihalli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ReservationActivity extends AppCompatActivity {
    Button makeReservationButton;
    Button checkReservationsButton;
    Button editButton;
    Button logOutButton;
    Button showMyReservations;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        makeReservationButton = (Button) findViewById(R.id.makeReservation);
        editButton = (Button) findViewById(R.id.editButton);
        logOutButton = (Button) findViewById(R.id.logOutButton);
        checkReservationsButton = (Button) findViewById(R.id.checkAllReservations);
        showMyReservations = (Button) findViewById(R.id.showMyreservations);

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
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
            }
        });
        checkReservationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opencheckReservations();
            }
        });

        showMyReservations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMyReservations();
            }
        });
    }

    public void openDetailsActivity() {
        startActivity(new Intent(ReservationActivity.this, AccountDetailsActivity.class));
    }

    public void openMakeActivity() {
        startActivity(new Intent(ReservationActivity.this, MakeActivity.class));
    }

    public void logOut() {
        User.activeUser = null;
        startActivity(new Intent(ReservationActivity.this, MainActivity.class));
    }
    public void opencheckReservations() {
        startActivity(new Intent(ReservationActivity.this, JoinReservationActivity.class));
    }
    public void openMyReservations() {
        startActivity(new Intent(ReservationActivity.this, MyReservationsActivity.class));
    }
}