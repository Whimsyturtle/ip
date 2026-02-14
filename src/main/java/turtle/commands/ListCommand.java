package turtle.commands;

import java.util.Objects;

import turtle.core.Chatbot;
import turtle.exceptions.CommandTurtleException;

/** ListCommand represents the command `list [sort]` */
public class ListCommand extends Command {

    private final boolean shouldSort;

    /**
     * Parses the list command and extracts the list options (if any).
     *
     * @param sections Command sections.
     * @param correctSyntax Command syntax `list [sort]`.
     * @throws CommandTurtleException If command syntax is malformed.
     */
    public ListCommand(String[] sections, String correctSyntax) throws CommandTurtleException {
        assert sections != null;
        assert correctSyntax != null;
        if (sections.length > 2) {
            throw new CommandTurtleException("Invalid syntax", correctSyntax);
        }
        if (sections.length == 1) {
            this.shouldSort = false;
            return;
        }
        if (!Objects.equals(sections[1], "sort")) {
            throw new CommandTurtleException("Unknown list option '" + sections[1] + "'", correctSyntax);
        }
        this.shouldSort = true;
    }

    /**
     * Lists all tasks stored in the Turtle Chatbot's task list.
     *
     * @param bot Turtle Chatbot.
     */
    @Override
    public void executeCommand(Chatbot bot) {
        assert bot != null;
        bot.list(this.shouldSort);
    }

    @Override
    public String toString() {
        return "ListCommand{shouldSort=" + this.shouldSort + "}";
    }

}
