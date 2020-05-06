package com.example.monitoimihalli;

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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;
import java.util.List;

public class MakeActivity extends AppCompatActivity {

    EditText descText;
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
    Place place = new Place();
    Room room = new Room();
    String placeName;
    String sport;
    String roomNumber;
    String hours;
    List<String> placeOptions;
    List<String> roomOptions;
    List<String> sportOptions;

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
        descText = (EditText) findViewById(R.id.descriptionText);

        getSpinnerOptions();

        spinnerplace = findViewById(R.id.spinnerplace);
        ArrayAdapter<String> adapter = new  ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, placeOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerplace.setAdapter(adapter);
        spinnerplace.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                placeName = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinnerroom = findViewById(R.id.spinnerroom);
        ArrayAdapter<CharSequence> adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, roomOptions);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerroom.setAdapter(adapter2);
        spinnerroom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                roomNumber = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinnersport = findViewById(R.id.spinnersport);
        ArrayAdapter<CharSequence> adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sportOptions);
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

        spinnerhours = findViewById(R.id.spinnerhours);
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

    }

    public void makeNewReservation() {
        FileClass fileClass = new FileClass(this);
        String pl = placeName;
        String sp = sport;
        String rm = roomNumber;
        String hs = hours;
        String dt = chosenDate.getText().toString();
        String fn = User.activeUser.getFirstName();
        String ln = User.activeUser.getLastName();
        String em = User.activeUser.getEmail();
        String dc = descText.getText().toString();
        if (reservation.reservationCheck(dt, hs, rm)) {
            warningText.setText("Room, date and hours already reserved, select another room/date/hours");
        } else {
            Reservation r = new Reservation(rm, pl, dt, hs, sp, fn, ln, em, dc);
            r.addParticipant(User.activeUser);
            fileClass.FileWriteReservation();
            openReservationActivity();
        }
    }

    public void getSpinnerOptions() {
        if (Place.placeArray.size() == 0) {
            new Place("Skinnarilankatu 100", "Sports Hall", 3);
        }
        placeOptions = place.getPlaceOptions();
        if (Room.roomArray.size() ==0) {
            for (int i = 1; i <= Place.placeArray.get(0).getNumberOfRooms(); i++) {
                new Room(String.valueOf(i), Place.placeArray.get(0).getName());
            }
        }
        roomOptions = room.getRoomOptions();
        sportOptions = room.getAvailableSports();
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
            confirmReservation.setEnabled(!chosenDate.getText().toString().equals("Chosen date"));
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}