package com.example.monitoimihalli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button registerButton;
    Button loginButton;
    EditText loginEmail;
    EditText loginPassword;
    TextView wrongText;
    MainActivity context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        FileClass file = new FileClass(context);
        file.fileReadUser();
        file.fileReadReservation();
        loginButton = (Button) findViewById(R.id.loginButton);
        registerButton = (Button) findViewById(R.id.registerButton);
        loginEmail = (EditText) findViewById(R.id.loginEmail);
        loginPassword = (EditText) findViewById(R.id.loginPassword);
        wrongText = (TextView) findViewById(R.id.wrongText);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openReservationActivity();
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterActivity();
            }
        });
    }
    public void openRegisterActivity() {
        startActivity(new Intent(MainActivity.this, RegisterActivity.class));
    }
    public void openReservationActivity() {
        User user = new User();
        if (user.loginCheck(loginEmail.getText().toString(), loginPassword.getText().toString())) {
            startActivity(new Intent(MainActivity.this, ReservationActivity.class));
            }
            else {
                wrongText.setText("Wrong email and/or password");
            }

    }
}
