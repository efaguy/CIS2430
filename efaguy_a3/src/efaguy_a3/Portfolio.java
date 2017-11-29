package efaguy_a3;

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
     * @return A string detailing the result of the loading
     */
   public String load(String filename)
    {
        String type;
        String symbol;
        String name;
        String quantity;
        String price;
        String bookVal;
        
        try
        {
            FileReader fw = new FileReader(filename);
            BufferedReader bw = new BufferedReader(fw);
            while(true)
            {
                type = bw.readLine();
                if(type == null)
                {
                    bw.close();
                    if(investments.isEmpty())
                    {
                        return "File is empty\n";
                    }
                    return "Investments loaded from file.\n";
                }
                symbol = bw.readLine();
                
                name = bw.readLine();
                
                quantity = bw.readLine();
                
                
                price = bw.readLine();
                
                
                bookVal = bw.readLine();
                
                try
                {
                    if(type.equalsIgnoreCase("stock"))
                    {
                        investments.add(new Stock(symbol, name, quantity, price, bookVal));
                    }
                    else if (type.equalsIgnoreCase("mutual fund"))
                    {
                        investments.add(new MutualFund(symbol, name, quantity, price, bookVal));
                    }
                }
                catch(BadInputException e)
                {
                    index.clear();
                    investments.clear();
                    return "Error reading file. No investments loaded\n" + e.getMessage();
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
        catch (IOException e)
        {
            index.clear();
            investments.clear();
            return "Error reading file. No investments loaded.\n";
        }
    }
    
    /**
     * Saves all current investments to a text file
     * @param filename the name of the file to save the investments to
     * @return A string detailing the results of the saving
     **/
    public String save(String filename)
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
            bw.close();
            return "\nInvestments saved to file.\n";
        }
        catch (FileNotFoundException ex) 
        {
           return "Unable to find file with that name.\n";
        } 
        catch (IOException ex) 
        {
           return "Error writing to file.\n";
        }
    }
    
    /**
     * Buys a investments for the price and quantity defined by the user
     * @param type Type of investment being bought
     * @param symbol Symbol of investment
     * @param name Name of investment
     * @param quantity Quantity being bought
     * @param price Price being bought at
     * @return A string detailing the results of the buying
     */
    public String buy(String type, String symbol, String name, String quantity, String price)
    {
        boolean found = false;
        int i;
        
        //Ask user for input and checks if it is valid
        for(i = 0;i < investments.size(); i++)
        {
            //If there is another investmemt with the same symbol
            if(investments.get(i).getSymbol().equals(symbol))
            {
                if(investments.get(i) instanceof Stock)
                {
                    if(type.equalsIgnoreCase("stock"))
                    {
                        found = true;
                        break;
                    }
                    else
                    {
                        return "You already own a stock with that symbol.";
                    }
                }
                else if(investments.get(i) instanceof MutualFund)
                {
                    if(type.equalsIgnoreCase("mutual fund"))
                    {
                        found = true;
                        break;
                    }
                    else
                    {
                        return "You already own a mutual fund with that symbol.";
                    }
                }
            }   
        }
        if(found)
        {
            //Update the price of the investment and add the new shares to the investment
            try
            {
                investments.get(i).setPrice(price);
            
                int addQuantity = investments.get(i).addQuantity(investments.get(i).getQuantity(), quantity);
                //Output how much they paid and how many shares they own
                String paid = investments.get(i).calculatePrice(addQuantity);
                return "You paid $" + paid + " and own " + investments.get(i).getQuantity() + " units\n" + investments.get(i).toString();
                
            }
            catch(BadInputException e)
            {
                return "Invalid " + e.getMessage() + " input.";
            }
        }
        //If it is a new stock
        else
        {
            if(type.equalsIgnoreCase("stock"))
            {
                Stock temp;
                try
                {
                    //Add the new stock to the list
                    temp = new Stock(symbol, name, quantity, price);
                    investments.add(temp);
                }
                catch(BadInputException e)
                {
                    return "Invalid " + e.getMessage() + " input.";
                }
            }
            else if(type.equalsIgnoreCase("mutual fund"))
            {
                MutualFund temp;
                try
                {
                    temp = new MutualFund(symbol, name, quantity, price);
                    investments.add(temp);
                }
                catch(BadInputException e)
                {
                    return "Invalid " + e.getMessage() + " input.";
                }
                
                 
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
            String paid;
            Investment temp = investments.get(investments.size() - 1);
            paid = temp.calculatePrice(temp.getQuantity());
            return "You paid $" + paid + " and own " + quantity + " units\n" + temp.toString();
        }
    }
    
    /**
     * Sells shares of a stock or units of a fund that are already owned  
     * @param symbol Symbol of investment being sold
     * @param quantity Quantity being sold
     * @param price Price being sold for
     * @return A string detailing the results of the selling
     */
    public String sell(String symbol, String quantity, String price)
    {
        //If they don't own any stocks
        int i;
        boolean found = false;
        if(investments.isEmpty())
        {
            return "You do not own any investments";
        }


        //Find the stock they want to sell
        for(i = 0;i < investments.size(); i++)
        {   
            if(investments.get(i).getSymbol().equals(symbol))
            {
                found = true;
                break;
            }
        }
        if(!found)
        {
            //If they entered a symbol thay doesn't correspond to a stuck
            return "You do not own a investment with that symbol. Please enter a new symbol";
        }
        int soldQuantity;
        try
        {
            investments.get(i).setPrice(price);
        }
        catch(BadInputException e)
        {
            return "Invalid " + e.getMessage() + " input.";
        }
        try 
        {
            soldQuantity = investments.get(i).removeQuantity(quantity);
        } 
        catch (BadInputException ex) 
        {
            switch (ex.getMessage()) {
                case "quantityv":
                    return "Quantity must be greater than zero.";
                case "quantityt":
                    return "You do not own that many units.";
                default:
                    return "Invalid " + ex.getMessage() + " input";
            }
        }
        String output;
        //Tell them how much they made and how many shares they have left
        String profit = investments.get(i).calculateProfit(soldQuantity);
        output = "You received $" + profit + " and have " + investments.get(i).getQuantity() + " shares left\n" + investments.get(i).toString();
        if(investments.get(i).getQuantity() == 0)
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
        return output;
    }
    
    /**
     * Updates the price of an investment
     * @param posn Index of the investment being update
     * @param price The new price of the investment
     * @return A string detailing the results of the update
     */
    public String update(int posn, String price)
    {
        try 
        {
            investments.get(posn).setPrice(price);
        }
        catch (BadInputException ex) 
        {
            return "Invalid " + ex.getMessage() + " input\n";
        }
        return "Price changed to $" + price + "\n" + investments.get(posn).toString();
    }
    /**
     * Calculate the profit that would be many if all the investment were sold at the current prices
     * @return A array containing the individual gains of all investments and the total gain
     */
    public String[] getGain()
    {
        double totalGain = 0;
        double curGain;
        String gain = "";
        String temp;
        //For all the investments
        for(int i = 0;i < investments.size();i++)
        {
            //Caculate the gain for the investments then add it to the total gain
            curGain = investments.get(i).getGain();
            temp = String.format("%.2f", curGain);
            gain += "The gain of "  + investments.get(i).getSymbol() + " is $" + temp + "\n";
            totalGain += curGain;
        }
        String stotalGain = String.format("$%.2f", totalGain);
        String[] output = {gain, stotalGain};
        //System.out.println(index.toString());
        return output;
    }

    /**
     * Searches all the investments for a symbol, and/or price range, and/or keywords in the name inputed by the user
     * @param symbol The symbol being searched
     * @param keywords The keywords being searched
     * @param lowPrice The low price
     * @param highPrice The high price
     * @return A string detailing the results of the search 
     */
    public String search(String symbol, String keywords, String lowPrice, String highPrice) {
        boolean match;
        boolean found = false;
        double hPrice;
        double lPrice;
        String output = "";
        

        if(lowPrice.isEmpty())
        {
            lPrice = 0;
        }
        else
        {
            try
            {
                lPrice = Double.parseDouble(lowPrice);
            }
            catch(NumberFormatException e)
            {
                return "Invalid low price input\n";
            }
        }
        if(highPrice.isEmpty())
        {
            hPrice = Double.POSITIVE_INFINITY;
        }
        else
        {
            try
            {
                hPrice = Double.parseDouble(highPrice);
            }
            catch(NumberFormatException e)
            {
                return "Invalid high price input\n";
            }
        }
        if(hPrice < 0 || lPrice < 0)
        {
            return "Prices myst be higher than zero.\n";        
        }
        if(lPrice > hPrice)
        {
            return "Low price must be lower than high price.\n";
        }
        
        //Get the keywords and split them up into any array
        String[] words = keywords.toLowerCase().split(" ");
        if(!words[0].isEmpty())
        {
            ArrayList<Integer> removeList = new ArrayList<>();
            ArrayList<Integer> searchList = new ArrayList<>();
            if(index.get(words[0]) == null)
            {
                return "No investments were found\n";
            }
            searchList.addAll(index.get(words[0]));
            for(String word: words)
            {
                if(index.get(word) == null)
                {
                    return "No investments were found\n";
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
                    match = curInvest.getPrice() >= lPrice && curInvest.getPrice() <= hPrice;
                }
                else
                {
                    match = false;
                }
                if(match)
                {
                    output += curInvest.toString() + "\n";
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
                    match = curInvest.getPrice() >= lPrice && curInvest.getPrice() <= hPrice;
                }
                else
                {
                    match = false;
                }
                if(match)
                {
                    output += curInvest.toString() + "\n";
                    found = true;
                }
            }
        }
        if(!found)
        {
            return "No investments were found\n";
        }
        return output;
    }
    
    /**
     * A function for converting an investment to a string
     * @return A formating string representation of the investment
     */
    @Override
    public String toString()
    {
        String port = "";
        for(Investment cur: investments)
        {
            port += cur.toString();
        }
        return port;
    }
    
    /**
     * Returns the symbols of all the investments
     * @return The symbols of all the investments
     */
    public ArrayList<String> getSymbols()
    {
        ArrayList<String> symbols = new ArrayList<>();
        for(Investment curInvest : investments)
        {
            symbols.add(curInvest.getSymbol());
        }
        
        return symbols;
    }
    
    /**
     * Returns the names of all the investments
     * @return The names of all the investments
     */
    public ArrayList<String> getNames()
    {
        ArrayList<String> names = new ArrayList<>();
        for(Investment curInvest : investments)
        {
            names.add(curInvest.getName());
        }
        
        return names;
    }
    
    /**
     * Returns the prices of all the investments
     * @return The prices of all the investments
     */
    public ArrayList<Double> getPrices()
    {
        ArrayList<Double> prices = new ArrayList<>();
        for(Investment curInvest : investments)
        {
            prices.add(curInvest.getPrice());
        }
        
        return prices;   
    }
    
    /**
     * Returns the quantities of all the investments
     * @return The quantities of all the investments
     */
    public ArrayList<Integer> getQuantities()
    {
        ArrayList<Integer> quantities = new ArrayList<>();
        for(Investment curInvest : investments)
        {
            quantities.add(curInvest.getQuantity());
        }
        
        return quantities;   
    }
    
     /**
     * Returns the book values of all the investments
     * @return The book values of all the investments
     */
    public ArrayList<Double> getBookVals()
    {
        ArrayList<Double> bookVals = new ArrayList<>();
        for(Investment curInvest : investments)
        {
            bookVals.add(curInvest.getBookValue());
        }
        
        return bookVals;   
    }
    
}

    