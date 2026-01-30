package turtle.exceptions;

/** TurtleException related to commands. */
public class CommandTurtleException extends TurtleException {

    /**
     * Creates CommandTurtleException object which represents an error related to Turtle Chatbot commands.
     *
     * @param detail Specific detail about the error.
     * @param correctSyntax Correct syntax of the command.
     */
    public CommandTurtleException(String detail, String correctSyntax) {
        super(detail + "\nCorrect Syntax: " + correctSyntax);
    }

}
