package turtle.core;

import turtle.commands.ByeCommand;
import turtle.commands.Command;
import turtle.commands.DeadlineCommand;
import turtle.commands.DeleteCommand;
import turtle.commands.EventCommand;
import turtle.commands.FindCommand;
import turtle.commands.ListCommand;
import turtle.commands.MarkCommand;
import turtle.commands.TodoCommand;
import turtle.commands.UnmarkCommand;
import turtle.exceptions.CommandTurtleException;
import turtle.exceptions.TurtleException;

/** Parser supports the parsing of user commands into Command objects. */
public class Parser {

    /**
     * Converts the given user command string into its corresponding command object, by parsing the command type first,
     * followed by each of the command's various sections.
     *
     * @param userCommand User's command.
     * @return Command object that represents the user's command.
     * @throws TurtleException If command syntax is malformed.
     */
    public Command parseCommand(String userCommand) throws TurtleException {
        assert userCommand != null;
        String[] sections = userCommand.split(" ");
        assert sections.length > 0;
        return switch (sections[0]) {
            case "bye" -> new ByeCommand();
            case "list" -> new ListCommand(sections, "list [sort]");
            case "mark" -> new MarkCommand(sections, "mark <index>");
            case "unmark" -> new UnmarkCommand(sections, "unmark <index>");
            case "delete" -> new DeleteCommand(sections, "delete <index>");
            case "todo" -> new TodoCommand(sections, "todo <task_name>");
            case "deadline" -> new DeadlineCommand(sections, "deadline <task_name> /by <deadline>");
            case "event" -> new EventCommand(sections, "event <task_name> /from <from_date> /to <to_date>");
            case "find" -> new FindCommand(sections, "find <search_str>");
            default -> throw new CommandTurtleException("Unknown command '" + sections[0] + "'", "help");
        };
    }

}
