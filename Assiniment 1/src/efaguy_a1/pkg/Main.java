/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package efaguy_a1.pkg;

import java.util.*;

/**
 *
 * @author Eric
 */

public class Main {

    /**
     * Ask the user want they want to do and call the respective function
     * @param args
     */
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Portfolio investments = new Portfolio();
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
                    System.out.println("Please select a valid option");
                    break;
            }
        }
    }
    
}
    
