/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

import java.util.Arrays;
import java.util.Scanner;
import java.util.List;

/**
 *
 * @author Eric
 */
public class Lab1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String[] sentences = new String[10];
        boolean found = false;
        String search = null;
        String searchSent = null;
        int choice = 0;        
        int i = 0;
        int numChars = 0;
        int numVols;
        loop: while(true)
        {
            System.out.println("Please select one of the following option by entering the number\n1. Enter a sentence\n2. Print all sentences entered, in the order they were entered\n3. Print all sentences entered, in reverse order\n4. Print the total number of sentences entered\n5. Print the total number of characters in all sentences\n6. Print the number of vowels in all sentences\n7. Print the kth sentence stored (if it exists)\n8. Search for a word using case-insensitive comparisons\n9. Search for a word using case-sensitive comparisons\n10. Quit");
            choice = s.nextInt();
            s.nextLine(); //Remove newline from input stream
            while(choice > 10 || choice < 1) 
            {
                System.out.println("Please enter a valid choice.");
                System.out.println("Please select one of the following option by entering the number\n1. Enter a sentence\n2. Print all sentences entered, in the order they were entered\n3. Print all sentences entered, in reverse order\n4. Print the total number of sentences entered\n5. Print the total number of characters in all sentences\n6. Print the number of vowels in all sentences\n7. Print the kthsentence stored (if it exists)\n8. Search for a word using case-insensitive comparisons\n9. Search for a word using case-sensitive comparisons\n10. Quit");
                choice = s.nextInt();
                s.nextLine(); //Remove newline from input stream
            }
            switch(choice)
            {
                case 1: 
                    if (sentences[9] != null)
                    {
                        System.out.println("There are already 10 sentences entered");
                        break;
                    }
                    i = 0;
                    for (i = 0; i < sentences.length; i ++) 
                    {
                        if (sentences[i] == null) break;
                    }
                    System.out.println("Please enter a sentence:");
                    sentences[i] = s.nextLine();
                    while(sentences[i].length() == 0)
                    {
                        System.out.println("Please enter a valid sentence:");
                        sentences[i] = s.nextLine();
                    }
                    break;
                case 2:
                    if(sentences[0] == null)
                    {
                        System.out.println("There are no sentences entered.");
                    } 
                    else
                    {
                        i = 0;
                        while(sentences[i] != null)
                        {
                            System.out.println(sentences[i]);
                            i++;
                            if (i == 10) break;
                        }
                    }
                    break;
                case 3:
                    if(sentences[0] == null)
                    {
                        System.out.println("There are no sentences entered.");
                    } 
                    else
                    {
                        i = 0;
                        for (i = 0; i < sentences.length; i ++) 
                        {
                            if (sentences[i] == null) break;
                        }
                        i--;
                        for (i = i;i >= 0;i--)
                        {
                            System.out.println(sentences[i]);
                        }
                    }
                    break;
                case 4:
                    if(sentences[0] == null)
                    {
                        System.out.println("There are no sentences entered.");
                    }
                    else
                    {
                        for (i = 0; i < sentences.length; i ++) 
                            {
                                if (sentences[i] == null) break;
                            }
                        System.out.println("There are " + i + " sentences entered.");
                    }
                    break;
                case 5:
                    if(sentences[0] == null)
                    {
                        System.out.println("There are no sentences entered.");
                    } 
                    else
                    {
                        i = 0;
                        numChars = 0;
                        while(sentences[i] != null)
                        {
                            numChars += sentences[i].length();
                            i++;
                            if (i == 10) break;
                        }
                        System.out.println("There are " + numChars + " total characters in all sentences.");
                    }
                    break;
                case 6:
                    if(sentences[0] == null)
                    {
                        System.out.println("There are no sentences entered.");
                    } 
                    else
                    {
                        i = 0;
                        numVols = 0;
                        for (i = 0; i < sentences.length; i ++) 
                        {
                            if (sentences[i] == null) break;
                        }
                        for (int j = 0;j < i;j++)
                        {
                            for (int x = 0;x <  sentences[j].length();x++)
                            {
                                if (sentences[j].charAt(x) == 'a' || sentences[j].charAt(x) == 'e' || sentences[j].charAt(x) == 'i' || sentences[j].charAt(x) == 'o' || sentences[j].charAt(x) == 'u' || sentences[j].charAt(x) == 'y' || sentences[j].charAt(x) == 'A' || sentences[j].charAt(x) == 'E' || sentences[j].charAt(x) == 'I' || sentences[j].charAt(x) == 'O' || sentences[j].charAt(x) == 'U' || sentences[j].charAt(x) == 'Y')
                                {
                                    numVols++;
                                }
                            }
                        }
                        System.out.println("There are " + numVols + " vowels in all sentences.");
                    }
                    break;
                case 7: 
                    if(sentences[0] == null)
                    {
                        System.out.println("There are no sentences entered.");
                    }
                    else 
                    {
                        System.out.println("Which sentences would you like to print?");
                        choice = s.nextInt();
                        s.nextLine(); //Remove newline from input stream
                        while(choice <= 0 || choice > 10 || sentences[choice - 1] == null)
                        {
                            System.out.println("Please enter a valid sentence number");
                            System.out.println("Which sentences would you like to print?");
                            choice = s.nextInt();
                            s.nextLine(); //Remove newline from input stream
                        }
                        System.out.println(sentences[choice - 1]);
                    }
                    break;
                case 8:
                    found = false;
                    if(sentences[0] == null)
                    {
                        System.out.println("There are no sentences entered.");
                    }
                    else 
                    {
                        for (i = 0; i < sentences.length; i ++) 
                        {
                            if (sentences[i] == null) break;
                        }
                        System.out.println("What word would you like to search for?");
                        search = s.nextLine();
                        while(search.length() == 0)
                        {
                            System.out.println("Please enter a valid word:");
                            search = s.nextLine();
                        }
                        for(int j = 0;j < i;j++)
                        {
                            searchSent = sentences[j].toLowerCase();
                            List<String> words = Arrays.asList(searchSent.split("\\s"));
                            if(words.contains(search.toLowerCase()))
                            {
                                System.out.println(search + " has been found in sentence " + (j+1) + ": " +sentences[j]);
                                found = true;
                            }
                        }
                        if (found == false) 
                        {
                            System.out.println(search + " coud not be found");
                        }
                    }
                    break;
                case 9:
                    found = false;
                    if(sentences[0] == null)
                    {
                        System.out.println("There are no sentences entered.");
                    }
                    else 
                    {
                        for (i = 0; i < sentences.length; i ++) 
                        {
                            if (sentences[i] == null) break;
                        }
                        System.out.println("What word would you like to search for?");
                        search = s.nextLine();
                        while(search.length() == 0)
                        {
                            System.out.println("Please enter a valid word:");
                            search = s.nextLine();
                        }
                        for(int j = 0;j < i;j++)
                        {
                            List<String> words = Arrays.asList(sentences[j].split("\\s"));
                            if(words.contains(search))
                            {
                                System.out.println(search + " has been found in sentence " + (j+1) + ": " +sentences[j]);
                                found = true;
                            }
                        }
                        if (found == false) 
                        {
                            System.out.println(search + " coud not be found");
                        }
                    }
                    break;
                case 10:
                    break loop;
                    
                
            }
            
        
        }
    
    }
}
