package com.example.monitoimihalli;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
    public static List<String> myReservations = new ArrayList<String>();
    String myreservation;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myreservations);

        edit = (Button) findViewById(R.id.editMyReservation);
        myReservationsText = (TextView) findViewById(R.id.myReservationsText);


        showmy = (Button) findViewById(R.id.showmy);
        showmy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMyreservations();
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEdit(myreservation);
            }
        });
        spinnermyreservation = (Spinner) findViewById(R.id.spinnermyreservation);
        ArrayAdapter<String> adapter = new  ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, myReservations);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnermyreservation.setAdapter(adapter);spinnermyreservation.setAdapter(adapter);
        spinnermyreservation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                myreservation = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    @SuppressLint("SetTextI18n")
    public void showMyreservations() {
        myReservationsText.setText("");
        for (Reservation r : Reservation.reservations) {
            if (r.getEmail().equals(User.activeUser.getEmail())) {
                String dt = r.getDate();
                String h = r.getHours();
                String pl = r.getPlace();
                String sp = r.getSport();
                String rm = r.getRoomNumber();
                myReservationsText.append("Date: " + dt + " Time: " + h + " Place: " + pl + " Sport: " + sp + " Room: " + rm + "\n" + "\n");
                String all = (dt+": "+ h + ": "+pl+ ": "+sp+ ": "+rm);
                myReservations.add(all);
            }

        }
        if (myReservationsText.getText().toString().equals("")) {
            myReservationsText.setText("You have no reservations");



        }

    }
    public void  openEdit(String myreservation) {
        startActivity(new Intent(MyReservationsActivity.this,ReservationEditActivity.class));


    }
}

