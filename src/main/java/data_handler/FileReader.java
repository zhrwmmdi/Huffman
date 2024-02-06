package data_handler;

import java.io.File;
import java.io.FileInputStream;
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

                StringBuilder stringBuilder = new StringBuilder();

                try (FileInputStream inputStream = new FileInputStream(path)) {
                    int byteValue;
                    while ((byteValue = inputStream.read()) != -1) {
                        char character = (char) byteValue;
                        stringBuilder.append(character);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String originalString = stringBuilder.toString();
                stringData = originalString;
            }



    public static String getStringData() {
        return stringData;
    }
}
