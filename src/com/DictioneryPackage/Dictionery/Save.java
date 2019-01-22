package com.DictioneryPackage.Dictionery;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Hamed on 01/01/2017.
 */

public class Save {

    private String path = "Dictionary.txt";
    private Search search = new Search();
    public Save(){
        try {
            FileWriter fileWriter = new FileWriter( path );
            BufferedWriter bufferedWriter = new BufferedWriter( fileWriter );
            bufferedWriter.write( "" );
            bufferedWriter.close();
        }catch ( IOException e ){
            System.out.println( " The File In Path : " + path + " not Opened !! " );
        }
    }
    public void saveToFileByInorder( Edit edit , Node root , String meaning ){
        String word;
        if( root != null ){
            saveToFileByInorder( edit , root.getLeftChild() , meaning );
            if( root != null && ( root.getWordMeaning() !=  meaning ) ){
                word = search.searchByMeaning( edit.getRoot() , root.getWordMeaning() );
                addToFile( word , root.getWordMeaning() );
            }
            saveToFileByInorder( edit ,root.getRightChild() , meaning );
        }
    }

    private void addToFile( String word , String meaning ){
        try {
            FileWriter fileWriter = new FileWriter( path , true );
            BufferedWriter bufferedWriter= new BufferedWriter( fileWriter );
            if( !isExistInFile( word ) ){
                bufferedWriter.write( word + " " + meaning + "\n" );
            }
            bufferedWriter.close();
        }catch ( IOException e ){
            System.out.println( " The File In Path : " + path + " not Opened !! " );
        }
    }

    private boolean isExistInFile( String word ){
        try {
            FileReader fileReader = new FileReader( path );
            BufferedReader bufferedReader = new BufferedReader( fileReader );
            String readLine ;
            while ( ( readLine = bufferedReader.readLine() ) != null ){
                String[] parts = readLine.split( " " );
                if(  parts[0].equals( word ) ){
                    return true;
                }
            }
            bufferedReader.close();
        }catch ( IOException e ){
            System.out.println( " The File In Path : " + path + " not Opened !! " );
        }
        return false;
    }
}
