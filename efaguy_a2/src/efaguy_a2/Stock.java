/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package efaguy_a2;

/**
 * A child class of investment for stocks
 * @author Eric
 */
public class Stock extends Investment {
    
    public static final double FEE = 9.99;

      /**
     * Creates a stock instance
     * @param symbol the symbol of the stock
     * @param name the name of the stock
     * @param quantity how many shares are owned
     * @param price the price of the stock per share
     */
    public Stock(String symbol, String name, int quantity, double price) {
        super(symbol, name, quantity, price);
        this.setBookValue(FEE + (quantity * price));
    }

    /**
     * Creates a stock instance with a set bookValue
     * @param symbol the symbol of the stock
     * @param name the name of the stock
     * @param quantity how many shares are owned
     * @param price the price of the stock per share
     * @param bookVal the bookValue of the stock
     */
    public Stock(String symbol, String name, int quantity, double price, double bookVal) {
        super(symbol, name, quantity, price);
        this.setBookValue(bookVal);
    }
    
    /**
     * Add quantity to the stock
     * @param quantity the new quantity
     */
    public void addQuantity(int quantity)
    {
        this.setBookValue(this.getBookValue() + (this.getPrice() * (quantity - this.getQuantity()) + FEE));
        this.setQuantity(quantity);
    }
    
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
            Stock other = (Stock)otherObject;
            return this.getSymbol().equals(other.getSymbol());
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }
    
    
    
}
