/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package efaguy_a2;

/**
 * A child class of investment for mutual funds
 * @author Eric
 */
public class MutualFund extends Investment {
    
    public static final int FEE = 45;

    /**
     * Create a mutualFund instance
     * @param symbol the symbol of the mutualFund
     * @param name the name of the mutualFund
     * @param quantity how many units are owned
     * @param price the price of the mutualFund per units
     */
    public MutualFund(String symbol, String name, int quantity, double price) {
        super(symbol, name, quantity, price);
        this.setBookValue(price * quantity);
    }

    
    /**
     * Creates a mutualFund instance with a set bookValue
     * @param symbol the symbol of the mutualFund
     * @param name the name of the mutualFund
     * @param quantity how many units are owned
     * @param price the price of the mutualFund per unit
     * @param bookVal the bookValue of the mutualFund
     */
    public MutualFund(String symbol, String name, int quantity, double price, double bookVal) {
        super(symbol, name, quantity, price);
        this.setBookValue(bookVal);
    }
    
    /**
     * Add quantity to the stock
     * @param quantity the new quantity
     */
    public void addQuantity(int quantity)
    {
        this.setBookValue(this.getBookValue() + (this.getPrice() * (quantity - this.getQuantity())));
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
            MutualFund other = (MutualFund)otherObject;
            return this.getSymbol().equals(other.getSymbol());
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }
    
    
    
}
