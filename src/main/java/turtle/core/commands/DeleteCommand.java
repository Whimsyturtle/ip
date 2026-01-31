package turtle.core.commands;

import turtle.core.Chatbot;
import turtle.exceptions.CommandTurtleException;
import turtle.exceptions.TurtleException;

public class DeleteCommand extends Command {

    private final int index;

    public DeleteCommand(String[] sections, String correctSyntax) throws CommandTurtleException {
        super(sections, correctSyntax);
        if (sections.length != 2) {
            throw new CommandTurtleException("Invalid syntax", correctSyntax);
        }
        try {
            this.index = Integer.parseInt(sections[1]);
        } catch (NumberFormatException e) {
            throw new CommandTurtleException("Unable to parse <index> '" + sections[1] + "'", correctSyntax);
        }
    }

    @Override
    public void executeCommand(Chatbot bot) throws TurtleException {
        bot.delete(this.index);
    }

}
