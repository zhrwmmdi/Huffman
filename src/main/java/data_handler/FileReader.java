package data_handler;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileReader {
    private static String stringData = "";

    protected void readFile(String path) {
        File file = new File(path);
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNext()) {
                stringData = stringData.concat(reader.nextLine());
                System.out.println(stringData);
            }
            stringData = stringData.replaceAll("\u0000", "");//????

        } catch (Exception ex) {
            System.err.println("ERROR in readFile() method of data_handler.FileReader class: " + ex.getMessage());
        }
    }
    protected void readCmpFile(String path){
        try {
            // Read the bytes from the file
            byte[] byteContent = Files.readAllBytes(Paths.get(path));

            // Convert the byte content to a string using UTF-8 encoding
            String stringContent = new String(byteContent, StandardCharsets.UTF_8);

            // Now you have the string value of the content
            stringData = stringContent;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getStringData() {
        return stringData;
    }
}
