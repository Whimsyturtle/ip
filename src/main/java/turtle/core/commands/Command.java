package turtle.core.commands;

import turtle.core.Chatbot;
import turtle.exceptions.TurtleException;

public abstract class Command {

    public abstract void executeCommand(Chatbot bot) throws TurtleException;

}
