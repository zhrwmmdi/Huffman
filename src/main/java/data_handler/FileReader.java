package data_handler;

import java.io.File;
import java.util.Scanner;

 public class FileReader {
    private static String stringData = "";
    protected void readFile(String path){
        File file = new File(path);
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNext()){
                stringData = stringData.concat(reader.nextLine());
            }
        }catch (Exception ex){
            System.err.println("ERROR in readFile() method of data_handler.FileReader class: "+ex.getMessage());
        }
    }

    public static String getStringData(){
        return stringData;
    }
}
