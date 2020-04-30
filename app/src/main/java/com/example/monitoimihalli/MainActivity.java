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
    public static User activeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        startActivity(new Intent(MainActivity.this, registerActivity.class));
    }
    public void openReservationActivity() {
        for (int i = 0; i < User.user_array.size(); i++) {
            if (loginEmail.getText().toString().equals(User.user_array.get(i).getEmail()) && loginPassword.getText().toString().equals(User.user_array.get(i).getPassword())) {
                activeUser = User.user_array.get(i);
                startActivity(new Intent(MainActivity.this, reservationActivity.class));
                break;
            }
            else if (i == User.user_array.size() - 1) {
                wrongText.setText("Wrong email and/or password");
            }

        }
    }
}
