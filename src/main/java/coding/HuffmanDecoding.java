package coding;

import data_handler.FileReader;
import data_handler.FileWriter;
import structure.Node;

import java.util.*;

public class HuffmanDecoding {
    private final Map<Character, Integer> charFreqMap = new HashMap<>();
    private Map<Character,String> charCodeMap = new HashMap<>();
    private final PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(Node::getFrequency));
    Node root;
    int diff;

    public void decode(String data) {
        String[] split = data.split("[ ]");
        diff  = Integer.parseInt(split[0]);
        String codedData = split[split.length-1];
        //fill charCode map
        for (int j = 1; j < split.length-1 ; j++) {
            if (split[j].length()== 0){
                j++;
                charFreqMap.put(' ', Integer.valueOf(split[j]));
            }else {
                charFreqMap.put(split[j].charAt(0), Integer.valueOf(split[j].substring(1)));
            }
        }
        //tick

        //create the tree
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
        root = queue.peek();


        //get the codes
        charCodeMap = createCharCodes(root,"",charCodeMap);


        String result = String.valueOf(getOriginalText(codedData, charCodeMap));
        FileWriter.createOriginalTxtFile(result);
        System.out.println("Decoded text: " + result);
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

    private StringBuilder produceEncodedString() {
        StringBuilder resultString = new StringBuilder();
        for (char c : FileReader.getStringData().toCharArray()) {
            resultString.append(charCodeMap.get(c));
        }
        return resultString;
    }

    private StringBuilder getOriginalText(String codedText, Map<Character, String> charCodes) {
        //Decode according to map
        String[] binar  = new String[codedText.length()];
        for (int i = 0; i < codedText.length()-1; i++) {
            int da = codedText.charAt(i);

            //gets the ascii code of each character in decimal redix
            String f = Integer.toBinaryString(da);//converts that decimal ascii value to binary
            StringBuilder h = new StringBuilder();
            //put zero before it
            StringBuilder paddedString = new StringBuilder(f);
            while (paddedString.length() < 8) {
                paddedString.insert(0, '0');
            }


            //put 8 bit binary num in an array
            binar[i] = String.valueOf(paddedString);
        }

        //the last char that is the last chunk
        int t = codedText.charAt(codedText.length()-1);//ascii code of last char that was the last chunck
        String tt = Integer.toBinaryString(t);//binary value of the last chunk

        //check 8 length
        StringBuilder h = new StringBuilder();
        if (tt.length() < 8){
            int y = 8 - tt.length();
            h.append("0".repeat(y));
            h.append(tt);
            tt = String.valueOf(h);
        }
        tt  = tt.substring(0,diff);//010

        binar[codedText.length() - 1] =tt;



        StringBuilder binarResult  = new StringBuilder();
        for (String c: binar){
            binarResult.append(c);
        }

        //now we have th binary decoded value, lets get the original txt


        System.out.println("Binary Result:"+binarResult);


        StringBuilder result = new StringBuilder();
        String s = "";
        for (int i = 0; i < binarResult.length(); i++) {
            s = s.concat(String.valueOf(binarResult.charAt(i)));
            for (Map.Entry<Character, String> entry : charCodes.entrySet()) {
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
