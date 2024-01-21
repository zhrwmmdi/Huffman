import data_handler.FileReader;
public class Main {
    public static void main(String[] args){
        Browser browser  = new Browser();

        FileReader reader = new FileReader();
        reader.readFile(browser.getSelectedFilePath());

        HuffmanCoding huffman = new HuffmanCoding();
        huffman.encode(FileReader.getStringData());
    }
}
