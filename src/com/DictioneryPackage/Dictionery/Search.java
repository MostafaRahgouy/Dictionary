package com.DictioneryPackage.Dictionery;

/**
 * Created by Hamed on 01/01/2017.
 */

public class Search {

    public Node searchByWord(Node root , String word ){
        String wordFromTree = "" ; // this string not exist in reality
        if( root.getLetter() == ' ' )
        {
            return null; // this not true return return have to be space
        }
        Node node = root;
        int i = 0;
        while ( i < word.length()  ){
            if( node.getLetter() == word.charAt( i ) ){
                wordFromTree += node.getLetter();
                if( word.equals( wordFromTree ) && node.getWordMeaning() != "" ){
                    return node;
                }
                i ++;
                if( i <  word.length() && node.getLeftChild() == null ){
                   return null;
                }
                node = node.getLeftChild();
            }
            else {
                if (node.getRightChild() == null) {
                    return null;
                }
                node = node.getRightChild();
            }
        }
        return null;
    }

    public String searchByMeaning( Node root , String meaning ){
        Node meaningNode = searchMeaningNodeByInorder( root , meaning );
        String word = "";
        if( meaningNode != null ) {
            word += meaningNode.getLetter();
            Node parent;
            while (meaningNode.getParent() != null) {
                parent = meaningNode.getParent();
                if ((parent.getLeftChild() == meaningNode)) {
                    word += parent.getLetter();
                }
                meaningNode = parent;
            }
            word = getWord( word );
        }
        return word;
    }

    private String getWord( String word ){
        int i , j;
        char temp;
        StringBuilder stringBuilder = new StringBuilder( word );
        for( i = 0 , j = ( word.length() - 1 ) ; i < j ; i ++ , j -- ){
            temp = word.charAt( i );
            stringBuilder.setCharAt( i , word.charAt( j ) );
            stringBuilder.setCharAt( j , temp );
        }
        word = stringBuilder.toString();
        return word;
    }

    private Node searchMeaningNodeByInorder( Node root , String meaning ){
        Node virtualNode;
        if( root != null ){
            virtualNode = searchMeaningNodeByInorder( root.getLeftChild() , meaning );
            if( ( virtualNode != null ) && ( virtualNode.getWordMeaning() ).equals( meaning ) ){
                return virtualNode;
            }
            if( root != null && ( root.getWordMeaning() ).equals( meaning )){
                return root;
            }
            virtualNode = searchMeaningNodeByInorder( root.getRightChild() , meaning );
            if( ( virtualNode != null ) && ( virtualNode.getWordMeaning() ).equals( meaning ) ){
                return virtualNode;
            }
        }
        return null;
    }
}