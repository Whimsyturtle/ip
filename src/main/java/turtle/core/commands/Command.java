package turtle.core.commands;

import turtle.core.Chatbot;
import turtle.exceptions.CommandTurtleException;
import turtle.exceptions.TurtleException;

public abstract class Command {

    public Command(String[] sections, String correctSyntax) throws CommandTurtleException {

    }

    public abstract void executeCommand(Chatbot bot) throws TurtleException;

}
