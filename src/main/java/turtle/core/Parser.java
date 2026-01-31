package turtle.core;

import turtle.core.commands.ByeCommand;
import turtle.core.commands.Command;
import turtle.core.commands.DeadlineCommand;
import turtle.core.commands.DeleteCommand;
import turtle.core.commands.EventCommand;
import turtle.core.commands.FindCommand;
import turtle.core.commands.ListCommand;
import turtle.core.commands.MarkCommand;
import turtle.core.commands.TodoCommand;
import turtle.core.commands.UnmarkCommand;
import turtle.exceptions.CommandTurtleException;
import turtle.exceptions.TurtleException;

public class Parser {

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
