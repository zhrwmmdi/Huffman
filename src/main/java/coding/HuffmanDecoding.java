package coding;
import data_handler.FileReader;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HuffmanDecoding {

    private final Map<String,String> charCodeMap = new HashMap<>();

    public void decode(String data){

        String[] split =data.split("[\\D]");
        String codedData = split[0];
        //fill charCode map
        for (int i = 0, j =1; i < data.length(); i++) {
            if (!Character.isDigit(data.charAt(i))){
                charCodeMap.put(String.valueOf(data.charAt(i)),split[j]);
                j++;
            }
        }
        String result = String.valueOf(getOriginalText(codedData,charCodeMap));
        createOriginalTxtFile(result);
        System.out.println("Decoded text: " + result);
    }

    private StringBuilder getOriginalText(String codedText, Map<String,String> charCodes){
        //Decode according to map
        StringBuilder result = new StringBuilder();
        String s = "";
        for (int i = 0; i < codedText.length(); i++) {
            s = s.concat(String.valueOf(codedText.charAt(i)));
            for (Map.Entry<String,String> entry: charCodes.entrySet()){
                if (s.equals(entry.getValue())){
                    result.append(entry.getKey());
                    s = "";
                    break;
                }
            }
        }
        return result;
    }
    private void createOriginalTxtFile(String data){
        String destinationPath = "C:/Users/Hp/Desktop/Original Files/original.txt";
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(destinationPath))) {
        dos.writeBytes(data);
            System.out.println("File created successfully in C -> Users -> Hp -> Desktop -> Original Files.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
