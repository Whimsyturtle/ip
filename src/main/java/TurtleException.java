public class TurtleException extends Exception {

    public TurtleException(String detail, String correctSyntax) {
        super(detail + "\nCorrect Syntax: " + correctSyntax);
    }

}
