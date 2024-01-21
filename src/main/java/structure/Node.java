package structure;

public class Node {
    private final int frequency;
    private char character;
    Node left;
    Node right;
    public Node(char character, int frequency){
        this.frequency = frequency;
        this.character = character;
    }
    public Node(int frequency, Node left, Node right){
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
    public Node left(){
        return left;
    }
    public Node right(){
        return right;
    }
}
