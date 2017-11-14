/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package efaguy_a2;

import java.io.*;
import java.util.*;


/**
 * A class to hold all the investments and perform actions on them(buy, sell, search, etc.)
 * @author Eric
 */
public class Portfolio {
   
    private ArrayList<Investment> investments; 
    private  HashMap<String, ArrayList<Integer>> index;
    
    /**
     * Create a portfolio instance
     */
    public Portfolio()
    {
        investments =  new ArrayList<>();
        index = new HashMap<>();
    }
    
    /**
     * Loads in investment from a text file
     * @param filename the name of the file to load the investments from
     */
    public void load(String filename)
    {
        String type;
        String symbol;
        String name;
        int quantity;
        String sQuantity;
        double price;
        String sPrice;
        String sBookVal;
        double bookVal;
        
        try
        {
            FileReader fw = new FileReader(filename);
            BufferedReader bw = new BufferedReader(fw);
            while(true)
            {
                type = bw.readLine();
                if(type == null)
                {
                    System.out.println("Investments loaded from file.\n");
                    bw.close();
                    for(Investment curInvest : investments)
                    {
                        System.out.println(curInvest.toString());
                        System.out.print("\n");
                    }
                    break;
                }
                symbol = bw.readLine();
                
                name = bw.readLine();
                
                sQuantity = bw.readLine();
                quantity = Integer.parseInt(sQuantity);
                
                sPrice = bw.readLine();
                price = Double.parseDouble(sPrice);
                
                sBookVal = bw.readLine();
                bookVal = Double.parseDouble(sBookVal);
             
                if(type.equalsIgnoreCase("stock"))
                {
                    investments.add(new Stock(symbol, name, quantity, price, bookVal));
                }
                else if (type.equalsIgnoreCase("mutual fund"))
                {
                    investments.add(new MutualFund(symbol, name, quantity, price, bookVal));
                }
                int posn = investments.size() - 1;
                for(String word: name.toLowerCase().split(" "))
                {
                    ArrayList<Integer> cur = new ArrayList<>();
                    if(index.get(word) != null)
                        cur = index.get(word);
                    cur.add(posn);
                    index.put(word, cur);
                }
            }
        }
        catch (NumberFormatException e)
        {
            index.clear();
            investments.clear();
            System.out.println("Error reading file. No investments loaded\n");
        } 
        catch (IOException e)
        {
            index.clear();
            investments.clear();
            System.out.println("File is Empty. No investments loaded.\n");
        }
    }
    
