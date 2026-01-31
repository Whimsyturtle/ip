package turtle.core.commands;

import turtle.core.Chatbot;
import turtle.exceptions.ByeTurtleException;
import turtle.exceptions.TurtleException;

/** ByeCommand represents the command `bye` */
public class ByeCommand extends Command {

    /**
     * Terminates the Turtle Chatbot.
     *
     * @param bot Turtle Chatbot.
     * @throws TurtleException Used to trigger termination of the Turtle Chatbot.
     */
    @Override
    public void executeCommand(Chatbot bot) throws TurtleException {
        throw new ByeTurtleException();
    }

}
