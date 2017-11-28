/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package efaguy_a3;

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
     * @throws efaguy_a3.BadInput
     */
    public MutualFund(String symbol, String name, String quantity, String price) throws BadInput
    {
        super(symbol, name, quantity, price);
        this.setBookValue(this.getQuantity() * this.getPrice());
    }

    
    /**
     * Creates a mutualFund instance with a set bookValue
     * @param symbol the symbol of the mutualFund
     * @param name the name of the mutualFund
     * @param quantity how many units are owned
     * @param price the price of the mutualFund per unit
     * @param bookVal the bookValue of the mutualFund
     * @throws efaguy_a3.BadInput
     */
    public MutualFund(String symbol, String name, String quantity, String price, String bookVal) throws BadInput
    {
        super(symbol, name, quantity, price);
        double b;
        try
        {
            b = Double.parseDouble(bookVal);
        }
        catch(NumberFormatException ex)
        {
            throw new BadInput("book val");
        }
        this.setBookValue(b);
    }
    
    /**
     * Add quantity to the stock
     * @param curQuantity
     * @param newQuantity
     * @return 
     * @throws efaguy_a3.BadInput
     */
    public int addQuantity(int curQuantity, String newQuantity) throws BadInput
    {
        int q;
        try
        {
            q = Integer.parseInt(newQuantity);
        }
        catch(NumberFormatException ex)
        {
            throw new BadInput("quantity");
        }
        q += curQuantity;
        this.setBookValue(this.getBookValue() + (this.getPrice() * (q - this.getQuantity())));
        this.setQuantity(q);
        return q - curQuantity;
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
