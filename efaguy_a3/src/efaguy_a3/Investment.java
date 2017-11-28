/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package efaguy_a3;

import java.util.Objects;

/**
 * A parent class to stock and mutualFund
 * @author Eric
 */
public class Investment {
    
    public double FEE;
    private String symbol;
    private String name;
    private int quantity;
    private double price;
    private double bookValue;
    
    /**
     * Creates a investment instance
     * @param symbol the symbol of the investment
     * @param name the name of the investment
     * @param quantity how many units are owned
     * @param price the price of the investment per unit
     * @throws efaguy_a3.BadInput
     */
    public Investment(String symbol, String name, String quantity, String price) throws BadInput
    {
        if(symbol.isEmpty())
        {
            throw new BadInput("symbol");
        }
        if(name.isEmpty())
        {
            throw new BadInput("name");
        }
        int q;
        try
        {
            q = Integer.parseInt(quantity);
        }
        catch(NumberFormatException ex)
        {
            throw new BadInput("quantity");
        }
        if(q <= 0)
        {
            throw new BadInput("quantity");
        }
        double p;
        try
        {
            p = Double.parseDouble(price);
        }
        catch(NumberFormatException ex)
        {
            throw new BadInput("price");
        }
        if(p <= 0)
        {
            throw new BadInput("price");
        }
        this.symbol = symbol;
        this.name = name;
        this.quantity = q;
        this.price = p;
    }
    
    /**
     * Returns the symbol the respective Investment
     * @return the symbol for the Investment
     */
    public String getSymbol()
    {
        return this.symbol;
    }

    /**
     * Returns the name the respective Investment
     * @return the name for the Investment
     */
    public String getName()
    {
        return this.name;
    }
    /**
     * Returns the quantity the respective Investment
     * @return the quantity for the Investment
     */
    public int getQuantity()
    {
        return this.quantity;
    }
    /**
     * Returns the price the respective Investment
     * @return the price for the Investment
     */
    public double getPrice()
    {
        return this.price;
    }
    
    /**
     * Returns the bookValue the respective Investment
     * @return the bookValue for the Investment
     */
    public double getBookValue()
    {
        return this.bookValue;
    }
    
    /**
     *Removes units and adjust the bookValue
     * @param quantity the new number of units
     * @return 
     * @throws efaguy_a3.BadInput
     */
    public int removeQuantity(String quantity) throws BadInput
    {
        int q;
        try
        {
            q = Integer.parseInt(quantity);
        }
        catch(NumberFormatException ex)
        {
            throw new BadInput("quantity");
        }
        if(q <= 0)
        {
            throw new BadInput("quantity");
        }
        if(q > this.quantity)
        {
            throw new BadInput("quantity");
        }
        float originalQuantity = (float)this.quantity;
        float newQuantity = originalQuantity - (float)q;
        float percentLeft = newQuantity/originalQuantity;
         
        this.bookValue = this.bookValue * percentLeft;
        this.quantity = (int)newQuantity;
        return q;
    }
    
    /**
     * Sets a new price for a Investment
     * @param price the new price
     * @throws efaguy_a3.BadInput
     */
    public void setPrice(String price) throws BadInput
    {
        double p;
        try
        {
            p = Double.parseDouble(price);
        }
        catch(NumberFormatException ex)
        {
            throw new BadInput("price");
        }        
        if(p <= 0)
        {
            throw new BadInput("price");
        }
        this.price = p;
    }
    
    /**
     * Sets the bookValue of the investment
     * @param bookValue the new bookValue
     */
    public void setBookValue(double bookValue)
    {
        this.bookValue = bookValue;
    }
    
    /**
     * Sets the quantity of the investment
     * @param quantity the new quantity of the investment
     */
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
    
    /**
     * Returns the data of the Investment in formated string
     * @return a formated string of the Investment
     */
    @Override
    public String toString()
    {
        String bookVal = String.format("%.2f", bookValue);
        return "Symbol: " + symbol + "\nName: " + name + "\nQuanity: " + quantity + "\nPrice: $" + price + "\nBook Value: $" + bookVal + "\n";
    }

    /**
     * Checks if the investment if equal to an other investment
     * @param otherObject the other investment to compare
     * @return if the two investments are equal
     */
    @Override
    public boolean equals(Object otherObject)
    {
        if(otherObject == null)
        {
            return false;
        }
        else if (getClass() != otherObject.getClass())
        {
            return false;
        }
        else
        {
            Investment other = (Investment)otherObject;
            return this.symbol.equals(other.symbol);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.symbol);
        return hash;
    }
}
