package com.DictioneryPackage.Dictionery;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Hamed on 01/01/2017.
 */

public class Edit {
    private Node root;

    private Search search;

    public Edit(){
        root = new Node();
        search = new Search();
        root.setLetter( ' ' );
        readWordsFromFile();
    }
    private void readWordsFromFile(){
        String path = "Dictionary.txt", word , meaning;
        try {
            FileReader fileReader = new FileReader( path );
            BufferedReader bufferedReader = new BufferedReader( fileReader );
            String readLine ;
            while ( ( readLine = bufferedReader.readLine() ) != null ){
                String[] parts = readLine.split( " " );
                word = parts[0];
                meaning = parts[1];
                addWord( word , meaning );
            }
            bufferedReader.close();
        }catch ( IOException e ){
            System.out.println( " The File In Path : " + path + " not Opened !! " );
        }
    }

    public Node getRoot(){
        return this.root;
    }

    public void addWord( String word , String meaning ){
        if( search.searchByWord( getRoot() , word ) == null ) {
            addInTree(root, word, meaning);
        }
    }

    public void deleteWord( String word , StringBuilder message ){
        Search search = new Search();
        Node searchNode = search.searchByWord( getRoot() , word );
        Node temp;
        if( searchNode != null ){
            int i = 0;
            while ( i < word.length() ){
                temp = searchNode;
                if( ( searchNode.getLeftChild() == null ) && ( searchNode.getRightChild() == null ) ){
                    if( ( searchNode.getParent().getLeftChild() == searchNode ) ){
                        searchNode.getParent().setLeftChild( null );
                    }
                    else {
                        searchNode.getParent().setRightChild( null );
                    }
                    searchNode = temp.getParent();
                    i ++;
                }
                else if( ( searchNode.getLeftChild() == null ) && ( searchNode.getRightChild() != null ) ){
                    if( searchNode.getParent().getLeftChild() == searchNode ){
                        searchNode.getRightChild().setParent( searchNode.getParent() );
                        searchNode.getParent().setLeftChild( searchNode.getRightChild() );
                        searchNode = null;
                        searchNode = temp.getParent();
                        i ++;
                    }
                    else {
                        searchNode.getRightChild().setParent( searchNode.getParent() );
                        searchNode.getParent().setRightChild( searchNode.getRightChild() );
                        searchNode = null;
                        searchNode = temp.getParent();
                        i ++;
                    }
                }
                else if( ( searchNode.getLeftChild() != null ) ){
                    searchNode.setWordMeaning( "" );
                    break;
                }
            }
            message.append( "Exist" );
        }
        else{
            message.append( "Not Exist" );
        }

    }

    public void editMeaning( String word , String newMeaning , StringBuilder message ){
        Search search = new Search();
        Node searchNode = search.searchByWord( getRoot() , word );
        if( search != null && searchNode.getWordMeaning() != "" ){
            searchNode.setWordMeaning( newMeaning );
            message.append( "Exist" );
        }
        else {
            message.append( "NOT EXIST" );
        }
    }

    public void addInTree( Node root , String word , String meaning ){
        if( root.getLetter() == ' ' ){
            root.setParent( null );
            root.setLetter( word.charAt( 0 ) );
        }
        Node node = root;
        int i = 0;
        while ( i < word.length()  ){
            if( node.getLetter() == word.charAt( i ) ){
                if( i == word.length() - 1 ){
                    node.setWordMeaning( meaning );
                }
                i ++;
                if( i <  word.length() && node.getLeftChild() == null ){
                    Node newNode = new Node();
                    newNode.setLetter( word.charAt( i ) );
                    newNode.setParent( node );
                    node.setLeftChild( newNode );
                }
                node = node.getLeftChild();
            }
            else{
                if( node.getRightChild() == null ){
                    Node newNode = new Node();
                    newNode.setLetter( word.charAt( i ) );
                    newNode.setParent( node );
                    node.setRightChild( newNode );
                }
                node = node.getRightChild();
            }
        }
    }

    public void printMeaning( Node root ){
        if( root != null ){
           /* if( root.getWordMeaning() != "" ){
                System.out.println( root.getLetter() + " - " + root.getWordMeaning() );
            }*/
            printMeaning( root.getLeftChild() );
            System.out.print( "the letter : " + root.getLetter() + "  parrent is : " );
            if( root.getParent() != null ){
                System.out.println( root.getParent().getLetter() );
            }
            else{
                System.out.println();
            }
            printMeaning( root.getRightChild() );
        }
    }
}