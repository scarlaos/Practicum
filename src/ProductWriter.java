import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductWriter {
    public static void main(String[] args) {
    JFileChooser chooser = new JFileChooser();
    File selectedFile;
    String rec = "";
    System.out.println("Please enter the path of the file you would like to read: ");
    try {
        File workingDirectory = new File(System.getProperty("user.dir"));


        chooser.setCurrentDirectory(workingDirectory);


        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            selectedFile = chooser.getSelectedFile();
            Path file = selectedFile.toPath();

            InputStream in =
                    new BufferedInputStream(Files.newInputStream(file, CREATE));
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(in));


            System.out.printf("%-6s %-12s %-25s %-8s%n","ID", "Product", "Description","Cost");
            System.out.println("=====================================================");

            while (reader.ready()) {
                rec = reader.readLine();
                // echo to screen
                String[] fields = rec.split(",");
                System.out.printf("%-6s %-12s %-25s %-8s%n", fields[0], fields[1], fields[2], fields[3]);
            }
            reader.close();
            System.out.println("\n\nData file read!");


        } else
        {
            System.out.println("No file selected!!! ... exiting.\nRun the program again and select a file.");
        }

    }
    catch (FileNotFoundException e)
    {
        System.out.println("File not found!!!");
        e.printStackTrace();
    }
    catch (IOException e)
    {
        e.printStackTrace();
    }
}
}
