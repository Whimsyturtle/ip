package turtle.core.commands;

import java.util.Arrays;

import turtle.core.Chatbot;
import turtle.exceptions.CommandTurtleException;
import turtle.exceptions.TurtleException;

public class FindCommand extends Command {

    private final String searchStr;

    public FindCommand(String[] sections, String correctSyntax) throws CommandTurtleException {
        if (sections.length == 1) {
            throw new CommandTurtleException("Invalid syntax", correctSyntax);
        }
        this.searchStr = String.join(" ", Arrays.copyOfRange(sections, 1, sections.length));
    }

    @Override
    public void executeCommand(Chatbot bot) throws TurtleException {
        bot.find(searchStr);
    }
}
