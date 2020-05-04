package com.example.monitoimihalli;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

public class makeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button chooseDate;
    DatePickerDialog dtp;
    TextView chosenDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make);

        Spinner spinnerplace = findViewById(R.id.spinnerplace);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.place, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerplace.setAdapter(adapter);
        spinnerplace.setOnItemSelectedListener(this);
        spinnerplace.getAdapter().toString();

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




        chooseDate = (Button) findViewById(R.id.ChooseDateButton);
        chosenDate = (TextView) findViewById(R.id.chosenDate);


        chooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);



                dtp = new DatePickerDialog(makeActivity.this, new DatePickerDialog.OnDateSetListener() {
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
}
