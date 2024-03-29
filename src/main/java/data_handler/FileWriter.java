package data_handler;

import coding.HuffmanEncoding;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class FileWriter {
    static DataOutputStream dos;
    private static String compressedFilesPath = "C:/Users/Hp/Desktop/Compressed Files/compressed.cmp";
    private static String originalFilesPath = "C:/Users/Hp/Desktop/Original Files/original.txt";

    public static void createOriginalTxtFile(String data) {
        try {
            int count = 1;
            File file = new File(originalFilesPath);
            while (file.exists()){
                originalFilesPath = String.format("C:/Users/Hp/Desktop/Original Files/original(%d).txt",count);
                file = new File(originalFilesPath);
                count++;
            }
                dos = new DataOutputStream(new FileOutputStream(originalFilesPath));
                dos.writeBytes(data);
                System.out.println("\033[0;32m"+"File created successfully in C -> Users -> Hp -> Desktop -> Original Files."+"\033[0m");

        } catch (IOException e) {
            System.err.println("Error in FileWriter.createOriginalTxtFile(): " + e.getMessage());
        }
    }

    public static void createCmpFile(String data, int diff) {
        try {
            int count = 1;
            File file = new File(compressedFilesPath);
            while (file.exists()){
                compressedFilesPath = String.format("C:/Users/Hp/Desktop/Compressed Files/compressed(%d).cmp",count);
                file = new File(compressedFilesPath);
                count++;
            }
            dos = new DataOutputStream(new FileOutputStream(compressedFilesPath));
            dos.writeBytes(String.valueOf(diff));
            dos.writeByte(' ');

            for (var item : HuffmanEncoding.getCharFrequencyMap().entrySet()) {
                dos.writeByte(item.getKey());
                dos.writeBytes(String.valueOf(item.getValue()));
                dos.writeByte(' ');
            }
            dos.writeBytes(data);

            System.out.println("\033[0;32m"+"File created successfully in C -> Users -> Hp -> Desktop -> Compressed Files."+"\033[0m");
        } catch (IOException e) {
            System.err.println("Error in FileWriter.createCmpFile(): " + e.getMessage());
        }
    }
}
