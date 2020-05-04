package com.example.monitoimihalli;
import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileClass {
    private Context context;
    private String fileName;

    public FileClass(Context con) {
        context = con.getApplicationContext();
    }

    public void FileWrite() {
            try {
                fileName = "userfile.csv";
                String userHeader = "first Name,last name,address,email,phone number,password\n";
                OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_PRIVATE));
                osw.write(userHeader);
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


    public void fileRead() {
        try {
            fileName = "userfile.csv";
            InputStream stream = context.openFileInput(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            br.readLine();
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] linea = line.split(",");
                User.user_array.add(new User(linea[0], linea[1], linea[2], linea[3], linea[4], linea[5]));
            }
            stream.close();
        } catch (IOException ex) {
                ex.printStackTrace();
        }
    }
}

