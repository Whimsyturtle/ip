package turtle.core.commands;

import java.util.Arrays;

import turtle.core.Chatbot;
import turtle.exceptions.CommandTurtleException;

/** FindCommand represents the command `find &lt;search_str&gt;` */
public class FindCommand extends Command {

    private final String searchStr;

    /**
     * Parses the find command and extracts the search string.
     *
     * @param sections Command sections.
     * @param correctSyntax Command syntax `find &lt;search_str&gt;`.
     * @throws CommandTurtleException If command syntax is malformed.
     */
    public FindCommand(String[] sections, String correctSyntax) throws CommandTurtleException {
        if (sections.length == 1) {
            throw new CommandTurtleException("Invalid syntax", correctSyntax);
        }
        this.searchStr = String.join(" ", Arrays.copyOfRange(sections, 1, sections.length));
    }

    /**
     * Finds all tasks containing the specified search string in the Turtle Chatbot's task list.
     *
     * @param bot Turtle Chatbot.
     */
    @Override
    public void executeCommand(Chatbot bot) {
        bot.find(searchStr);
    }
}
