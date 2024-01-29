package coding;

import data_handler.FileWriter;

import java.util.HashMap;
import java.util.Map;

public class HuffmanDecoding {
    private final Map<String, String> charCodeMap = new HashMap<>();

    public void decode(String data) {
        String[] split = data.split("[\\D]");
        String codedData = split[0];
        //fill charCode map
        for (int i = 0, j = 1; i < data.length(); i++) {
            if (!Character.isDigit(data.charAt(i))) {
                charCodeMap.put(String.valueOf(data.charAt(i)), split[j]);
                j++;
            }
        }
        String result = String.valueOf(getOriginalText(codedData, charCodeMap));
        FileWriter.createOriginalTxtFile(result);
        System.out.println("Decoded text: " + result);
    }

    private StringBuilder getOriginalText(String codedText, Map<String, String> charCodes) {
        //Decode according to map
        StringBuilder result = new StringBuilder();
        String s = "";
        for (int i = 0; i < codedText.length(); i++) {
            s = s.concat(String.valueOf(codedText.charAt(i)));
            for (Map.Entry<String, String> entry : charCodes.entrySet()) {
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
