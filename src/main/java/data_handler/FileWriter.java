package data_handler;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static coding.HuffmanEncoding.charCodeMap;

public class FileWriter {
    static DataOutputStream dos;
   private static String compressedFilesPath = "C:/Users/Hp/Desktop/Compressed Files/compressed.txt";
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
                System.out.println("File created successfully in C -> Users -> Hp -> Desktop -> Original Files.");

        } catch (IOException e) {
            System.err.println("Error in FileWriter.createOriginalTxtFile(): " + e.getMessage());
        }
    }

    public static void createCmpFile(String[] data, int diff) {
        String destinationPath = String.format(compressedFilesPath, FileReader.getStringData());
        try {
            int count = 1;
            File file = new File(compressedFilesPath);
            while (file.exists()){
                compressedFilesPath = String.format("C:/Users/Hp/Desktop/Compressed Files/compressed(%d).txt",count);
                file = new File(compressedFilesPath);
                count++;
            }
                dos = new DataOutputStream(new FileOutputStream(destinationPath));
                dos.writeBytes(String.valueOf(diff));
                dos.writeByte(' ');
                for (var item : charCodeMap.entrySet()) {
                    dos.writeByte(item.getKey());
                    for (int i = 0; i < item.getValue().length(); i++) {
                        dos.writeByte(item.getValue().charAt(i));
                    }
                    dos.writeByte(' ');
                }

                for (String s: data){
                    dos.writeBytes(s+' ');
                }
                System.out.println("File created successfully in C -> Users -> Hp -> Desktop -> Compressed Files.");
        } catch (IOException e) {
            System.err.println("Error in FileWriter.createCmpFile(): " + e.getMessage());
        }
    }
}
