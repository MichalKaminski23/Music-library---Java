package View;

/**
 * This class is responsible for printing error messages to the console. It
 * provides a simple method to display error messages in a standardized way.
 *
 * @author Michal Kaminski
 * @version 1.0
 */
public class MessageErrorPrinter {

    /**
     * Empty constructor for the MessageErrorPrinter class.
     */
    @SuppressWarnings("empty-statement")
    public MessageErrorPrinter() {
        ;
    }

    /**
     * Prints the provided error message to the console.
     *
     * This method takes a string representing an error message and prints it
     * out to the standard output.
     *
     * @param errorText The error message to be printed.
     */
    public void printMessage(String errorText) {
        System.out.println(errorText);
    }

}
