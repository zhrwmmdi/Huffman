package coding;

import data_handler.FileReader;
import structure.Node;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanEncoding {
    private final Map<Character, Integer> charFrequencyMap = new HashMap<>();
    private static Map<Character, String> charCodeMap = new HashMap<>();
    private static StringBuilder codedText;
    private static Node root;

    //The double colon (::) operator, also known as method reference operator in Java, is used to call a method by referring to it with the help of its class directly.
    private final PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(Node::getFrequency));
    public void encode(String data){
        Node root = createHuffmanTree(data);
        charCodeMap = createCharCodes(root, "", charCodeMap);
        codedText = produceEncodedString();
        createComFile(String.valueOf(codedText));
        System.out.println("Encoded text: "+ codedText);
    }
    public void createComFile(String data){
        String destinationPath = String.format("C:/Users/Hp/Desktop/Compressed Files/%s.cmp",FileReader.getStringData());
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(destinationPath))) {
            //dos.writeChars();
            dos.writeChars(data);
            for (var item : charCodeMap.entrySet()){
                dos.writeChars(item.getKey()+item.getValue());
            }
            System.out.println("File created successfully in C -> Users -> Hp -> Desktop -> Compressed Files.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private Node createHuffmanTree(String data){
        fillMap(data);
        //used to create and then return a 'set' of the same elements that are already present in the HashMap. Because in map case we can't have item.getKey() or item.getVAlue()
        for (var item : charFrequencyMap.entrySet()){
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
        return root;
    }
    private void fillMap(String data){
        for (char c:data.toCharArray()){
            charFrequencyMap.put(c,(charFrequencyMap.getOrDefault(c,0)+1));
        }
    }
    public  Map<Character,String> createCharCodes(Node root, String str, Map<Character, String> charCode) {
        if (root == null) return null;
        if (isLeaf(root)) charCode.put(root.getCharacter(), str.length() > 0 ? str : "1");

        createCharCodes(root.left(), str + '0', charCode);
        createCharCodes(root.right(), str + '1', charCode);
        return charCode;
    }
    private   boolean isLeaf(Node node) {
        return node.left() == null && node.right() == null;
    }
    private StringBuilder produceEncodedString (){
        StringBuilder resultString = new StringBuilder();
        for (char c: FileReader.getStringData().toCharArray()){
            resultString.append(charCodeMap.get(c));
        }
        return resultString;
    }
    public static StringBuilder getCodedText() {
        return codedText;
    }
    public static Node getRoot(){
        return root;
    }
}

