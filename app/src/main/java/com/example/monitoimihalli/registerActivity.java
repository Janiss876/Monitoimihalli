package com.example.monitoimihalli;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class registerActivity extends AppCompatActivity {

    private ArrayList<User> user_array = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void newUser(String fN, String lN, String a, String e, String phone, String pass) {
    }
}
