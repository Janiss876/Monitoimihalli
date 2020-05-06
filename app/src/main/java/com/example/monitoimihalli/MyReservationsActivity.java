package com.example.monitoimihalli;

import android.app.AppComponentFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MyReservationsActivity extends AppCompatActivity {
    Button edit;
    TextView myReservationsText;
    Spinner spinnermyreservation;
    Button showmy;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myreservations);

        edit = (Button) findViewById(R.id.editMyReservation);
        myReservationsText = (TextView) findViewById(R.id.myReservationsText);
        spinnermyreservation = (Spinner) findViewById(R.id.spinnermyreservation);
        showmy = (Button) findViewById(R.id.showmy);
        showmy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMyreservations();
            }
        });
    }
    public void showMyreservations () {
        myReservationsText.setText("");
        List<String> optionsList = new ArrayList<String>();
        for (Reservation r : Reservation.reservations) {
            if (r.getEmail().equals(User.activeUser.getEmail())) {
                String dt = r.getDate();
                String h = r.getHours();
                String pl = r.getPlace();
                String sp = r.getSport();
                String rm = r.getRoomNumber();
                myReservationsText.append("Date: " + dt + " Time: " + h + " Place: " + pl + " Sport: " + sp + " Room: " + rm + "\n" + "\n");

            }

        }
        if (myReservationsText.getText().toString().equals("")) {
            myReservationsText.setText("You have no reservations");
        }
    }
}

