package coding;

import data_handler.FileWriter;
import structure.Node;

import java.util.*;

public class HuffmanDecoding {
    private final Map<Character, Integer> charFreqMap = new HashMap<>();
    private Map<Character,String> charCodeMap = new HashMap<>();
    private final PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(Node::getFrequency));
    private int diff;

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

        //write original data on txt file
        FileWriter.createOriginalTxtFile(result);

        //print out original data
        System.out.println("Decoded text: " + result);
    }
    private Node createHuffmanTree(){
        for (var item : charFreqMap.entrySet()) {
            queue.add(new Node(item.getKey(), item.getValue()));
        }
        while (queue.size() > 1) {
            Node left = queue.poll();
            Node right = queue.poll();

            assert right != null;
            int sumFreq = left.getFrequency() + right.getFrequency();

            queue.add(new Node(sumFreq, left, right));
        }
        return queue.peek();
    }
    private void fillCharFreqMap(String[] split){
        for (int j = 1; j < split.length-1 ; j++) {
            if (split[j].length()== 0){
                j++;
                charFreqMap.put(' ', Integer.valueOf(split[j]));
            }else {
                charFreqMap.put(split[j].charAt(0), Integer.valueOf(split[j].substring(1)));
            }
        }
    }

    public Map<Character, String> createCharCodes(Node root, String str, Map<Character, String> charCode) {
        if (root == null) return null;
        if (isLeaf(root)) charCode.put(root.getCharacter(), str.length() > 0 ? str : "1");

        createCharCodes(root.left(), str + '0', charCode);
        createCharCodes(root.right(), str + '1', charCode);
        return charCode;
    }

    private boolean isLeaf(Node node) {
        return node.left() == null && node.right() == null;
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
        StringBuilder binaryResult  = new StringBuilder();
        for (String c: binary8bitChucks){
            binaryResult.append(c);
        }
        System.out.println("Binary Result:"+binaryResult);
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
