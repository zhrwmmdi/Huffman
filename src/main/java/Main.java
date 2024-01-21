import data_handler.FileReader;

public class Main {
    public static void main(String[] args){
        FileReader reader = new FileReader();
        reader.readFile("text.txt");

        HuffmanCoding huffman = new HuffmanCoding();
        huffman.encode(FileReader.getStringData());
    }
}
