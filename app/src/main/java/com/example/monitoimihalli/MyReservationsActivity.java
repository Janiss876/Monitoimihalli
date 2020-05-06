package com.example.monitoimihalli;

import android.app.AppComponentFactory;
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
    List<String> optionsList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myreservations);

        edit = (Button) findViewById(R.id.editMyReservation);
        myReservationsText = (TextView) findViewById(R.id.myReservationsText);
        spinnermyreservation = (Spinner) findViewById(R.id.spinnermyreservation);

        showMyreservations();

        ArrayAdapter<String> adapter = new  ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, optionsList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnermyreservation.setAdapter(adapter);
        spinnermyreservation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void showMyreservations () {
        myReservationsText.setText("");
        int numb = 0;
        for (Reservation r : Reservation.reservations) {
            numb = numb + 1;
            if (r.getEmail().equals(User.activeUser.getEmail())) {
                String dt = r.getDate();
                String h = r.getHours();
                String pl = r.getPlace();
                String sp = r.getSport();
                String rm = r.getRoomNumber();
                myReservationsText.append(numb + ". " + "Date: " + dt + ", Time: " + h + ", Place: " + pl + ", Sport: " + sp + ", Room: " + rm + "\n" + "\n");
                String info = numb + ". " + dt + ":" + h + ":" + pl + ":" + sp + ":" + rm;
                optionsList.add(info);
            }

        }
        if (myReservationsText.getText().toString().equals("")) {
            myReservationsText.setText("You have no reservations");
        }
    }
}

