package com.example.monitoimihalli;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MakeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button chooseDate;
    Button confirmReservation;
    DatePickerDialog dtp;
    TextView chosenDate;
    Spinner spinnerplace;
    Spinner spinnersport;
    Spinner spinnerroom;
    Spinner spinnerhours;
    TextView warningText;
    MakeActivity context = null;
    Reservation reservation = new Reservation();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make);
        context = MakeActivity.this;
        Spinner spinnerplace = findViewById(R.id.spinnerplace);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.place, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerplace.setAdapter(adapter);
        spinnerplace.setOnItemSelectedListener(this);
        spinnerplace.getOnItemSelectedListener().toString();

        Spinner spinnersport = findViewById(R.id.spinnersport);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.sport, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnersport.setAdapter(adapter1);
        spinnersport.setOnItemSelectedListener(this);

        Spinner spinnerroom = findViewById(R.id.spinnerroom);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.room, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerroom.setAdapter(adapter2);
        spinnerroom.setOnItemSelectedListener(this);

        Spinner spinnerhours = findViewById(R.id.spinnerhours);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.hours, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerhours.setAdapter(adapter3);
        spinnerhours.setOnItemSelectedListener(this);



        warningText = (TextView) findViewById(R.id.reservationWarningText);
        chooseDate = (Button) findViewById(R.id.ChooseDateButton);
        chosenDate = (TextView) findViewById(R.id.chosenDate);
        confirmReservation = (Button) findViewById((R.id.ConfirmReservation));

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


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void makeNewReservation() {
        FileClass fileClass = new FileClass(this);
        String pl = spinnerplace.getOnItemClickListener().toString();
        String sp = spinnersport.getOnItemClickListener().toString();
        String rm = spinnerroom.getOnItemClickListener().toString();
        String hs = spinnerhours.getOnItemClickListener().toString();
        String dt = chosenDate.getText().toString();
        String fn = User.activeUser.getFirstName();
        String ln = User.activeUser.getLastName();
        String em = User.activeUser.getEmail();
        if (reservation.reservationCheck(dt, hs, rm)) {
            warningText.setText("Room, date and hours already reserved, select another room/date/hours");
        } else {
            Reservation.reservations.add(new Reservation(rm, pl, dt, hs, sp, fn, ln, em));
            fileClass.FileWriteReservation();
        }

    }
}
    String r, String p, String d, String h, String s