package efaguy_a1.pkg;


/**
 * A class to represent a stock purchase 
 * @author Eric
 */
public class Stock {
    
    public static final double FEE = 9.99;
    
    private String symbol;
    private String name;
    private int quantity;
    private double price;
    private double bookValue;
    
    /**
     * Create a stock instance
     * @param symbol the symbol of the stock
     * @param name the name of the stock
     * @param quantity how many shares are owned
     * @param price the price of the stock per share
     */
    public Stock(String symbol, String name, int quantity, double price)
    {
        this.symbol = symbol;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.bookValue = FEE + (quantity * price);
    }
    
    /**
     * Returns the symbol the respective stock
     * @return the symbol for the stock
     */
    public String getSymbol()
    {
        return this.symbol;
    }

    /**
     * Returns the name the respective stock
     * @return the name for the stock
     */
    public String getName()
    {
        return this.name;
    }
    /**
     * Returns the quantity the respective stock
     * @return the quantity for the stock
     */
    public int getQuantity()
    {
        return this.quantity;
    }
    /**
     * Returns the price the respective stock
     * @return the price for the stock
     */
    public double getPrice()
    {
        return this.price;
    }
    /**
     * Returns the bookValue the respective stock
     * @return the bookValue for the stock
     */
    public double getBookValue()
    {
        return this.bookValue;
    }
    
    /**
     * Add shares quantity and adjust the bookValue 
     * @param quantity the new number of shares
     */
    public void addQuantity(int quantity)
    {
        this.bookValue = this.bookValue + (this.price * (quantity - this.quantity) + FEE);
        this.quantity = quantity;
    }

    /**
     *Removes shares and adjust the bookValue
     * @param quantity the new number of shares
     */
    public void removeQuantity(int quantity)
    {
        float newQuantity = (float)quantity;
        float originalQuantity = (float)this.quantity;
        float percentLeft = newQuantity/originalQuantity;
        System.out.println(percentLeft);
         
        this.bookValue = this.bookValue * percentLeft;
        this.quantity = quantity;
    }
    
    /**
     * Sets a new price for a stock
     * @param price the new price
     */
    public void setPrice(double price)
    {
        this.price = price;
    }
    
    
    /**
     * Returns the data of the stock in formated string
     * @return a formated string of the stock
     */
    @Override
    public String toString()
    {
        String bookVal = String.format("%.2f", bookValue);
        return "Symbol: " + symbol + "\nName: " + name + "\nQuanity: " + quantity + "\nPrice: " + price + "\nBook Value: " + bookVal;
    }
    
    
}
