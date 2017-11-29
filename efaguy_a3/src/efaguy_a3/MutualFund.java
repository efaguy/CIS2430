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
     * @throws efaguy_a3.BadInputException An exception thrown when an invalid input is detected 
     */
    public MutualFund(String symbol, String name, String quantity, String price) throws BadInputException
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
     * @throws efaguy_a3.BadInputException An exception thrown when an invalid input is detected 
     */
    public MutualFund(String symbol, String name, String quantity, String price, String bookVal) throws BadInputException
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
        this.setBookValue(this.getBookValue() + (this.getPrice() * (q - this.getQuantity())));
        this.setQuantity(q);
        return q - curQuantity;
    }
    
    @Override
    public String calculatePrice(int quantity) {
        String paid = String.format("%.2f", (quantity * this.getPrice()));
        return paid;
    }
    
    @Override
    public String calculateProfit(int soldQuantity)
    {
        String profit = String.format("%.2f", ((this.getPrice() * soldQuantity) - MutualFund.FEE));
        return profit;
    }
    
    @Override
    public double getGain()
    {
        double curGain = ((this.getQuantity() * this.getPrice()) - MutualFund.FEE) - this.getBookValue();
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
            MutualFund other = (MutualFund)otherObject;
            return this.getSymbol().equals(other.getSymbol());
        }
    }    
}
