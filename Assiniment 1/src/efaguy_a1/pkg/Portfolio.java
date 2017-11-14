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
public class Portfolio {

   
    private ArrayList<Stock> stocks;
    private ArrayList<MutualFund> funds;
    
    /**
     * Create a portfolio instance
     */
    public Portfolio()
    {
        stocks =  new ArrayList<>();
        funds =  new ArrayList<>();
    }
    /**
     * Buys a stock or mutual fund for the price and quantity defined by the user
     */
    public void buy()
    {
        Scanner s = new Scanner(System.in);
        String choice;
        String symbol;
        String name;
        int quantity;
        int i;
        double price;
        boolean found = false;
        
        //Ask user for input and checks if it is valid
        System.out.println("Do you want to buy a stock or mutual fund?");
        choice = s.nextLine();
        while(!choice.toLowerCase().equals("stock") && !choice.toLowerCase().equals("mutual fund") && !choice.toLowerCase().equals("s") && !choice.toLowerCase().equals("m")) {
            System.out.println("Please either stock or mutual fund");
            choice = s.nextLine(); 
        }
        System.out.print("Symbol: ");
        symbol = s.nextLine();
        if(choice.toLowerCase().equals("stock") || choice.toLowerCase().equals("s"))
        {
            for(i = 0;i < stocks.size(); i++)
            {
                //If there is another stock with the same symbol
                if(stocks.get(i).getSymbol().equals(symbol))
                {
                    found = true;
                    break;
                }
            }
            if(found)
            {
                //Tell them how may shares they own and ask for how many they want to buy
                System.out.println("You already own " + stocks.get(i).getQuantity() + " shares.");
                System.out.print("How many more would you like to buy: ");
                quantity = s.nextInt();
                s.nextLine();
                System.out.print("What is the price per share: ");
                price = s.nextDouble();
                //Update the price of the stock and add the new shares to the stock
                stocks.get(i).setPrice(price);
                stocks.get(i).addQuantity(stocks.get(i).getQuantity() + quantity);
                //Output how much they paid and how many shares they own
                String paid = String.format("%.2f", (quantity * price) + Stock.FEE);
                System.out.println("You paid $" + paid + " and own " + stocks.get(i).getQuantity() + " shares");
            } 
            //If it is a new stock
            else
            {
                System.out.print("What is the name of the stock: ");
                name = s.nextLine();
                System.out.print("How many do you want to buy: ");
                quantity = s.nextInt();
                s.nextLine();
                System.out.print("What is the price per share: ");
                price = s.nextDouble();
                //Add the new stock to the list
                stocks.add(new Stock(symbol, name, quantity, price));
                //Output how much they paid and how many shares they own
                String paid = String.format("%.2f", (quantity * price) + Stock.FEE);
                System.out.println("You paid $" + paid + " and own " + quantity + " shares");
            }
        }
        else if (choice.toLowerCase().equals("mutual fund") || choice.toLowerCase().equals("m"))
        {
            for(i = 0;i < funds.size(); i++)
            {
                if(funds.get(i).getSymbol().equals(symbol))
                {
                    //If there is already a fund with the same symbol
                    found = true;
                    break;
                }
            }
            if(found)
            {
                System.out.println("You already own " + funds.get(i).getQuantity() + " shares.");
                System.out.print("How many more would you like to buy: ");
                quantity = s.nextInt();
                s.nextLine();
                System.out.print("What is the price per unit: ");
                price = s.nextDouble();
                funds.get(i).setPrice(price);
                funds.get(i).addQuanity(funds.get(i).getQuantity() + quantity);
                String paid = String.format("%.2f", (quantity * price));
                System.out.println("You paid $" + paid + " and own " + funds.get(i).getQuantity() + " units");
            }
            //If it is a new fund
            else
            {
                System.out.print("What is the name of the fund: ");
                name = s.nextLine();
                System.out.print("How many do you want to buy: ");
                quantity = s.nextInt();
                s.nextLine();
                System.out.print("What is the price per unit: ");
                price = s.nextDouble();
                funds.add(new MutualFund(symbol, name, quantity, price));
                String paid = String.format("%.2f", price * quantity);
                System.out.println("You paid $" + paid + " and own " + funds.get(i).getQuantity() + " units"); 
            }
        }
    }
    
