import data_handler.FileReader;
import structure.Node;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanCoding {
    private final Map<Character, Integer> charFrequencyMap = new HashMap<>();

    //The double colon (::) operator, also known as method reference operator in Java, is used to call a method by referring to it with the help of its class directly.
    private final PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(Node::getFrequency));
    public void encode(String data){
        Node root = createHuffmanTree(data);

    }
    public Node createHuffmanTree(String data){
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
        return queue.peek();
    }
    private void fillMap(String data){
        for (char c:data.toCharArray()){
            charFrequencyMap.put(c,(charFrequencyMap.getOrDefault(c,0)+1));
        }
    }
}

