package data_handler;

import java.util.HashMap;
import java.util.Map;

public class CharacterMap {
    Map<Character, Integer> charMap = new HashMap<>();
    public void fillMap(String data){
        for (char c:data.toCharArray()){
            charMap.put(c,(charMap.getOrDefault(c,0)+1));
        }
    }


}

