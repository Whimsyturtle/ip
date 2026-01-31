package turtle.core.commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import turtle.core.Chatbot;
import turtle.exceptions.CommandTurtleException;
import turtle.tasks.EventTask;

public class EventCommand extends Command {

    private final EventTask task;

    public EventCommand(String[] sections, String correctSyntax) throws CommandTurtleException {
        int fromSectionIdx = Arrays.asList(sections).indexOf("/from");
        int toSectionIdx = Arrays.asList(sections).indexOf("/to");
        if (fromSectionIdx == -1) {
            throw new CommandTurtleException("Unable to find '/from' section", correctSyntax);
        }
        if (toSectionIdx == -1) {
            throw new CommandTurtleException("Unable to find '/to' section", correctSyntax);
        }
        if (fromSectionIdx >= toSectionIdx) {
            throw new CommandTurtleException("Expected '/from' before '/to'", correctSyntax);
        }
        if (fromSectionIdx == 1) {
            throw new CommandTurtleException("Unable to find <task_name>", correctSyntax);
        }
        if (fromSectionIdx + 1 == toSectionIdx) {
            throw new CommandTurtleException("Unable to find <from_date>", correctSyntax);
        }
        if (toSectionIdx == sections.length - 1) {
            throw new CommandTurtleException("Unable to find <to_date>", correctSyntax);
        }
        String taskName = String.join(" ", Arrays.copyOfRange(sections, 1, fromSectionIdx));
        String taskFromDate = String.join(" ", Arrays.copyOfRange(sections, fromSectionIdx + 1, toSectionIdx));
        String taskToDate = String.join(" ", Arrays.copyOfRange(sections, toSectionIdx + 1, sections.length));
        LocalDate parsedTaskFromDate = null;
        try {
            parsedTaskFromDate = LocalDate.parse(taskFromDate);
        } catch (DateTimeParseException e) {
            throw new CommandTurtleException("Unable to parse <from_date> '" + taskFromDate + "'", correctSyntax);
        }
        LocalDate parsedTaskToDate = null;
        try {
            parsedTaskToDate = LocalDate.parse(taskToDate);
        } catch (DateTimeParseException e) {
            throw new CommandTurtleException("Unable to parse <to_date> '" + taskToDate + "'", correctSyntax);
        }
        this.task = new EventTask(taskName, parsedTaskFromDate, parsedTaskToDate);
    }

    @Override
    public void executeCommand(Chatbot bot) {
        bot.addTask(task);
    }

}
