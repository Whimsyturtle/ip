package turtle.core.commands;

import turtle.core.Chatbot;
import turtle.exceptions.CommandTurtleException;
import turtle.exceptions.TurtleException;

/** UnmarkCommand represents the command `unmark &lt;index&gt;` */
public class UnmarkCommand extends Command {

    private final int index;

    /**
     * Parses the unmark command and extracts the specified index.
     *
     * @param sections Command sections.
     * @param correctSyntax Command syntax `unmark &lt;index&gt;`.
     * @throws CommandTurtleException If command syntax is malformed.
     */
    public UnmarkCommand(String[] sections, String correctSyntax) throws CommandTurtleException {
        if (sections.length != 2) {
            throw new CommandTurtleException("Invalid syntax", correctSyntax);
        }
        try {
            this.index = Integer.parseInt(sections[1]);
        } catch (NumberFormatException e) {
            throw new CommandTurtleException("Unable to parse <index> '" + sections[1] + "'", correctSyntax);
        }
    }

    /**
     * Unmarks the task at the specified index in the Turtle Chatbot's task list.
     *
     * @param bot Turtle Chatbot.
     * @throws TurtleException If the specified index is invalid.
     */
    @Override
    public void executeCommand(Chatbot bot) throws TurtleException {
        bot.unmark(this.index);
    }

}
