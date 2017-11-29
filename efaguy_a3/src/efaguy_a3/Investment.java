package efaguy_a3;

import java.util.Objects;

/**
 * A abstract parent class to stock and mutualFund
 * @author Eric
 */
public abstract class Investment {
    
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
     * @throws efaguy_a3.BadInputException An exception thrown when an invalid input is detected 
     */
    public Investment(String symbol, String name, String quantity, String price) throws BadInputException
    {
        if(symbol.isEmpty())
        {
            throw new BadInputException("symbol");
        }
        if(name.isEmpty())
        {
            throw new BadInputException("name");
        }
        int q;
        try
        {
            q = Integer.parseInt(quantity);
        }
        catch(NumberFormatException ex)
        {
            throw new BadInputException("quantity");
        }
        if(q <= 0)
        {
            throw new BadInputException("quantity");
        }
        double p;
        try
        {
            p = Double.parseDouble(price);
        }
        catch(NumberFormatException ex)
        {
            throw new BadInputException("price");
        }
        if(p <= 0)
        {
            throw new BadInputException("price");
        }
        this.symbol = symbol;
        this.name = name;
        this.quantity = q;
        this.price = p;
    }
    
    /**
     * A copy constructor to copy one investment and make other with the same values 
     * @param original The investment being copied
     */
    public Investment(Investment original)
    {
        String newSym = original.symbol;
        String newName = original.name;
        int newQuant = original.quantity;
        double newPrice = original.price;
        this.symbol = newSym;
        this.name = newName;
        this.quantity = newQuant;
        this.price = newPrice;
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
     * @return The amount of quantity that was removed
     * @throws efaguy_a3.BadInputException An exception thrown when an invalid input is detected 
     */
    public int removeQuantity(String quantity) throws BadInputException
    {
        int q;
        try
        {
            q = Integer.parseInt(quantity);
        }
        catch(NumberFormatException ex)
        {
            throw new BadInputException("quantity");
        }
        if(q <= 0)
        {
            throw new BadInputException("quantityv");
        }
        if(q > this.quantity)
        {
            throw new BadInputException("quantityt");
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
     * @throws efaguy_a3.BadInputException An exception thrown when an invalid input is detected 
     */
    public void setPrice(String price) throws BadInputException
    {
        double p;
        try
        {
            p = Double.parseDouble(price);
        }
        catch(NumberFormatException ex)
        {
            throw new BadInputException("price");
        }        
        if(p <= 0)
        {
            throw new BadInputException("price");
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
     * An abstract function to add quantity to an investment
     * @param curQuantity The current quantity of the investment 
     * @param newQuantity The quantity being added
     * @return The new quantity of the investment
     * @throws BadInputException An exception thrown when an invalid input is detected 
     */
    public abstract int addQuantity(int curQuantity, String newQuantity) throws BadInputException;
    
    /**
     * An abstract function to calculate the price of buying an investment
     * @param quantity The quantity being bought
     * @return The price of the purchase
     */
    public abstract String calculatePrice(int quantity);
    
    /**
     * An abstract function to calculate the profit from selling an investment
     * @param soldQuantity The quantity being sold
     * @return The profit of the sale
     */
    public abstract String calculateProfit(int soldQuantity);
    
    /**
     * An abstract function to calculate the gain of an investment
     * @return The gain of the investment
     */
    public abstract double getGain();
    
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
}