    /**
     * Sells shares of a stock or units of a fund that are already owned  
     */
    public void sell()
    {
        Scanner s = new Scanner(System.in);
        String choice;
        String symbol;
        int i;
        int quantity;
        int newQuantity;
        double price;
        
        ////Ask user for input and checks if it is valid
        System.out.println("Do you want to sell a stock or mutual fund?");
        choice = s.nextLine();
        while(!choice.toLowerCase().equals("stock") && !choice.toLowerCase().equals("mutual fund") && !choice.toLowerCase().equals("s") && !choice.toLowerCase().equals("m")) {
            System.out.println("Please either stock or mutual fund");
            choice = s.nextLine(); 
        }
    
        if(choice.toLowerCase().equals("stock") || choice.toLowerCase().equals("s"))
        {
            //If they don't own any stocks
            if(stocks.isEmpty())
            {
                System.out.println("You do not own any stocks");
            }else 
            {
                System.out.print("Symbol: ");
                symbol = s.nextLine();
                loop: while(true)
                {
                    //Find the stock they want to sell
                    for(i = 0;i < stocks.size(); i++)
                    {   
                        System.out.println(symbol);
                        System.out.println(stocks.get(i).getSymbol());
                        if(stocks.get(i).getSymbol().equals(symbol))
                        {
                            break loop;
                        }
                    }
                    //If they entered a symbol thay doesn't correspond to a stuck
                    System.out.println("You do not own a stock with that symbol. Please enter a new symbol");
                    symbol = s.nextLine();
                }
                //Tell them how many they own and ask how many they want to sell
                System.out.println("You own " + stocks.get(i).getQuantity() + " shares");
                System.out.print("How many do you want to sell: ");
                quantity = s.nextInt();
                s.nextLine();
                while(stocks.get(i).getQuantity() < quantity)
                {
                    System.out.println("You do not own that many shares. Please enter a number less than " + stocks.get(i).getQuantity());
                    quantity = s.nextInt();
                    s.nextLine();
                }
                System.out.print("What is the price: ");
                price = s.nextDouble();  
                stocks.get(i).setPrice(price);
        
                newQuantity = stocks.get(i).getQuantity() - quantity;
                if(newQuantity == 0)
                {
                    //If they sold all there shares remove it from the list
                    stocks.remove(i);
                }
                else 
                {
                    //Remove the shares that were sold
                    stocks.get(i).removeQuantity(newQuantity);
                }
                //Tell them how much they made and how many share they have left
                String profit = String.format("%.2f", ((price * quantity) - Stock.FEE));
                System.out.println("You received $" + profit + " and have " + newQuantity + " shares left");
            }
        }
        else if (choice.toLowerCase().equals("mutual fund") || choice.toLowerCase().equals("m"))
        {
            //If they don't own any funds
            if(funds.isEmpty())
            {
                System.out.println("You do not own any mutual funds");
            }
            else
            {
                System.out.print("Symbol: ");
                symbol = s.nextLine();
                loop: while(true)
                {
                     for(i = 0;i < funds.size(); i++)
                    {
                        if(funds.get(i).getSymbol().equals(symbol))
                        {
                            break loop;
                        }
                    }
                    System.out.println("You do not own a mutual fund with that symbol. Please enter a new symbol");
                    symbol = s.nextLine();
                }
                System.out.println("You own " + funds.get(i).getQuantity() + " units");
                System.out.print("How many do you want to sell: ");
                quantity = s.nextInt();
                s.nextLine();
                while(funds.get(i).getQuantity() < quantity)
                {
                    System.out.println("You do don't own that many units. Please enter a number less than " + funds.get(i).getQuantity());
                    quantity = s.nextInt();
                    s.nextLine();
                }
                System.out.print("Price: ");
                price = s.nextDouble();  
        
                newQuantity = funds.get(i).getQuantity() - quantity;
                if(newQuantity == 0)
                {
                    funds.remove(i);
                }
                else 
                {
                    funds.get(i).removeQuantity(newQuantity);
                }
                String profit = String.format("%.2f", ((price * quantity) - MutualFund.FEE));
                System.out.println("You received $" + profit + " and have " + newQuantity + " units left");
            }
        }
    }
    
