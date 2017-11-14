package efaguy_a1.pkg;
/**
 * A class to represent a mutual fund purchase 
 * @author Eric
 */
public class MutualFund {
    
   public static final int FEE = 45;
    
    private String symbol;
    private String name;
    private int quantity;
    private double price;
    private double bookValue;
    
    /**
     * Create a mutualFund instance
     * @param symbol symbol of the mutual fund
     * @param name name of the fund
     * @param quantity how much of the fund is owned
     * @param price price per unit of the fund
     */
    public MutualFund(String symbol, String name, int quantity, double price)
    {
        this.symbol = symbol;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.bookValue = price * quantity;
    }
    
    /**
     * Returns the symbol of the fund
     * @return Symbol of the stock
     */
    public String getSymbol()
    {
        return this.symbol;
    }

    /**
     * Returns the name of the fund
     * @return Name of stock
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Returns how many units are owned
     * @return Quantity of the fund
     */
    public int getQuantity()
    {
        return this.quantity;
    }

    /**
     * Return the price per unit of the mutual fund
     * @return Price of the fund
     */
    public double getPrice()
    {
        return this.price;
    }

    /**
     * Returns the book value of the fund
     * @return BookValue of the fund
     */
    public double getBookValue()
    {
        return this.bookValue;
    }
    
    /**
     * Adds more units to the fund and adjust the bookValue 
     * @param quanity the new number of units
     */
    public void addQuanity(int quanity)
    {
        this.bookValue = this.bookValue + (this.price * (quantity - this.quantity));
        this.quantity = quanity;
    }

    /**
     * Removes units from the fund and adjusts the book value
     * @param quantity the new number of units
     */
    public void removeQuantity(int quantity)
    {
        this.bookValue = this.bookValue * (quantity/this.quantity);
        this.quantity = quantity;
    }

    /**
     * Sets the price of the mutual fund
     * @param price the new price
     */
    public void setPrice(double price)
    {
        this.price = price;
    }
    
    /**
     * Returns the data of the fund in a formated string
     * @return a formated string of the fund
     */
    @Override
    public String toString()
    {
        return "Symbol: " + symbol + "\nName: " + name + "\nQuanity: " + quantity + "\nPrice: " + price + "\nBook Value: " + bookValue;
    }
   
}
