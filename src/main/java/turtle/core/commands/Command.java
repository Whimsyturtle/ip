package turtle.core.commands;

import turtle.core.Chatbot;
import turtle.exceptions.TurtleException;

/** Base Command class for the Turtle Chatbot. */
public abstract class Command {

    /**
     * Executes the command on the given chatbot.
     *
     * @param bot Turtle Chatbot.
     * @throws TurtleException If the command results in a TurtleException.
     */
    public abstract void executeCommand(Chatbot bot) throws TurtleException;

}
