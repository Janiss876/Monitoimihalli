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
    public static User activeUser;
    public static ArrayList<User> user_array = new ArrayList<User>();

    public User(String fN, String lN, String addr, String email, String phone, String pass) {
        firstName = fN;
        lastName = lN;
        userAddress = addr;
        emailAddress = email;
        phoneNumber = phone;
        password = pass;
    }

    public User() {

    }


    public String getEmail() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public boolean checkEmail(String em) {
        for (User u : user_array) {
            if (u.emailAddress.equals(em)) {
                return true;
            }
        }
        return false;
    }

    public boolean loginCheck(String em, String pw) {
        for (int i = 0; i < User.user_array.size(); i++) {
            if (em.equals(User.user_array.get(i).getEmail()) && pw.equals(User.user_array.get(i).getPassword())) {
                activeUser = User.user_array.get(i);
                return true;
            }
        }
        return false;
    }
}
