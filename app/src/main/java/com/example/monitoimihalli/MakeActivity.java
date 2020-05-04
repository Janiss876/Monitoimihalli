package com.example.monitoimihalli;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MakeActivity extends AppCompatActivity {

    Button chooseDate;
    Button confirmReservation;
    Button cancelButton;
    DatePickerDialog dtp;
    TextView chosenDate;
    Spinner spinnerplace;
    Spinner spinnersport;
    Spinner spinnerroom;
    Spinner spinnerhours;
    TextView warningText;
    MakeActivity context = null;
    Reservation reservation = new Reservation();
    String place;
    String sport;
    String room;
    String hours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make);
        context = MakeActivity.this;
        cancelButton = (Button) findViewById(R.id.CancelReservation);
        warningText = (TextView) findViewById(R.id.reservationWarningText);
        chooseDate = (Button) findViewById(R.id.ChooseDateButton);
        chosenDate = (TextView) findViewById(R.id.chosenDate);
        chosenDate.addTextChangedListener(makeTextWatcher);
        confirmReservation = (Button) findViewById((R.id.ConfirmReservation));

        Spinner spinnerplace = findViewById(R.id.spinnerplace);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.place, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerplace.setAdapter(adapter);
        spinnerplace.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                place = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner spinnersport = findViewById(R.id.spinnersport);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.sport, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnersport.setAdapter(adapter1);
        spinnersport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sport = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner spinnerroom = findViewById(R.id.spinnerroom);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.room, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerroom.setAdapter(adapter2);
        spinnerroom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                room = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner spinnerhours = findViewById(R.id.spinnerhours);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.hours, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerhours.setAdapter(adapter3);
        spinnerhours.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hours = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        confirmReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeNewReservation();
            }
        });

        chooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                dtp = new DatePickerDialog(MakeActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        chosenDate.setText(day + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, day);
                dtp.show();

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openReservationActivity();
            }
        });


    }/**

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }**/

    public void makeNewReservation() {
        FileClass fileClass = new FileClass(this);
        String pl = place;
        String sp = sport;
        String rm = room;
        String hs = hours;
        String dt = chosenDate.getText().toString();
        String fn = User.activeUser.getFirstName();
        String ln = User.activeUser.getLastName();
        String em = User.activeUser.getEmail();
        if (reservation.reservationCheck(dt, hs, rm)) {
            warningText.setText("Room, date and hours already reserved, select another room/date/hours");
        } else {
            Reservation.reservations.add(new Reservation(rm, pl, dt, hs, sp, fn, ln, em));
            fileClass.FileWriteReservation();
            openReservationActivity();

        }

    }

    public void openReservationActivity() {
        startActivity(new Intent(MakeActivity.this, ReservationActivity.class));
    }

    private TextWatcher makeTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            confirmReservation.setEnabled(!chosenDate.getText().toString().equals(("Chosen date")));
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}