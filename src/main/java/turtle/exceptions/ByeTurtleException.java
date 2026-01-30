package turtle.exceptions;

/** TurtleException related to terminating. */
public class ByeTurtleException extends TurtleException {

    /**
     * Creates ByeTurtleException object which is used to trigger termination of the Turtle chatbot.
     */
    public ByeTurtleException() {
        super("Exiting Turtle...");
    }

}
