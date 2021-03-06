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
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class JoinReservationActivity extends AppCompatActivity {
    JoinReservationActivity context = null;
    Button chooseDate2;
    Button checkButton;
    Button selectJoinButton;
    Spinner spinnerPickPlace;
    Spinner spinnerPickRoom;
    Spinner spinnerJoin;
    DatePickerDialog dtp2;
    TextView reservationsText;
    TextView chosenDate2;
    String pickedRoom;
    String pickedPlace;
    String pickedTime;
    Place place = new Place();
    Room room = new Room();
    Reservation reservation = new Reservation();
    List<String> placeOptions;
    List<String> roomOptions;
    List<String> joinableList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_reservation);
        context = JoinReservationActivity.this;
        chooseDate2 = (Button) findViewById(R.id.chooseDate2);
        checkButton = (Button) findViewById(R.id.checkButton);
        selectJoinButton = (Button) findViewById(R.id.JoinReservation);
        chosenDate2 = (TextView) findViewById(R.id.chosenDate2);
        chosenDate2.addTextChangedListener(checkTextWatcher);
        reservationsText = (TextView) findViewById(R.id.AllReservationsText);
        reservationsText.addTextChangedListener(joinTextWatcher);

        getSpinnerOptions();

        spinnerPickPlace = findViewById(R.id.spinnerPlacePick);
        ArrayAdapter<CharSequence> adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, placeOptions);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPickPlace.setAdapter(adapter1);
        spinnerPickPlace.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pickedPlace = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerPickRoom = findViewById(R.id.spinner_pickRoom2);
        ArrayAdapter<CharSequence> adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, roomOptions);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPickRoom.setAdapter(adapter2);
        spinnerPickRoom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pickedRoom = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerJoin = findViewById(R.id.spinnerJoin);
        final ArrayAdapter<CharSequence> adapter3 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, joinableList);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerJoin.setAdapter(adapter3);
        spinnerJoin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pickedTime = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        chooseDate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                dtp2 = new DatePickerDialog(JoinReservationActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        chosenDate2.setText(year + "-" + (month + 1) + "-" + day);
                    }
                }, year, month, day);
                dtp2.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                dtp2.getDatePicker().setMaxDate(System.currentTimeMillis() + 2 * 604800000);
                dtp2.show();

            }
        });

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSelected();
                adapter3.notifyDataSetChanged();
            }
        });

        selectJoinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                joinTheReservation();
            }
        });
    }

    public void getSpinnerOptions() {
        if (Place.placeArray.size() == 0) {
            new Place("Skinnarilankatu 100", "Sports Hall", 3);
        }
        placeOptions = place.getPlaceOptions();
        if (Room.roomArray.size() == 0) {
            for (int i = 1; i <= Place.placeArray.get(0).getNumberOfRooms(); i++) {
                new Room(String.valueOf(i), Place.placeArray.get(0).getName());
            }
        }
        roomOptions = room.getRoomOptions();
    }

    /** Shows all 6 possible reservation times of the date, place and room.
     If there are reservations, info is shown about them, otherwise the row says Free.
    This method also gets the options for the spinner that you use to select which reservation
     you want to join. If all possible reservation times are free, there are no options**/
    public void checkSelected() {
        joinableList.clear();
        reservationsText.setText("");
        ArrayList<String> daysTexts = new ArrayList<String>();
        daysTexts.add("Hours 10-12: Free ");
        daysTexts.add("Hours 12-14: Free ");
        daysTexts.add("Hours 14-16: Free ");
        daysTexts.add("Hours 16-18: Free ");
        daysTexts.add("Hours 18-20: Free ");
        daysTexts.add("Hours 20-22: Free ");
        String dt = chosenDate2.getText().toString();
        for (Reservation r : Reservation.reservations) {
            if (dt.equals(r.getDate()) && pickedPlace.equals(r.getPlace()) && pickedRoom.equals(r.getRoomNumber())) {
                String info = "Hours " + r.getHours() + ": Reserver: " + r.getFirstName() + " " + r.getLastName() + ", Sport: " + r.getSport()
                            +", Description: "+ r.getDescription() + ", Max participants: " + r.getMaxParticipants();
                if (r.getHours().equals("10-12")) {
                    daysTexts.set(0, info);
                } else if (r.getHours().equals("12-14")) {
                    daysTexts.set(1, info);
                } else if (r.getHours().equals("14-16")) {
                    daysTexts.set(2, info);
                } else if (r.getHours().equals("16-18")) {
                    daysTexts.set(3, info);
                } else if (r.getHours().equals("18-20")) {
                    daysTexts.set(4, info);
                } else if (r.getHours().equals("20-22")) {
                    daysTexts.set(5, info);
                }
            }
        }
        for (String s : daysTexts) {
            if (!s.substring(13, 17).equals("Free")) {
                joinableList.add(s.substring(6, 11));
            }
            reservationsText.append(s + "\n-------------------------------------\n");
        }
    }

    //Adds active user's name to participants of a reservation based on their choice
    public void joinTheReservation() {
        FileClass fileClass = new FileClass(this);
        Reservation joiningReservation = null;
        for (Reservation r : Reservation.reservations) {
            if (r.getHours().equals(pickedTime) && r.getPlace().equals(pickedPlace) && r.getRoomNumber().equals(pickedRoom)) {
                joiningReservation = r;
            }
        }
        if (joiningReservation.getMaxParticipants() > joiningReservation.participantsArray.size()) {
            reservation.joinReservation(joiningReservation);
            fileClass.fileWriteReservation();
            startActivity(new Intent(JoinReservationActivity.this, WelcomeActivity.class));
        } else {
            reservationsText.setText("MAXIMUM NUMBER OF PARTICIPANTS ALREADY JOINED");
        }


    }

    //The TextWatchers make sure that user can't press a button when he hasn't selected everything that they need
    private TextWatcher checkTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            checkButton.setEnabled(!chosenDate2.getText().toString().equals("Chosen date"));
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private TextWatcher joinTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            selectJoinButton.setEnabled(joinableList.size() != 0);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}


