package com.example.monitoimihalli;
import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileClass {
    ;
    private Context context;
    private File userFile;
    private String userFileName;

    public FileClass(Context con) {
        context = con.getApplicationContext();

    }

    public void FileWrite(String fN, String lN, String a, String e, String ph, String pa) {
            try {
                userFile = new File(context.getFilesDir() + "/userfile.csv");
                userFileName = "userfile.csv";
                OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput(userFileName, Context.MODE_APPEND));
                if (!userFile.exists() || userFile.length() == 0) {
                    String userHeader = "first Name,last name,address,email,phone number,password\n";
                    osw.write(userHeader);
                }
                osw.write(fN + ",");
                osw.write(lN + ",");
                osw.write(a + ",");
                osw.write(e + ",");
                osw.write(ph + ",");
                osw.write(pa + "\n");
                osw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    }
}
