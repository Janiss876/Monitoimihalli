package com.example.monitoimihalli;

import java.util.ArrayList;
import java.util.Scanner;

public class User {

    private String firstName;
    private String lastName;
    private String userAddress;
    private String emailAddress;
    private String phoneNumber;
    private String password;
    public static ArrayList<User> user_array = new ArrayList<User>();

public User(String fN, String lN, String addr, String email, String phone, String pass) {
    firstName = fN;
    lastName = lN;
    userAddress = addr;
    emailAddress = email;
    phoneNumber = phone;
    password = pass;
}



    public String getEmail() {
        return emailAddress;
    }
    public String getPassword() {
    return password;
    }
}

