package coding;

import data_handler.FileWriter;
import structure.Node;

import java.util.*;

public class HuffmanDecoding extends Huffman {
    private int diff;
    private final StringBuilder binaryResult  = new StringBuilder();

    public void decode(String data) {
        String[] split = data.split("[ ]");
        diff  = Integer.parseInt(split[0]);
        String codedData = split[split.length-1];

        //fill charFreqMap according to the beginning of the compressed file
        fillCharFreqMap(split);

        //create the tree according to the charFreq map
        Node root = createHuffmanTree();

        //get the codes according to huffman tree root
        charCodeMap = createCharCodes(root,"",charCodeMap);

        //find out the original data by help of charCodeMap
        String result = String.valueOf(processDecoding(codedData));

        System.out.println( "\033[0;1m"+"Decoded text: " + result+"\033[0m");
        //System.out.println("Binary Result:"+binaryResult);

        //write original data on txt file
        FileWriter.createOriginalTxtFile(result);
    }
    private String processDecoding(String codedText) {
        int size = codedText.length();
        String[] binary8bitChucks  = new String[size];
        for (int i = 0; i < size-1; i++) {
            int asciiDec = codedText.charAt(i); //The ascii code of each char in decimal format

            String asciiBin = Integer.toBinaryString(asciiDec);//The ascii code of each char in binary format

            //put zero before at the beginning to reach length 8
            StringBuilder paddedString = new StringBuilder(asciiBin);
            while (paddedString.length() < 8) {
                paddedString.insert(0, '0');
            }

            //store each 8 bit binary num in an array
            binary8bitChucks[i] = String.valueOf(paddedString);
        }

        //the last char that is the last chunk
        int lastAsciiDec = codedText.charAt(size-1); //Decimal ascii code of last char (that was the last chunk)
        String lastAsciiBin = Integer.toBinaryString(lastAsciiDec);//Binary ascii code of last char (that was the last chunk)

        //put zero before at the beginning to reach length 8
        StringBuilder paddedString = new StringBuilder(lastAsciiBin);
        while (paddedString.length() < 8) {
            paddedString.insert(0, '0');
        }

        //remove extra zeros that were temporarily added in encoding
        paddedString  = new StringBuilder(paddedString.substring(0, diff));

        //save the last part in array
        binary8bitChucks[size - 1] = String.valueOf(paddedString);

        //get the original binary code
        for (String c: binary8bitChucks){
            binaryResult.append(c);
        }
        return String.valueOf(getOriginalResult(binaryResult, charCodeMap));
    }
    private StringBuilder getOriginalResult(StringBuilder binaryResult, Map<Character,String> charCodeMap){
        //now we have the binaryResult and the charCodeMap, lets get the original text
        StringBuilder result = new StringBuilder();
        String s = "";
        for (int i = 0; i < binaryResult.length(); i++) {
            s = s.concat(String.valueOf(binaryResult.charAt(i)));
            for (Map.Entry<Character, String> entry : charCodeMap.entrySet()) {
                if (s.equals(entry.getValue())) {
                    result.append(entry.getKey());
                    s = "";
                    break;
                }
            }
        }
        return result;
    }

}
