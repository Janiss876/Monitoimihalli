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

public User(String fN, String lN, String a, String e, String phone, String pass) {
    firstName = fN;
    lastName = lN;
    userAddress = a;
    emailAddress = e;
    phoneNumber = phone;
    password = pass;
}


    public void login() {
        Scanner log = new Scanner(System.in);
        System.out.println("Enter email");
        emailAddress = log.nextLine();
        System.out.println("Enter password");
        password = log.nextLine();
    }
    public void register() {
        ArrayList userList = new ArrayList();
        Scanner reg = new Scanner(System.in);
        System.out.println("Firstname");
        firstName = reg.nextLine();
        System.out.println("Lastname");
        lastName = reg.nextLine();
        System.out.println("address");
        userAddress = reg.nextLine();
        System.out.println("Phonenumber");
        phoneNumber = reg.nextLine();
        System.out.println("Password");
        password = reg.nextLine();
    }
    public String getEmail() {
        return emailAddress;
    }
}