    /**
     * Ask the user for new prices for all investment and updates them
     */
    public void update()
    {
        double price;
        Scanner s = new Scanner(System.in);
        //For all the stocks
        for(int i = 0;i < stocks.size();i++)
        {
            //Tell them the current price
            String curPrice = String.format("%.2f", stocks.get(i).getPrice());
            System.out.println("The current price for " + stocks.get(i).getSymbol() + " is $" + curPrice + " per share.");
            System.out.print("What is the new price: ");
            price = s.nextDouble();
            //Set the new price
            stocks.get(i).setPrice(price);
        }
        //For all the funds
        for(int i = 0;i < funds.size();i++)
        {
            String curPrice = String.format("%.2f", funds.get(i).getPrice());
            System.out.println("The current price for " + funds.get(i).getSymbol() + " is $" + curPrice + " per unit");
            System.out.print("What is the new price: ");
            price = s.nextDouble();
            funds.get(i).setPrice(price);
        }
    }
    
    /**
     * Calculate the profit that would be many if all the investment were sold at the current prices
     */
    public void getGain()
    {
        double totalGain = 0;
        double curGain;
        //For all the stocks
        for(int i = 0;i < stocks.size();i++)
        {
            //Caculate the gain for the stock then add it to the total gain
            curGain = ((stocks.get(i).getQuantity()* stocks.get(i).getPrice()) - Stock.FEE) - stocks.get(i).getBookValue();
            totalGain += curGain;
        }
        //For all the funds
        for(int i = 0;i < funds.size();i++)
        {
            curGain = ((funds.get(i).getQuantity()* funds.get(i).getPrice()) - MutualFund.FEE) - funds.get(i).getBookValue();
            totalGain += curGain;
        }
        String gain = String.format("%.2f", totalGain);
        System.out.println("The total gain of your investment portfolio is $" + gain);
    }
    
    /**
     * Searches all the investments for a symbol, and/or price range, and/or keywords in the name inputed by the user
     */
    public void search()
    {
        Scanner s = new Scanner(System.in);
        String symbol;
        String priceRange;
        double lowPrice = 0;
        double highPrice = 0;
        String keywords;
        boolean matches = false;
        
        System.out.print("What symbol would you like to seach for: ");
        symbol = s.nextLine();
        System.out.print("What price range would you lke to search for: ");
        priceRange = s.nextLine();
        //Determines which format the price range was in and set low and high price accordingly
        String[] prices = priceRange.split("-");
        if(priceRange.isEmpty())
        {
            //If no price range was entered
            lowPrice = -1;
            highPrice = Double.POSITIVE_INFINITY;
        }
        else if(priceRange.startsWith("-"))
        {
            //If only a high price was entered
            lowPrice = -1;
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
        //Get the keywords and split them up into any array
        System.out.print("What keyword would you like to search for: ");
        keywords = s.nextLine();
        String[] words = keywords.split("\\s+");
        //For all the stocks
        for (Stock curStock: stocks)
        {
            //If the symbol match or no symbol was entered
            if(symbol.equals(curStock.getSymbol()) || symbol.isEmpty())
            {
                //if it is within the price range
                if(curStock.getPrice() >= lowPrice && curStock.getPrice() <= highPrice)
                {
                    for(String word: words)
                    {
                        //Check if each keyword is in the name or is no keywords were entered
                        if(curStock.getName().toLowerCase().contains(word.toLowerCase()) || word.isEmpty())
                        {
                            matches = true;
                        }
                        else
                        {
                            matches = false;
                            break;
                        }
                    }
                }
            }
            //If the stock matches print it
            if(matches)
                System.out.println(curStock.toString());
            matches = false;
        }
        //For all the funds
        for (MutualFund curFund : funds) {
            //If the symbol match or no symbol was entered
            if(symbol.equalsIgnoreCase(curFund.getSymbol()) || symbol.isEmpty())
            {
                //if it is within the price range
                if(curFund.getPrice() >= lowPrice && curFund.getPrice() <= highPrice)
                {
                    for(String word: words)
                    {
                       //Check if each keyword is in the name or is no keywords were entered
                        if(curFund.getName().contains(word.toLowerCase()) || word.isEmpty())
                        {
                            matches = true;
                        }
                        else
                        {
                            matches = false;
                            break;
                        }
                    }
                }
            }
            //If the fund matches print it
            if(matches)
                System.out.println(curFund.toString());
        }
    }
}