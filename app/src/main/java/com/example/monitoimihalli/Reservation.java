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
    private int maxParticipants = 20;
    private String description;
    ArrayList<String> participantsArray = new ArrayList<String>();

    public static Reservation editingReservation;

    public static ArrayList<Reservation> reservations = new ArrayList<>();

    public Reservation(String r, String p, String dt, String h, String s, String f, String l, String e, String dc) {
        roomNumber = r;
        place = p;
        date = dt;
        hours = h;
        sport = s;
        firstName = f;
        lastName = l;
        email = e;
        description = dc;
        reservations.add(this);
    }

    public Reservation() {

    }
    //checks if reservation taken
    public boolean reservationCheck(String dt, String hs, String rm) {
        for (int i = 0; i < reservations.size(); i++) {
            if (dt.equals(reservations.get(i).getDate()) && hs.equals(reservations.get(i).getHours()) && rm.equals(reservations.get(i).getRoomNumber())) {
                return true;
            }
        }
        return false;
    }

    //time/place/room check for editing existing reservation
    public boolean reservationCheckEdit(String dt, String hs, String rm) {
        if (dt.equals(editingReservation.getDate()) && hs.equals(editingReservation.getHours()) && rm.equals(editingReservation.getRoomNumber())) {
            return false;
        }
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

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public String getDescription() {
        return description;
    }

    public void editReservation(String r, String p, String dt, String h, String s, String dc) {
        editingReservation.roomNumber = r;
        editingReservation.place = p;
        editingReservation.date = dt;
        editingReservation.hours = h;
        editingReservation.sport = s;
        editingReservation.description = dc;
        editingReservation = null;
        return;
    }

    public void deleteReservation() {
        Reservation remRes = null;
        for (Reservation r : reservations) {
            if (r.getRoomNumber().equals(editingReservation.getRoomNumber()) && r.getPlace().equals(editingReservation.getPlace()) && r.getHours().equals(editingReservation.getHours())) {
                remRes = r;
                break;
            }
        }
        reservations.remove(remRes); //removes a reservation from the list
    }

    //activeuser can join to reservation as a participant ; his name adds to participant list to the certain reservation
    public void joinReservation(Reservation r) {
        r.participantsArray.add(User.activeUser.getFirstName() + " " + User.activeUser.getLastName());
    }
}
