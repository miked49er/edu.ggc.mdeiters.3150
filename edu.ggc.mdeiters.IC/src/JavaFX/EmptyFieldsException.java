package JavaFX;

/**
 * Class: EmptyFieldsException
 * @author Mike Deiters
 * @version 1.0
 * October 15, 2015
 * ITEC 3150-01
 *
 * Description: Custom Exception for empty fields
 *
 * Purpose: Create a custom exception
 */
public class EmptyFieldsException extends Exception {

    /**
     * Constructor: EmptyFieldsException
     */
    public EmptyFieldsException() {

        super();
    }

    /**
     * Constructor: EmptyFieldsException
     * @param message String
     */
    public EmptyFieldsException( String message ) {

        super(message);
    }
}
