package turtle.core.commands;

import java.util.Arrays;

import turtle.core.Chatbot;
import turtle.exceptions.CommandTurtleException;
import turtle.tasks.TodoTask;

/** TodoCommand represents the command `todo &lt;task_name&gt;` */
public class TodoCommand extends Command {

    private final TodoTask task;

    /**
     * Parses the todo command and creates a TodoTask object.
     *
     * @param sections Command sections.
     * @param correctSyntax Command syntax `todo &lt;task_name&gt;`.
     * @throws CommandTurtleException If command syntax is malformed.
     */
    public TodoCommand(String[] sections, String correctSyntax) throws CommandTurtleException {
        if (sections.length == 1) {
            throw new CommandTurtleException("Invalid syntax", correctSyntax);
        }
        String remainingStr = String.join(" ", Arrays.copyOfRange(sections, 1, sections.length));
        this.task = new TodoTask(remainingStr);
    }

    /**
     * Adds the created TodoTask object to the Turtle Chatbot's task list.
     *
     * @param bot Turtle Chatbot.
     */
    @Override
    public void executeCommand(Chatbot bot) {
        bot.addTask(task);
    }

}
