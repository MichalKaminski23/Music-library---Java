package Model;

/**
 * Exception class to handle invalid integer input errors. Thrown when an
 * integer value is expected, but the input is invalid or out of the acceptable
 * range.
 *
 * @author Michal Kaminski
 * @version 1.0
 */
public class InvalidIntException extends Exception {

    /**
     * Constructor for InvalidIntException. Passes the error message to the
     * superclass (Exception).
     *
     * @param message The detail message that explains the cause of the
     * exception
     */
    public InvalidIntException(String message) {
        super(message);
    }

}
