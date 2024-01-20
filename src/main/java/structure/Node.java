package structure;

public class Node {
    private final int frequency;
    private final char character;
    Node left;
    Node right;
    public Node(char character, int frequency){
        this.frequency = frequency;
        this.character = character;
    }
    public Node(char character, int frequency, Node left, Node right){
        this.character = character;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    public int getFrequency() {
        return frequency;
    }

    public char getCharacter() {
        return character;
    }
}
