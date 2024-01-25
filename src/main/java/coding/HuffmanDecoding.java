package coding;
import structure.Node;
public class HuffmanDecoding {
    private final Node root;
    private final StringBuilder codedText;
    public HuffmanDecoding(){
        codedText = HuffmanEncoding.getCodedText();
        root = HuffmanEncoding.getRoot();
    }
    public void decode(){
        System.out.print("Decoded text: ");
        //For input with only one kind of character like a, aa, aaa
        if (isLeaf(root)) {
            int freq = root.getFrequency();
            for (int i = 0; i < freq; i++) {
                System.out.print(root.getCharacter());
            }
        }
        else {
            int index = -1;
            while (index < codedText.length() - 1) {
                index = decodeData(root, index, codedText);
            }
        }
        System.exit(0);
    }
    private static int decodeData(Node root, int index, StringBuilder codedTxt) {
        if (root == null) return index;
        if (isLeaf(root)){
            System.out.print(root.getCharacter());
            return index;
        }
        index++;
        root = (codedTxt.charAt(index) == '0') ? root.left() : root.right();
        index = decodeData(root, index, codedTxt);
        return index;
    }
    private static boolean isLeaf(Node node) {
        return node.left() == null && node.right() == null;
    }
}
