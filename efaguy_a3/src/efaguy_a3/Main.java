/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package efaguy_a3;

import java.util.Arrays;

/**
 *
 * @author Eric
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String test[] = {};
        GUI market;
        if(Arrays.equals(args, test))
        {
            market = new GUI("");
        }
        else
        {
            market = new GUI(args[0]);
        }
        
        market.setVisible(true);
    }
    
}
