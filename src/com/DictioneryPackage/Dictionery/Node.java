package com.DictioneryPackage.Dictionery;

/**
 * Created by Hamed on 01/01/2017.
 */

public class Node {

    private Node leftChild;
    private Node rightChild;
    private Node parent;
    private char letter;
    private String wordMeaning;

    public Node(){
        wordMeaning = "";
    }

    public Node getLeftChild()
    {
        return leftChild;
    }


    public void setLeftChild( Node leftChild ) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }
    public void setRightChild( Node rightChild ) {
        this.rightChild = rightChild;
    }

    public Node getParent(){
        return parent;
    }
    public void setParent( Node parent ){
        this.parent = parent;
    }

    public char getLetter() {
        return letter;
    }
    public void setLetter( char letter ) {
        this.letter = letter;
    }

    public String getWordMeaning(){
        return wordMeaning;
    }
    public void setWordMeaning( String wordMeaning ){
        this.wordMeaning = wordMeaning;
    }

}
