package mk.musiclibrarygui.models;

/**
 * The WrongInputException class is a custom exception that indicates an invalid
 * input has been provided. It extends the Exception class.
 *
 * @author Michal Kaminski
 * @version 2.0
 */
public class WrongInputException extends Exception {

    /**
     * Constructs a new WrongInputException with the specified detail message.
     * This message is passed to the superclass (Exception).
     *
     * @param message The detail message that explains the cause of the
     * exception
     */
    public WrongInputException(String message) {
        super(message);
    }

}
