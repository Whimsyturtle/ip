package turtle.core.commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import turtle.core.Chatbot;
import turtle.exceptions.CommandTurtleException;
import turtle.tasks.DeadlineTask;

/** DeadlineCommand represents the command `deadline &lt;task_name&gt; /by &lt;deadline&gt;` */
public class DeadlineCommand extends Command {

    private final DeadlineTask task;

    /**
     * Parses the deadline command and creates a DeadlineTask object.
     *
     * @param sections Command sections.
     * @param correctSyntax Command syntax `deadline &lt;task_name&gt; /by &lt;deadline&gt;`.
     * @throws CommandTurtleException If command syntax is malformed.
     */
    public DeadlineCommand(String[] sections, String correctSyntax) throws CommandTurtleException {
        int bySectionIdx = Arrays.asList(sections).indexOf("/by");
        if (bySectionIdx == -1) {
            throw new CommandTurtleException("Unable to find '/by' section", correctSyntax);
        }
        if (bySectionIdx == 1) {
            throw new CommandTurtleException("Unable to find <task_name>", correctSyntax);
        }
        if (bySectionIdx == sections.length - 1) {
            throw new CommandTurtleException("Unable to find <deadline>", correctSyntax);
        }
        String taskName = String.join(" ", Arrays.copyOfRange(sections, 1, bySectionIdx));
        String taskDeadline = String.join(" ", Arrays.copyOfRange(sections, bySectionIdx + 1, sections.length));
        LocalDate parsedTaskDeadline = null;
        try {
            parsedTaskDeadline = LocalDate.parse(taskDeadline);
        } catch (DateTimeParseException e) {
            throw new CommandTurtleException("Unable to parse <deadline> '" + taskDeadline + "'", correctSyntax);
        }
        this.task = new DeadlineTask(taskName, parsedTaskDeadline);
    }

    /**
     * Adds the created DeadlineTask object to the Turtle Chatbot's task list.
     *
     * @param bot Turtle Chatbot.
     */
    @Override
    public void executeCommand(Chatbot bot) {
        bot.addTask(task);
    }

}
