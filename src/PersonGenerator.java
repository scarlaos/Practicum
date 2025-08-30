import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class PersonGenerator {
    public static void main(String[] args) {
        ArrayList<String> folks = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.toString(), "personData.txt");

        boolean done = false;

        String personRec = "";
        String ID = "";
        String firstName = "";
        String lastName = "";
        String title="";
        int YOB = 0;

        do{
            ID=SafeInput.getNonZeroLenString(in, "Enter the ID [6 digits]");
            firstName=SafeInput.getNonZeroLenString(in, "Enter the first name");
            lastName=SafeInput.getNonZeroLenString(in, "Enter the last name");
            title=SafeInput.getNonZeroLenString(in, "Enter the title");
            YOB=SafeInput.getRangedInt(in,"Enter the year of Birth", 1000, 9999);

            personRec=ID + ", " + firstName + ", " + lastName + ", " + title + ", " + YOB;
            folks.add(personRec);

            done= SafeInput.getYNConfirm(in, "Are you done? ");

        }while(!done);

        for(String p: folks)
            System.out.println(p);

        try
        {
            // Typical java pattern of inherited classes
            // we wrap a BufferedWriter around a lower level BufferedOutputStream
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            // Finally can write the file LOL!

            for(String rec : folks)
            {
                writer.write(rec, 0, rec.length());  // stupid syntax for write rec
                // 0 is where to start (1st char) the write
                // rec. length() is how many chars to write (all)
                writer.newLine();  // adds the new line

            }
            writer.close(); // must close the file to seal it and flush buffer
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}