package com.example.monitoimihalli;

import java.util.ArrayList;
import java.util.List;

public class Place {

    private String placeAddress;
    private String name;
    private int numberOfRooms;
    public static ArrayList<Place> placeArray = new ArrayList<>();

    public Place(String addr, String nm, int nor) {
        placeAddress = addr;
        name = nm;
        numberOfRooms = nor;
        placeArray.add(this);
    }

    public Place() {

    }

    //returns placeoptions for a spinner.
    public List<String> getPlaceOptions() {
        List<String> placeOptions = new ArrayList<>();
        for (Place p : placeArray) {
            placeOptions.add(p.getName());
        }
        return placeOptions;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }
}


