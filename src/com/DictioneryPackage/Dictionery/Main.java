package com.DictioneryPackage.Dictionery;

import java.util.Scanner;

public class Main {

    static Scanner getter = new Scanner( System.in );

    private static Edit edit = new Edit();
    private static Search search = new Search();

    public static void main(String[] args) {
        String word , meaning;
        StringBuilder massege;

		while( true ){
			switch ( menu() ) {
				case 1:
					switch( searchMenu() ){
						case 1:
                            System.out.print( "Enter word to search in dictionary : " );
                            word = getter.next();
                            Node searchNode = search.searchByWord( edit.getRoot() , word );
                            if ( searchNode == null ){
                                System.out.println( "NOT EXIST" );
                            }
                            else {
                                System.out.println("Word Meaning is : " + searchNode.getWordMeaning() );
                            }
							break;
						case 2:
                            System.out.print( "Enter meaning of Word to search in dictionary : " );
                            meaning = getter.next();
                            String meaningWord = search.searchByMeaning( edit.getRoot() , meaning );
                            if ( meaningWord.equals( "" ) ){
                                System.out.println( "NOT EXIST" );
                            }
                            else {
                                System.out.println( "The word is : " +  meaningWord );
                            }
							break;
						default:
							break;
					}
					break;
				case 2:
                    switch ( editMenu() ){
                        case 1:
                            System.out.print( "Enter the word for add to dictionary : " );
                            word = getter.next();
                            System.out.print( "Enter the meaning of this word : " );
                            meaning = getter.next();
                            edit.addWord( word , meaning );
                            break;
                        case 2:
                            massege = new StringBuilder();
                            System.out.print( "Enter the word for delete from dictionary : " );
                            word = getter.next();
                            edit.deleteWord( word , massege );
                            if( ( massege.toString().equals( "Exist" ) ) ){
                                System.out.println( "Word delete succesfuly." );
                            }
                            else{
                                System.out.println( " Word Not Deleted becuse : " + massege.toString() );
                            }
                            break;
                        case 3:
                            massege = new StringBuilder();
                            System.out.print( "Enter word for change meaning : " );
                            word = getter.next();
                            System.out.print( "Enter new meaning of this word : " );
                            meaning = getter.next();
                            edit.editMeaning( word , meaning , massege );
                            if( ( massege.toString().equals( "Exist" ) ) ){
                                System.out.println( "Word Meaning Edited." );
                            }
                            else{
                                System.out.println( " Word Meaning Not Edited becuse : " + massege.toString() );
                            }
                            break;
                        default:
                            break;
                    }
					break;
				default:
                    Save save = new Save();
                    save.saveToFileByInorder( edit , edit.getRoot() , "" );
					System.exit( 0 );
			}
		}
    }

    private static int menu(){
        System.out.println( "                  MENU               " );
        System.out.println( "_____________________________________" );
        System.out.println( " 1. Search                           " );
        System.out.println( " 2. Edit                             " );
        System.out.println( " 3. Exit                             " );
        System.out.println( "_____________________________________" );
        System.out.println( "Choice An Option :                   " );
        return getter.nextInt();
    }

    private static int searchMenu(){
        System.out.println( "                SEARCH MENU           " );
        System.out.println( "______________________________________" );
        System.out.println( "1. Search By Word                     " );
        System.out.println( "2. Search By Mean                     " );
        System.out.println( "______________________________________" );
        System.out.println( "Choice An Option :                    " );
        return getter.nextInt();
    }

    private static int editMenu(){
        System.out.println( "                EDIT MENU             " );
        System.out.println( "______________________________________" );
        System.out.println( "1. Add New Word                       " );
        System.out.println( "2. Delete Word                        " );
        System.out.println( "3. Edit Word Meaning                  " );
        System.out.println( "______________________________________" );
        System.out.println( "Choice An Option :                    " );
        return getter.nextInt();
    }

}
