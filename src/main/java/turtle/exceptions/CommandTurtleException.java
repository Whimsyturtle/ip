package turtle.exceptions;

public class CommandTurtleException extends TurtleException {

    public CommandTurtleException(String detail, String correctSyntax) {
        super(detail + "\nCorrect Syntax: " + correctSyntax);
    }

}
