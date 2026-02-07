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
        String[] sections = userCommand.split(" ");
        if (sections[0].equals("bye")) {
            return new ByeCommand();
        } else if (sections[0].equals("list")) {
            return new ListCommand();
        } else if (sections[0].equals("mark")) {
            return new MarkCommand(sections, "mark <index>");
        } else if (sections[0].equals("unmark")) {
            return new UnmarkCommand(sections, "unmark <index>");
        } else if (sections[0].equals("delete")) {
            return new DeleteCommand(sections, "delete <index>");
        } else if (sections[0].equals("todo")) {
            return new TodoCommand(sections, "todo <task_name>");
        } else if (sections[0].equals("deadline")) {
            return new DeadlineCommand(sections, "deadline <task_name> /by <deadline>");
        } else if (sections[0].equals("event")) {
            return new EventCommand(sections, "event <task_name> /from <from_date> /to <to_date>");
        } else if (sections[0].equals("find")) {
            return new FindCommand(sections, "find <search_str>");
        } else {
            throw new CommandTurtleException("Unknown command '" + sections[0] + "'", "help");
        }
    }

}
