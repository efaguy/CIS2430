/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package efaguy_a2;

import java.util.*;

/**
 * The main command loop asking the user what they want to do and calling the appropriate function
 * @author Eric
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String test[] = {};
        Scanner s = new Scanner(System.in);
        Portfolio investments = new Portfolio();
        if(Arrays.equals(args, test))
        {
            System.out.println("No filename provided\n");
        }
        else
        {
            investments.load(args[0]);
        }
        String choice;
        OUTER:
        while (true) {
            System.out.println("Please select an option for the list below:");
            System.out.println("Buy\nSell\nUpdate\nGetGain\nSearch\nQuit");
            choice = s.nextLine();
            switch (choice.toLowerCase()) {
                case "buy":
                case "b":
                    investments.buy();
                    break;
                case "sell":
                case "s":
                    investments.sell();
                    break;
                case "update":
                case "u":
                    investments.update();
                    break;
                case "getgain":
                case "g":
                    investments.getGain();
                    break;
                case "search":
                case "se":
                    investments.search();
                    break;
                case "quit":
                case "q":
                    break OUTER;
                default:
                    System.out.println("\nPlease select a valid option\n");
                    break;
            }
        }
        if(!Arrays.equals(args, test))
        {
            investments.save(args[0]);
        }
        else
        {
            System.out.println("What filename would you like to save to: ");
            choice = s.nextLine();
            investments.save(choice);
        }
    }
    
}
