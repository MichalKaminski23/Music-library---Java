package mk.models;

/**
 *
 *
 * @author Michal Kaminski
 * @version 1.0
 */
public class WrongInputException extends Exception {

    /**
     * Constructor for WrongInputException. Passes the error message to the
     * superclass (Exception).
     *
     * @param message The detail message that explains the cause of the
     * exception
     */
    public WrongInputException(String message) {
        super(message);
    }

}
