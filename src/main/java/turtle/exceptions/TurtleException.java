package turtle.exceptions;

/** Base Exception class for Turtle Chatbot. */
public class TurtleException extends Exception {

    /**
     * Creates TurtleException object which represents an error related to the Turtle Chatbot.
     *
     * @param message Error message.
     */
    public TurtleException(String message) {
        super(message);
    }

}
