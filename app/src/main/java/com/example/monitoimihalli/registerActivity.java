package com.example.monitoimihalli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class registerActivity extends AppCompatActivity {

    Button registerButton;
    Button cancelButton;
    EditText lastNameText;
    EditText firstNameText;
    EditText userAddressText;
    EditText emailText;
    EditText phoneText;
    EditText passwordText;
    TextView existingEmail;

    private ArrayList<User> user_array = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        cancelButton = (Button) findViewById(R.id.cancelRegisterButton);
        registerButton = (Button) findViewById(R.id.confirmRegisterButton);
        lastNameText = (EditText) findViewById(R.id.lastNameText);
        firstNameText = (EditText) findViewById(R.id.firstNameText);
        userAddressText = (EditText) findViewById(R.id.addressText);
        emailText = (EditText) findViewById(R.id.emailText);
        phoneText = (EditText) findViewById(R.id.phoneText);
        passwordText = (EditText) findViewById(R.id.passwordText);
        existingEmail = (TextView) findViewById(R.id.usedEmailText);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newUser();
            }
        });
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

    public void newUser() {
        String fN = lastNameText.getText().toString();
        String lN = firstNameText.getText().toString();
        String a = userAddressText.getText().toString();
        String e = emailText.getText().toString();
        String phone = phoneText.getText().toString();
        String pass = passwordText.getText().toString();
        if (user_array.size() > 0) {
            for (int i = 0; i < user_array.size(); i++) {
                if (e.equals(user_array.get(i).getEmail())) {
                    existingEmail.setText("The email is already in use");
                    break;
                } else if (i == user_array.size() - 1) {
                    user_array.add(new User(fN, lN, a, e, phone, pass));
                    existingEmail.setText("Added2");
                    //openMainActivity();
                }
            }
        } else {
            user_array.add(new User(fN, lN, a, e, phone, pass));
            existingEmail.setText("Added");
            //openMainActivity();
        }
    }
}
