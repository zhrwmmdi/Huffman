import structure.Node;
public class HuffmanDecoding {
    private final Node root;
    private final StringBuilder codedText;
    public HuffmanDecoding(){
        codedText = HuffmanCoding.getCodedText();
        root = HuffmanCoding.getRoot();
    }
    public void decode(){
        System.out.print("Decoded text: ");
        if (isLeaf(root)) {
            //special case: For input like a, aa, aaa, etc.
            int freq = root.getFrequency();
            while (freq-- > 0) {
                System.out.print(root.getCharacter());
            }
        }
        else {
            //move over the Huffman tree again and decode the encoded string
            int index = -1;
            while (index < codedText.length() - 1) {
                index = decodeData(root, index, codedText);
            }
        }
        System.exit(0);
    }
    private static int decodeData(Node root, int index, StringBuilder sb) {
        if (root == null) return index;
        if (isLeaf(root)){
            System.out.print(root.getCharacter());
            return index;
        }
        index++;
        root = (sb.charAt(index) == '0') ? root.left() : root.right();
        index = decodeData(root, index, sb);
        return index;
    }
    private static boolean isLeaf(Node node) {
        return node.left() == null && node.right() == null;
    }
}
