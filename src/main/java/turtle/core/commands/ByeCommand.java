package turtle.core.commands;

import turtle.core.Chatbot;
import turtle.exceptions.ByeTurtleException;
import turtle.exceptions.CommandTurtleException;
import turtle.exceptions.TurtleException;

public class ByeCommand extends Command {

    public ByeCommand(String[] sections, String correctSyntax) throws CommandTurtleException {
        super(sections, correctSyntax);
    }

    @Override
    public void executeCommand(Chatbot bot) throws TurtleException {
        throw new ByeTurtleException();
    }

}
