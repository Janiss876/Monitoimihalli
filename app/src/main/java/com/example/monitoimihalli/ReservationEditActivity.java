package com.example.monitoimihalli;

import androidx.appcompat.app.AppCompatActivity;

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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ReservationEditActivity extends AppCompatActivity {

    EditText descText;
    Button chooseDate;
    Button confirmReservationEdit;
    Button cancelButton;
    DatePickerDialog dtp;
    TextView chosenDate;
    Spinner spinnerplace;
    Spinner spinnersport;
    Spinner spinnerroom;
    Spinner spinnerhours;
    TextView warningText;
    ReservationEditActivity context = null;
    Reservation reservation = new Reservation();
    Place place = new Place();
    Room room = new Room();
    String placeName;
    String sport;
    String roomNumber;
    String hours;
    List<String> placeOptions = new ArrayList<String>();
    List<String> roomOptions = new ArrayList<String>();
    List<String> sportOptions = new ArrayList<String>();
    List<String> hourOptions = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_edit);
        context = ReservationEditActivity.this;
        cancelButton = (Button) findViewById(R.id.CancelEditButton);
        warningText = (TextView) findViewById(R.id.ConfirmEditButton);
        chooseDate = (Button) findViewById(R.id.ChooseDateButtonEdit);
        chosenDate = (TextView) findViewById(R.id.chosenDateEdit);
        chosenDate.addTextChangedListener(makeTextWatcher);
        confirmReservationEdit = (Button) findViewById(R.id.ConfirmEditButton);
        descText = (EditText) findViewById(R.id.descriptionTextEdit);

        getSpinnerOptions();

        spinnerplace = findViewById(R.id.spinnerplaceEdit);
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


        spinnerroom = findViewById(R.id.spinnerroomEdit);
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


        spinnersport = findViewById(R.id.spinnersportEdit);
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

        spinnerhours = findViewById(R.id.spinnerhoursEdit);
        ArrayAdapter<CharSequence> adapter3 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, hourOptions);
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

        startingOptions();

        confirmReservationEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editReservation();
            }
        });

        chooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                dtp = new DatePickerDialog(ReservationEditActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        chosenDate.setText(day + "-" + (month + 1) + "-" + year);
                    }
                }, year, month, day);
                dtp.show();

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMyReservationsActivity();
            }
        });

    }

    public void startingOptions() {
        int placeNumb = -1;
        int roomNumb = -1;
        int sportNumb = -1;
        int hoursNumb = -1;
        for (String s : placeOptions) {
            placeNumb = placeNumb + 1;
            if (s.equals(Reservation.editingReservation.getPlace())) {
                spinnerplace.setSelection(placeNumb);
            }
        }
        for (String s : roomOptions) {
            roomNumb = roomNumb + 1;
            if (s.equals(Reservation.editingReservation.getRoomNumber())) {
                spinnerroom.setSelection(roomNumb);
            }
        }
        for (String s : sportOptions) {
            sportNumb = sportNumb + 1;
            if (s.equals(Reservation.editingReservation.getSport())) {
                spinnersport.setSelection(sportNumb);
            }
        }
        for (String s : hourOptions) {
            hoursNumb = hoursNumb + 1;
            if (s.equals(Reservation.editingReservation.getHours())) {
                spinnerhours.setSelection(hoursNumb);
            }
        }
        descText.setText(Reservation.editingReservation.getDescription());
        chosenDate.setText(Reservation.editingReservation.getDate());
    }

    public void editReservation() {
        FileClass fileClass = new FileClass(this);
        String pl = placeName;
        String sp = sport;
        String rm = roomNumber;
        String hs = hours;
        String dt = chosenDate.getText().toString();
        String dc = descText.getText().toString();
        if (reservation.reservationCheck(dt, hs, rm)) {
            warningText.setText("Room, date and hours already reserved, select another room/date/hours");
        } else {
            reservation.editReservation(rm, pl, dt, hs, sp, dc);
            fileClass.FileWriteReservation();
            openReservationsActivity();
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
        hourOptions.add("10-12");
        hourOptions.add("12-14");
        hourOptions.add("14-16");
        hourOptions.add("16-18");
        hourOptions.add("18-20");
        hourOptions.add("20-22");
    }

    public void openMyReservationsActivity() {
        startActivity(new Intent(ReservationEditActivity.this, MyReservationsActivity.class));
    }

    public void openReservationsActivity() {
        startActivity(new Intent(ReservationEditActivity.this, WelcomeActivity.class));
    }

    private TextWatcher makeTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            confirmReservationEdit.setEnabled(!chosenDate.getText().toString().equals("Chosen date"));
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}

