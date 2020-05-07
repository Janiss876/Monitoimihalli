package com.example.monitoimihalli;

import java.util.ArrayList;

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
        user_array.add(this);
    }

    public User() {

    }


    public String getEmail() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return userAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    //Checks if one of the existing users already has the same email
    public boolean checkEmail(String em) {
        for (User u : user_array) {
            if (u.emailAddress.equals(em)) {
                return true;
            }
        }
        return false;
    }
    //checks if email matches with password
    public boolean loginCheck(String em, String pw) {
        for (int i = 0; i < user_array.size(); i++) {
            if (em.equals(user_array.get(i).getEmail()) && pw.equals(user_array.get(i).getPassword())) {
                activeUser = user_array.get(i);
                return true;
            }
        }
        return false;
    }
    //changes user details from AccountDetailsActivity
    public void detailChange(String fN, String lN, String addr, String email, String phone, String pass) {
        activeUser.firstName = fN;
        activeUser.lastName = lN;
        activeUser.userAddress = addr;
        activeUser.emailAddress = email;
        activeUser.phoneNumber = phone;
        activeUser.password = pass;
    }
}
