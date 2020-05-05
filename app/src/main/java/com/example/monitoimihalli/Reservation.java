package com.example.monitoimihalli;

import java.util.ArrayList;

public class Reservation {

    private String roomNumber;
    private String place;
    private String date;
    private String hours;
    private String sport;
    private String firstName;
    private String lastName;
    private String email;

    public static ArrayList<Reservation> reservations = new ArrayList<>();

    public Reservation(String r, String p, String d, String h, String s, String f, String l, String e) {
        roomNumber = r;
        place = p;
        date = d;
        hours = h;
        sport = s;
        firstName = f;
        lastName = l;
        email = e;
        reservations.add(this);
    }

    public Reservation() {

    }

    public boolean reservationCheck(String dt, String hs, String rm) {
        for (int i = 0; i < reservations.size(); i++) {
            if (dt.equals(reservations.get(i).getDate()) && hs.equals(reservations.get(i).getHours()) && rm.equals(reservations.get(i).getRoomNumber())) {
                return true;
            }
        }
        return false;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getPlace() {
        return place;
    }

    public String getDate() {
        return date;
    }

    public String getHours() {
        return hours;
    }

    public String getSport() {
        return sport;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void editReservation() {

    }

}
