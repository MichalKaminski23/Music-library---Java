package Model;

/**
 * Exception class to handle invalid date format errors. Thrown when the input
 * date format does not match the expected pattern.
 *
 * @author Michal Kaminski
 * @version 1.0
 */
public class InvalidDateFormatException extends Exception {

    /**
     * Constructor for InvalidDateFormatException. Passes the error message to
     * the superclass (Exception).
     *
     * @param message The detail message that explains the cause of the
     * exception
     */
    public InvalidDateFormatException(String message) {
        super(message);
    }

}
