package coding;

import data_handler.FileReader;
import data_handler.FileWriter;
import structure.Node;

import java.util.*;

public class HuffmanEncoding extends Huffman{
    private static StringBuilder codedText;
    public void encode(String data) {
        fillCharFreqMap(data);
        Node root = createHuffmanTree();
        charCodeMap = createCharCodes(root, "", charCodeMap);
        codedText = produceEncodedString();

        char[] chunks = convertCodeToAsciiChar(String.valueOf(codedText));
        int lastChunkLength = codedText.length() % 8;

        StringBuilder stringValue = new StringBuilder();
        for (char c : chunks){
            stringValue.append(c);
        }
        FileWriter.createCmpFile(String.valueOf(stringValue), lastChunkLength);
        System.out.println("Encoded text: " + codedText);
    }

    private  char[] convertCodeToAsciiChar(String binaryNumber){

        int length = binaryNumber.length();
        int numOfChunks = (int) Math.ceil((double) length / 8);
        char[] chunks = new char[numOfChunks];

        for (int i = 0; i < numOfChunks; i++) {
            int startIndex = i * 8;
            int endIndex = Math.min(startIndex + 8, length);
            StringBuilder x = new StringBuilder(binaryNumber.substring(startIndex, endIndex)); //each chunk with binary value
            if (x.length()<8){
                x.append("0".repeat(Math.max(0, (8 - x.length()))));
            }
            chunks[i] = (char)Integer.parseInt(x.toString(),2);
        }
        return chunks;
    }
    private StringBuilder produceEncodedString() {
        StringBuilder resultString = new StringBuilder();
        for (char c : FileReader.getStringData().toCharArray()) {
            resultString.append(charCodeMap.get(c));
        }
        return resultString;
    }

    public static Map<Character, Integer> getCharFrequencyMap() {
        return Huffman.charFreqMap;
    }
}

