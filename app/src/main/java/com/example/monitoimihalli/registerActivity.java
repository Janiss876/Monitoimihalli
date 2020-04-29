package com.example.monitoimihalli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class registerActivity extends AppCompatActivity {

    Button registerButton;
    Button cancelButton;
    TextView lastNameText;
    TextView firstNameText;
    TextView userAddressText;
    TextView emailText;
    TextView phoneText;
    TextView passwordText;

    private ArrayList<User> user_array = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        cancelButton = (Button) findViewById(R.id.cancelRegisterButton);
        registerButton = (Button) findViewById(R.id.registerButton);
        lastNameText = (TextView) findViewById(R.id.lastNameText);
        firstNameText = (TextView) findViewById(R.id.firstNameText);
        userAddressText = (TextView) findViewById(R.id.addressText);
        emailText = (TextView) findViewById(R.id.emailText);
        phoneText = (TextView) findViewById(R.id.phoneText);
        passwordText = (TextView) findViewById(R.id.passwordText);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });
    }

    public void openMainActivity() {
        startActivity(new Intent(registerActivity.this, MainActivity.class));
    }

    public void newUser(String fN, String lN, String a, String e, String phone, String pass) {
        fN = "";
        lN = "";
        a = "";
        e = "";
        phone = "";
        pass = "";
        user_array.add(new User(fN, lN, a, e, phone, pass));
    }
}
