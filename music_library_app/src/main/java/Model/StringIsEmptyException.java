package Model;

/**
 * Exception class to handle empty string input errors. Thrown when a string
 * input that is expected to have a value is found to be empty.
 *
 * @author Michal Kaminski
 * @version 1.0
 */
public class StringIsEmptyException extends Exception {

    /**
     * Constructor for StringIsEmptyException. Passes the error message to the
     * superclass (Exception).
     *
     * @param message The detail message that explains the cause of the
     * exception
     */
    public StringIsEmptyException(String message) {
        super(message);
    }

}
