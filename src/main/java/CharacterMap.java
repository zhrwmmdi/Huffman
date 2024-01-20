import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class CharacterMap {
    private final Map<Character, Integer> charMap = new HashMap<>();
    private void fillMap(String data){
        for (char c:data.toCharArray()){
            charMap.put(c,(charMap.getOrDefault(c,0)+1));
        }
    }

    public void createHuffmanTree(String data){
        fillMap(data);

    }


}

