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
     * @throws efaguy_a3.BadInputException An exception thrown when an invalid input is detected 
     */
    public Stock(String symbol, String name, String quantity, String price) throws BadInputException
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
     * @param bookVal the bookValue of the stock
     * @throws efaguy_a3.BadInputException An exception thrown when an invalid input is detected 
     */
    public Stock(String symbol, String name, String quantity, String price, String bookVal) throws BadInputException
    {
        super(symbol, name, quantity, price);
        double b;
        try
        {
            b = Double.parseDouble(bookVal);
        }
        catch(NumberFormatException ex)
        {
            throw new BadInputException("book val");
        }
        this.setBookValue(b);
    }
    
    public Stock(Investment original)
    {
        super(original);
    }
    
    @Override
    public int addQuantity(int curQuantity, String newQuantity) throws BadInputException
    {
        int q;
        try
        {
            q = Integer.parseInt(newQuantity);
        }
        catch(NumberFormatException ex)
        {
            throw new BadInputException("quantity");
        }
        q += curQuantity;
        this.setBookValue(this.getBookValue() + (this.getPrice() * (q - this.getQuantity()) + FEE));
        this.setQuantity(q);
        return q - curQuantity;
    }
    
    @Override
    public String calculatePrice(int quantity) {
        String paid = String.format("%.2f", (quantity * this.getPrice() + Stock.FEE));
        return paid;
    }
    
    @Override
    public String calculateProfit(int soldQuantity)
    {
        String profit = String.format("%.2f", ((this.getPrice() * soldQuantity) - Stock.FEE));
        return profit;
    }
    
    @Override
    public double getGain()
    {
        double curGain = ((this.getQuantity() * this.getPrice()) - Stock.FEE) - this.getBookValue();
        return curGain;
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
}
