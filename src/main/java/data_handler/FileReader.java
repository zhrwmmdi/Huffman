package data_handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class FileReader {
    private static String stringData = "";

    protected void readTxtFile(String path) {
        File file = new File(path);
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNext()) {
                stringData = stringData.concat(reader.nextLine());
            }
            stringData = stringData.replaceAll("\u0000", "");//????
        } catch (Exception ex) {
            System.err.println("ERROR in readTxtFile() method of data_handler.FileReader class: " + ex.getMessage());
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
