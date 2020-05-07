package com.example.monitoimihalli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AccountDetailsActivity extends AppCompatActivity {

    Button confirmButton;
    Button cancelButton;
    EditText lastNameText;
    EditText firstNameText;
    EditText userAddressText;
    EditText emailText;
    EditText phoneText;
    EditText passwordText;
    TextView warningText;
    User user = new User();
    AccountDetailsActivity context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);
        context = AccountDetailsActivity.this;
        cancelButton = (Button) findViewById(R.id.cancelDetailsButton);
        confirmButton = (Button) findViewById(R.id.confirmDetailsButton);
        lastNameText = (EditText) findViewById(R.id.detailsLastNameText);
        firstNameText = (EditText) findViewById(R.id.detailsFirstNameText);
        userAddressText = (EditText) findViewById(R.id.detailsAddressText);
        emailText = (EditText) findViewById(R.id.detailsEmailText);
        phoneText = (EditText) findViewById(R.id.detailsPhoneText);
        passwordText = (EditText) findViewById(R.id.detailsPasswordText);
        warningText = (TextView) findViewById(R.id.warningText);

        firstNameText.setText(User.activeUser.getFirstName());
        lastNameText.setText(User.activeUser.getLastName());
        userAddressText.setText(User.activeUser.getAddress());
        emailText.setText(User.activeUser.getEmail());
        phoneText.setText(User.activeUser.getPhoneNumber());
        passwordText.setText(User.activeUser.getPassword());

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editUser();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openReservationActivity();
            }
        });
        emailText.addTextChangedListener(detailsTextWatcher);
        lastNameText.addTextChangedListener(detailsTextWatcher);
        firstNameText.addTextChangedListener(detailsTextWatcher);
        userAddressText.addTextChangedListener(detailsTextWatcher);
        phoneText.addTextChangedListener(detailsTextWatcher);
        passwordText.addTextChangedListener(detailsTextWatcher);

    }

    public void openReservationActivity() {
        startActivity(new Intent(AccountDetailsActivity.this, WelcomeActivity.class));
    }

    // Edits user details based on user's wishes. Updates user_array and userfile accordingly
    public void editUser() {
        FileClass fileClass = new FileClass(this);
        String fN = firstNameText.getText().toString();
        String lN = lastNameText.getText().toString();
        String a = userAddressText.getText().toString();
        String e = emailText.getText().toString();
        String phone = phoneText.getText().toString();
        String pass = passwordText.getText().toString();
        if (user.checkEmail(e)) {
            warningText.setText("The email is already in use");
        } else {
            user.detailChange(fN, lN, a, e, phone, pass);
            fileClass.fileWriteUser();
            openReservationActivity();
        }
    }

    //Makes sure you can only press edit if all fields are not empty
    private TextWatcher detailsTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String f = firstNameText.getText().toString();
            String l = lastNameText.getText().toString();
            String a = userAddressText.getText().toString();
            String e = emailText.getText().toString();
            String ph = phoneText.getText().toString();
            String pa = passwordText.getText().toString();
            confirmButton.setEnabled(!f.isEmpty() && !l.isEmpty() && !f.isEmpty() && !a.isEmpty() && !e.isEmpty() && !ph.isEmpty() && !pa.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}

