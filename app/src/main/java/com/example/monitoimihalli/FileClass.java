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

    public void FileWriteUser() {
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


    public void fileReadUser() {
        try {
            fileName = "userfile.csv";
            InputStream stream = context.openFileInput(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] linea = line.split(",");
                User.user_array.add(new User(linea[0], linea[1], linea[2], linea[3], linea[4], linea[5]));
            }
            stream.close();
        } catch (IOException ex) {
                ex.printStackTrace();
        }
    }
    public void FileWriteReservation() {
        try {
            fileName = "reservationfile.csv";
            header = "room number,place,date,hours,sport,first name,last name,email\n";
            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_PRIVATE));
            osw.write(header);
            for (Reservation r : Reservation.reservations) {
                osw.write(r.getRoomNumber() + ",");
                osw.write(r.getPlace() + ",");
                osw.write(r.getDate() + ",");
                osw.write(r.getHours() + ",");
                osw.write(r.getSport() + ",");
                osw.write(r.getFirstName()+ ",");
                osw.write(r.getLastName() + ",");
                osw.write(r.getEmail() + "\n");
            }
            osw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void fileReadReservation() {
        try {
            fileName = "reservationfile.csv";
            InputStream stream = context.openFileInput(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] linea = line.split(",");
                Reservation.reservations.add(new Reservation(linea[0], linea[1], linea[2], linea[3], linea[4], linea[5], linea[6], linea[7]));
            }
            stream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

