package coding;

import structure.Node;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Huffman {
    int j;
    static final Map<Character, Integer> charFreqMap = new HashMap<>();
    static Map<Character, String> charCodeMap = new HashMap<>();
    static Node root;
    final PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(Node::getFrequency));
    Node createHuffmanTree() {
        //used to create and then return a 'set' of the same elements that are already present in the HashMap. Because in map case we can't have item.getKey() or item.getValue()
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
        return root;
    }
     void fillCharFreqMap(String data) {
        for (char c : data.toCharArray()) {
            charFreqMap.put(c, (charFreqMap.getOrDefault(c, 0) + 1));
        }
    }

     void fillCharFreqMap(String[] split) {
         boolean cont = true;
             int j = 1;
             while (cont) {
                 try {
                     if (split[j].length() == 0) {
                         j++;
                         charFreqMap.put(' ', Integer.valueOf(split[j]));
                     } else {
                         charFreqMap.put(split[j].charAt(0), Integer.valueOf(split[j].substring(1)));
                     }
                     j++;
                 } catch (NumberFormatException e) {
                     cont = false;
                 }
             }
             this.j = j;
             // j = beginf index of coded text


//        for (int j = 1; j < split.length-1 ; j++) {
//            if (split[j].length()== 0){
//                j++;
//                charFreqMap.put(' ', Integer.valueOf(split[j]));
//            }else {
//                charFreqMap.put(split[j].charAt(0), Integer.valueOf(split[j].substring(1)));
//            }
//        }
     }
     Map<Character, String> createCharCodes(Node root, String str, Map<Character, String> charCode) {
        if (root == null) return null;
        if (isLeaf(root)) charCode.put(root.getCharacter(), str.length() > 0 ? str : "1");

        createCharCodes(root.left(), str + '0', charCode);
        createCharCodes(root.right(), str + '1', charCode);
        return charCode;
    }

     boolean isLeaf(Node node) {
        return node.left() == null && node.right() == null;
    }
}
