package turtle.core.commands;

import turtle.core.Chatbot;
import turtle.exceptions.CommandTurtleException;

public class ListCommand extends Command {

    public ListCommand(String[] sections, String correctSyntax) throws CommandTurtleException {
        super(sections, correctSyntax);
    }

    @Override
    public void executeCommand(Chatbot bot) {
        bot.list();
    }

}
