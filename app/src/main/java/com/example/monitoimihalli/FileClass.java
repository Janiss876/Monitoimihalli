package com.example.monitoimihalli;
import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileClass {
    private File userFile;
    private String userHeader;
    private Context context;

    public FileClass(Context con) {
        context = con.getApplicationContext();
        userFile = new File("userfile.csv");
        userHeader = "first Name,last name,address,email,phone number,password\n";
    }

    public void FileWrite(String fN, String lN, String a, String e, String ph, String pa) {
            try {
                OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput(String.valueOf(userFile), Context.MODE_APPEND));
                if (!userFile.exists() || userFile.length() == 0) {
                    osw.write(userHeader);
                }
                osw.write(fN + ",");
                osw.write(lN + ",");
                osw.write(a + ",");
                osw.write(e + ",");
                osw.write(ph + ",");
                osw.write(pa + "\n");
                osw.close();
                System.out.print(userFile.length());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    }
}
