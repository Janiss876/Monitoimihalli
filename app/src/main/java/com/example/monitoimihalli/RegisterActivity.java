package com.example.monitoimihalli;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    Button registerButton;
    Button cancelButton;
    EditText lastNameText;
    EditText firstNameText;
    EditText userAddressText;
    EditText emailText;
    EditText phoneText;
    EditText passwordText;
    TextView existingEmail;
    User user = new User();
    RegisterActivity context = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        context = RegisterActivity.this;
        cancelButton = (Button) findViewById(R.id.cancelRegisterButton);
        registerButton = (Button) findViewById(R.id.confirmRegisterButton);
        lastNameText = (EditText) findViewById(R.id.lastNameText);
        firstNameText = (EditText) findViewById(R.id.firstNameText);
        userAddressText = (EditText) findViewById(R.id.addressText);
        emailText = (EditText) findViewById(R.id.emailText);
        phoneText = (EditText) findViewById(R.id.phoneText);
        passwordText = (EditText) findViewById(R.id.passwordText);
        existingEmail = (TextView) findViewById(R.id.warninglText);

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
        emailText.addTextChangedListener(registerTextWatcher);
        lastNameText.addTextChangedListener(registerTextWatcher);
        firstNameText.addTextChangedListener(registerTextWatcher);
        userAddressText.addTextChangedListener(registerTextWatcher);
        phoneText.addTextChangedListener(registerTextWatcher);
        passwordText.addTextChangedListener(registerTextWatcher);

    }

    public void openMainActivity() {
        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
    }

    public void newUser() {
        FileClass fileClass = new FileClass(this);
        String fN = firstNameText.getText().toString();
        String lN = lastNameText.getText().toString();
        String a = userAddressText.getText().toString();
        String e = emailText.getText().toString();
        String phone = phoneText.getText().toString();
        String pass = passwordText.getText().toString();
        if (user.checkEmail(e)) {
            existingEmail.setText("The email is already in use");
        } else {
            new User(fN, lN, a, e, phone, pass);
            fileClass.FileWriteUser();
            openMainActivity();
        }
    }
    private TextWatcher registerTextWatcher = new TextWatcher() {
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
            registerButton.setEnabled(!f.isEmpty() && !l.isEmpty() && !f.isEmpty() && !a.isEmpty() && !e.isEmpty() && !ph.isEmpty() && !pa.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
