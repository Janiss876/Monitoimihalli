package com.example.monitoimihalli;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private String roomNumber;
    private String placeName;
    private List<String> availableSports = new ArrayList<>();
    public static ArrayList<Room> roomArray = new ArrayList<>();

    public Room(String rn, String pn) {
        roomNumber = rn;
        placeName = pn;
        availableSports.add("Futsal");
        availableSports.add("Tennis");
        availableSports.add("Badminton");
        availableSports.add("Floorball");
        roomArray.add(this);
    }

    public Room() {
        availableSports.add("Futsal");
        availableSports.add("Tennis");
        availableSports.add("Badminton");
        availableSports.add("Floorball");
    }

    public List<String> getRoomOptions() {
        List<String> roomOptions = new ArrayList<String>();
        for (Room r : roomArray) {
            roomOptions.add(r.getRoomNumber());
        }
        return roomOptions;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public List<String> getAvailableSports() {
        return availableSports;
    }

}