    /**
     * Saves all current investments to a text file
     * @param filename the name of the file to save the investments to
     */
    public void save(String filename)
    {
        try
        {
            FileWriter fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw);
            
            for (Investment investment : investments) 
            {
                if(investment instanceof Stock)
                {
                    bw.write("Stock");
                    bw.newLine();
                }
                else if(investment instanceof MutualFund)
                {
                    bw.write("Mutual Fund");
                    bw.newLine();
                }
                bw.write(investment.getSymbol());
                bw.newLine();
                bw.write(investment.getName());
                bw.newLine();
                bw.write(Integer.toString(investment.getQuantity()));
                bw.newLine();
                bw.write(Double.toString(investment.getPrice()));
                bw.newLine();
                bw.write(Double.toString(investment.getBookValue()));
                bw.newLine();
            }
            System.out.println("\nInvestments saved to file.\n");
            bw.close();
        }
        catch (FileNotFoundException ex) 
        {
           System.out.println("Unable to find file with that name.\n");
        } 
        catch (IOException ex) 
        {
           System.out.println("Error writing to file.\n");
        }
    }
    
    /**
     * Buys a investments for the price and quantity defined by the user
     */
    public void buy()
    {
        Scanner s = new Scanner(System.in);
        String type;
        String symbol;
        String name;
        int quantity;
        String sQuantity;
        int i;
        double price;
        String sPrice;
        boolean found = false;
        
        //Ask user for input and checks if it is valid
        System.out.println("Do you want to buy a stock or mutual fund?");
        type = s.nextLine();
        while(!type.toLowerCase().equals("stock") && !type.toLowerCase().equals("mutual fund") && !type.toLowerCase().equals("s") && !type.toLowerCase().equals("m")) {
            System.out.println("Please either stock or mutual fund");
            type = s.nextLine(); 
        }
        System.out.print("What is the symbol of the investment: ");
        symbol = s.nextLine();
        for(i = 0;i < investments.size(); i++)
        {
            //If there is another investmemt with the same symbol
            if(investments.get(i).getSymbol().equals(symbol))
            {
                if(investments.get(i) instanceof Stock)
                {
                    if(type.equalsIgnoreCase("stock") || type.equalsIgnoreCase("s"))
                    {
                        found = true;
                        break;
                    }
                    else
                    {
                        System.out.println("\nYou already own a stock with that symbol\n");
                        return;
                    }
                }
                else if(investments.get(i) instanceof MutualFund)
                {
                    if(type.equalsIgnoreCase("mutual fund") || type.equalsIgnoreCase("m"))
                    {
                        found = true;
                        break;
                    }
                    else
                    {
                        System.out.println("\nYou already own a mutual fund with that symbol\n");
                        return;
                    }
                }
            }   
        }
        if(found)
        {
            //Tell them how much they own and ask for how many they want to buy
            System.out.println("You already own " + investments.get(i).getQuantity());
            while(true)
            {
                System.out.print("How many more would you like to buy: ");
                sQuantity = s.nextLine();
                try
                {
                    quantity = Integer.parseInt(sQuantity);
                    if(quantity <= 0)
                    {
                        System.out.println("Please enter a valid quantity");
                        continue;
                    }
                }    
                catch(NumberFormatException e)
                {
                    System.out.println("Please enter a number");
                    continue;
                }
                break;
            }
            while(true)
            {
                System.out.print("What is the price: ");
                sPrice = s.nextLine();
                try
                {
                    price = Double.parseDouble(sPrice);
                    if(price <= 0)
                    {
                        System.out.println("Please enter a valid price");
                        continue;
                    }
                }
                catch(NumberFormatException e)
                {
                    System.out.println("Please enter a valid price");
                    continue;
                }
                break;
            }
            //Update the price of the investment and add the new shares to the investment
            investments.get(i).setPrice(price);
            if(type.equalsIgnoreCase("stock") || type.equalsIgnoreCase("s"))
            {
                Stock temp = (Stock) investments.get(i);
                temp.addQuantity(investments.get(i).getQuantity() + quantity);
                //Output how much they paid and how many shares they own
                String paid = String.format("%.2f", (quantity * price) + Stock.FEE);
                System.out.println("You paid $" + paid + " and own " + investments.get(i).getQuantity() + " shares");
            }
            else if(type.equalsIgnoreCase("mutual fund") || type.equalsIgnoreCase("m"))
            {
                MutualFund temp = (MutualFund) investments.get(i);
                temp.addQuantity(investments.get(i).getQuantity() + quantity);
                //Output how much they paid and how many shares they own
                String paid = String.format("%.2f", (quantity * price));
                System.out.println("You paid $" + paid + " and own " + investments.get(i).getQuantity() + " units");
            }
        }
        //If it is a new stock
        else
        {
            System.out.print("What is the name of the investment: ");
            name = s.nextLine();
            while(true)
            {
                System.out.print("How many would you like to buy: ");
                sQuantity = s.nextLine();
                try
                {
                    quantity = Integer.parseInt(sQuantity);
                }    
                catch(NumberFormatException e)
                {
                    System.out.println("Please enter a number");
                    continue;
                }
                break;
            }
            while(true)
            {
                System.out.print("What is the price: ");
                sPrice = s.nextLine();
                try
                {
                    price = Double.parseDouble(sPrice);
                }
                catch(NumberFormatException e)
                {
                    System.out.println("Please enter a valid price");
                    continue;
                }
                break;
            }
            if(type.equalsIgnoreCase("stock") || type.equalsIgnoreCase("s"))
            {
                //Add the new stock to the list
                investments.add(new Stock(symbol, name, quantity, price));
                //Output how much they paid and how many shares they own
                String paid = String.format("%.2f", (quantity * price) + Stock.FEE);
                System.out.println("You paid $" + paid + " and own " + quantity + " shares");
            }
            else if(type.equalsIgnoreCase("mutual fund") || type.equalsIgnoreCase("m"))
            {
                investments.add(new MutualFund(symbol, name, quantity, price));
                String paid = String.format("%.2f", price * quantity);
                System.out.println("You paid $" + paid + " and own " + investments.get(i).getQuantity() + " units"); 
            }
            int posn = investments.size() - 1;
            for(String word: name.toLowerCase().split(" "))
            {
                ArrayList<Integer> cur = new ArrayList<>();
                if(index.get(word) != null)
                    cur = index.get(word);
                cur.add(posn);
                index.put(word, cur);
            }
        }
    }
    
    /**
     * Sells shares of a stock or units of a fund that are already owned  
     */
    public void sell()
    {
        //If they don't own any stocks
        if(investments.isEmpty())
        {
            System.out.println("You do not own any investments");
            return;
        }
        Scanner s = new Scanner(System.in);
        //String type;
        String symbol;
        int quantity;
        String sQuantity;
        int i;
        double price;
        String sPrice;
        int newQuantity;
        
        //Ask user for input and checks if it is valid
        System.out.print("What is the symbol of the investment you want to sell: ");
        symbol = s.nextLine();
        loop: while(true)
        {
            //Find the stock they want to sell
            for(i = 0;i < investments.size(); i++)
            {   
                //System.out.println(symbol);
                //System.out.println(investments.get(i).getSymbol());
                if(investments.get(i).getSymbol().equals(symbol))
                {
                    break loop;
                }
            }
        
            //If they entered a symbol thay doesn't correspond to a stuck
            System.out.println("You do not own a investment with that symbol. Please enter a new symbol");
            symbol = s.nextLine();
        }
            
        //Tell them how many they own and ask how many they want to sell
        System.out.println("You own " + investments.get(i).getQuantity());
        while(true)
        {
            System.out.print("How would you like to sell: ");
            sQuantity = s.nextLine();
            try
            {
                quantity = Integer.parseInt(sQuantity);
                if(quantity <= 0)
                {
                    System.out.println("Please enter a valid quantity");
                    continue;
                }
            }    
            catch(NumberFormatException e)
            {
                System.out.println("Please enter a number");
                continue;
            }
            if(investments.get(i).getQuantity() < quantity)
            {
                System.out.println("You do not own that many. Please enter a number less than " + investments.get(i).getQuantity());
                continue;
            }
            break;
        }
        while(true)
        {
            System.out.print("What is the price: ");
            sPrice = s.nextLine();
            try
            {
                price = Double.parseDouble(sPrice);
                if(price <= 0)
                 {
                    System.out.println("Please enter a valid price");
                    continue;
                }
            }
            catch(NumberFormatException e)
            {
                System.out.println("Please enter a valid price");
                continue;
            }
            break;
        }
        investments.get(i).setPrice(price);
        newQuantity = investments.get(i).getQuantity() - quantity;
        if(investments.get(i) instanceof Stock)
        {
            //Tell them how much they made and how many shares they have left
            String profit = String.format("%.2f", ((price * quantity) - Stock.FEE));
            System.out.println("You received $" + profit + " and have " + newQuantity + " shares left");
        }
        else if(investments.get(i) instanceof MutualFund)
        {
            //Tell them how much they made and how many units they have left
            String profit = String.format("%.2f", ((price * quantity) - MutualFund.FEE));
            System.out.println("You received $" + profit + " and have " + newQuantity + " units left");
        }
        if(newQuantity == 0)
        {
            investments.remove(i);
            int posn = 0;
            index.clear();
            for(Investment cur: investments)
            {
                for(String word: cur.getName().toLowerCase().split(" "))
                {
                    ArrayList<Integer> list = new ArrayList<>();
                    if(index.get(word) != null)
                    {
                        list = index.get(word);
                    }
                    list.add(posn);
                    index.put(word, list);
                }
            posn++;
            }
        }
        else 
        {
            //Remove the shares that were sold
            investments.get(i).removeQuantity(newQuantity);
        }
    }
    /**
     * Ask the user for new prices for all investment and updates them
     */
    public void update()
    {
        double price;
        String sPrice;
        Scanner s = new Scanner(System.in);
        //For all the investments
        for(int i = 0;i < investments.size();i++)
        {
            //Tell them the current price
            String curPrice = String.format("%.2f", investments.get(i).getPrice());
            System.out.println("The current price for " + investments.get(i).getSymbol() + " is $" + curPrice);
            while(true)
            {
                System.out.print("What is the new price: ");
                sPrice = s.nextLine();
                try
                {
                    price = Double.parseDouble(sPrice);
                }
                catch(NumberFormatException e)
                {
                    System.out.println("Please enter a valid price");
                    continue;
                }
                break;
            }
            investments.get(i).setPrice(price);
        }
    }
    
    /**
     * Calculate the profit that would be many if all the investment were sold at the current prices
     */
    public void getGain()
    {
        double totalGain = 0;
        double curGain;
        //For all the investments
        for(int i = 0;i < investments.size();i++)
        {
            //Caculate the gain for the investments then add it to the total gain
            if(investments.get(i) instanceof Stock)
            {
                curGain = ((investments.get(i).getQuantity()* investments.get(i).getPrice()) - Stock.FEE) - investments.get(i).getBookValue();
                totalGain += curGain;
            }
            else if (investments.get(i) instanceof MutualFund)
            {
                curGain = ((investments.get(i).getQuantity()* investments.get(i).getPrice()) - MutualFund.FEE) - investments.get(i).getBookValue();
                totalGain += curGain;
            }
        }
        String gain = String.format("%.2f", totalGain);
        System.out.println("The total gain of your investment portfolio is $" + gain);
        System.out.println(index.toString());
    }

    /**
     * Searches all the investments for a symbol, and/or price range, and/or keywords in the name inputed by the user
     */
    public void search() {
        Scanner s = new Scanner(System.in);
        String symbol;
        String priceRange;
        double lowPrice = 0;
        double highPrice = 0;
        String keywords;
        boolean match = false;
        boolean found = false;
        
        System.out.print("What symbol would you like to seach for: ");
        symbol = s.nextLine();
        
        while(true)
        {
            System.out.print("What price range would you lke to search for: ");
            priceRange = s.nextLine();
            String[] prices = priceRange.split("-");
            //System.out.println(Arrays.toString(prices));
            try
            {
                //Determines which format the price range was in and set low and high price accordingly
                if(priceRange.isEmpty())
                {
                    //If no price range was entered
                    lowPrice = 0;
                    highPrice = Double.POSITIVE_INFINITY;
                }
                else if(priceRange.startsWith("-"))
                {
                    //If only a high price was entered
                    //System.out.println(prices[1]);
                    lowPrice = 0;
                    highPrice = Double.parseDouble(prices[1]);
                }
                else if(priceRange.endsWith("-"))
                {
                    //if only a low price was entered
                    lowPrice = Double.parseDouble(prices[0]);
                    highPrice = Double.POSITIVE_INFINITY;
                }
                else if(priceRange.contains("-"))
                {
                    //if both high and low were entered
                    lowPrice = Double.parseDouble(prices[0]);
                    highPrice = Double.parseDouble(prices[1]);
                }
                else
                {
                    //If only one number was entered
                    lowPrice = Double.parseDouble(priceRange);
                    highPrice = Double.parseDouble(priceRange);
                }
                if(lowPrice < 0 || highPrice < 0)
                {
                    System.out.println("Please enter a valid price");
                    continue;
                }
            }
            catch(NumberFormatException e)
            {
                System.out.println("Please enter a valid price");
                continue;
            }
            break;
        }
        
        
        //Get the keywords and split them up into any array
        System.out.print("What keyword would you like to search for: ");
        keywords = s.nextLine();
        String[] words = keywords.toLowerCase().split(" ");
        if(!words[0].isEmpty())
        {
            ArrayList<Integer> removeList = new ArrayList<>();
            ArrayList<Integer> searchList = new ArrayList<>();
            if(index.get(words[0]) == null)
            {
                System.out.println("\nNo investments were found\n");
                return;
            }
            searchList.addAll(index.get(words[0]));
            for(String word: words)
            {
                if(index.get(word) == null)
                {
                    System.out.println("\nNo investments were found\n");
                    return;
                }
                for (int x : searchList) {
                    if(!index.get(word).contains(x))
                    {
                        removeList.add(x);
                    }
                }
            }
            for(int x : removeList)
            {
                if(searchList.contains(x))  
                    searchList.remove((Object)x);
            }
            for(int x: searchList)
            {
                Investment curInvest = investments.get(x);
                if(symbol.equals(curInvest.getSymbol()) || symbol.isEmpty())
                {
                    match = curInvest.getPrice() >= lowPrice && curInvest.getPrice() <= highPrice;
                }
                else
                {
                    match = false;
                }
                if(match)
                {
                    System.out.print("\n");
                    System.out.println(curInvest.toString());
                    System.out.print("\n");
                    match = false;
                    found = true;
                }
            }
        }
        else
        {
            for(Investment curInvest : investments)
            {
                if(symbol.equals(curInvest.getSymbol()) || symbol.isEmpty())
                {
                    //if it is within the price range
                    match = curInvest.getPrice() >= lowPrice && curInvest.getPrice() <= highPrice;
                }
                else
                {
                    match = false;
                }
                if(match)
                {
                    System.out.print("\n");
                    System.out.println(curInvest.toString());
                    System.out.print("\n");
                    match = false;
                    found = true;
                }
            }
        }
        if(!found)
        {
            System.out.println("\nNo investments were found\n");
        }
    }
    
    
}

    