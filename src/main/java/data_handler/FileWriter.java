package data_handler;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static coding.HuffmanEncoding.charCodeMap;

public class FileWriter {
    static DataOutputStream dos;
    private final static String compressedFilesPath = "C:/Users/Hp/Desktop/Compressed Files/%s.cmp";
    private final static String originalFilesPath = "C:/Users/Hp/Desktop/Original Files/original.txt";

    public static void createOriginalTxtFile(String data) {
        try {
            dos = new DataOutputStream(new FileOutputStream(originalFilesPath));
            dos.writeBytes(data);
            System.out.println("File created successfully in C -> Users -> Hp -> Desktop -> Original Files.");
        } catch (IOException e) {
            System.err.println("Error in FileWriter.createOriginalTxtFile(): " + e.getMessage());
        }
    }

    public static void createCmpFile(String data) {
        String destinationPath = String.format(compressedFilesPath, FileReader.getStringData());
        try {
            dos = new DataOutputStream(new FileOutputStream(destinationPath));
            dos.writeChars(data);
            for (var item : charCodeMap.entrySet()) {
                dos.writeChars(" "+item.getKey() + item.getValue());
            }
            System.out.println("File created successfully in C -> Users -> Hp -> Desktop -> Compressed Files.");
        } catch (IOException e) {
            System.err.println("Error in FileWriter.createCmpFile(): " + e.getMessage());
        }
    }
}
