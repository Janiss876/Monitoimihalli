package com.example.monitoimihalli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerButton = (Button) findViewById(registerButton);
    }

    public void toRegisterActivity(View v) {
        startActivity(new Intent(MainActivity.this, registerActivity.class));
    }
}
