package efaguy_a3;

/**
 * An Exception to be thrown when an invalid input is detected
 * @author Eric
 */
class BadInputException extends Exception {
    
    public BadInputException(String type)
    {
        super(type);
    }   
}
