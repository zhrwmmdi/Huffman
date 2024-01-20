import data_handler.CharacterMap;
import data_handler.FileReader;

import java.io.File;

public class Main {
    public static void main(String[] args){
        FileReader reader = new FileReader();
        reader.readFile("text.txt");

        CharacterMap huffman = new CharacterMap();
        huffman.fillMap(FileReader.getStringData());
    }
}
