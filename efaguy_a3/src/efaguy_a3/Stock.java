/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package efaguy_a3;

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
     * @throws efaguy_a3.BadInput
     */
    public Stock(String symbol, String name, String quantity, String price) throws BadInput
    {
        super(symbol, name, quantity, price);
        this.setBookValue(FEE + (this.getQuantity() * this.getPrice()));
    }

    /**
     * Creates a stock instance with a set bookValue
     * @param symbol the symbol of the stock
     * @param name the name of the stock
     * @param quantity how many shares are owned
     * @param price the price of the stock per share
     * @param bookVal the bookValue of th
     * @throws efaguy_a3.BadInput stock
     */
    public Stock(String symbol, String name, String quantity, String price, String bookVal) throws BadInput
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
        this.setBookValue(this.getBookValue() + (this.getPrice() * (q - this.getQuantity()) + FEE));
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
