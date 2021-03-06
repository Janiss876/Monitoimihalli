package com.example.monitoimihalli;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileClass {
    private Context context;
    private String fileName;
    private String header;

    public FileClass(Context con) {
        context = con.getApplicationContext();
    }

    //writes all users to usefile. This happens when new user is created or existing user updated
    public void fileWriteUser() {
            try {
                fileName = "userfile.csv";
                header = "first Name,last name,address,email,phone number,password\n";
                OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_PRIVATE));
                osw.write(header);
                for (User u : User.user_array) {
                    osw.write(u.getFirstName() + ",");
                    osw.write(u.getLastName() + ",");
                    osw.write(u.getAddress() + ",");
                    osw.write(u.getEmail() + ",");
                    osw.write(u.getPhoneNumber() + ",");
                    osw.write(u.getPassword() + "\n");
                }
                osw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    }

    //Reads users from userfile and adds them to user_array. This happens when you start the app.
    public void fileReadUser() {
        try {
            fileName = "userfile.csv";
            InputStream stream = context.openFileInput(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] linea = line.split(",");
                new User(linea[0], linea[1], linea[2], linea[3], linea[4], linea[5]);
            }
            stream.close();
        } catch (IOException ex) {
                ex.printStackTrace();
        }
    }
    //writes all reservations to reservationfile. This happens when new reservation is created or existing reservation updated/deleted
    public void fileWriteReservation() {
        try {
            fileName = "reservationfile.csv";
            header = "room number,place,date,hours,sport,first name,last name,email,description,max paricipants,participants\n";
            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_PRIVATE));
            osw.write(header);
            for (Reservation r : Reservation.reservations) {
                String ps = "";
                for (String s : r.participantsArray)
                    if (s.equals(r.participantsArray.get(r.participantsArray.size() - 1))) {
                        ps = ps + s + "\n";
                    } else {
                        ps = ps + s + "/";
                    }
                if (ps.equals("")) {
                    ps = "none\n";
                }
                osw.write(r.getRoomNumber() + ",");
                osw.write(r.getPlace() + ",");
                osw.write(r.getDate() + ",");
                osw.write(r.getHours() + ",");
                osw.write(r.getSport() + ",");
                osw.write(r.getFirstName()+ ",");
                osw.write(r.getLastName() + ",");
                osw.write(r.getEmail() + ",");
                osw.write(r.getDescription() + ",");
                osw.write(r.getMaxParticipants() + ",");
                osw.write(ps);
            }
            osw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    //Reads reservation from reservationfile and adds them to reservations arraylist. This happens when you start the app.
    public void fileReadReservation() {
        try {
            fileName = "reservationfile.csv";
            InputStream stream = context.openFileInput(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] linea = line.split(",");
                Reservation r = new Reservation(linea[0], linea[1], linea[2], linea[3], linea[4], linea[5], linea[6], linea[7], linea[8]);
                if (!linea[10].equals("none")) {
                    String[] partc = linea[10].split("/");
                    for (String s : partc) {
                        r.participantsArray.add(s);
                    }
                }
            }
            stream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}