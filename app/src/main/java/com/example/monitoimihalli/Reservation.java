package com.example.monitoimihalli;

import java.util.ArrayList;

public class Reservation {

    private String roomNumber;
    private String place;
    private String date;
    private String hours;
    private String sport;
    public static ArrayList<Reservation> reservations = new ArrayList<Reservation>();

    public Reservation(String r, String p, String d, String h, String s) {
        roomNumber = r;
        place = p;
        date = d;
        hours = h;
        sport = s;
    }

    public boolean reservationCheck(String dt, String hs) {
        for (int i = 0; i < reservations.size(); i++) {
            if (dt.equals(reservations.get(i).getDate()) && hs.equals(reservations.get(i).getHours())) {
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

    public void editReservation() {

    }

}
