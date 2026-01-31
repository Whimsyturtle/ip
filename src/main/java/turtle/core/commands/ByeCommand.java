package turtle.core.commands;

import turtle.core.Chatbot;
import turtle.exceptions.ByeTurtleException;
import turtle.exceptions.TurtleException;

public class ByeCommand extends Command {

    @Override
    public void executeCommand(Chatbot bot) throws TurtleException {
        throw new ByeTurtleException();
    }

}
