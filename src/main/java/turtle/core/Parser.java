package turtle.core;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import turtle.exceptions.ByeTurtleException;
import turtle.exceptions.CommandTurtleException;
import turtle.exceptions.TurtleException;
import turtle.tasks.DeadlineTask;
import turtle.tasks.EventTask;
import turtle.tasks.TodoTask;

public class Parser {

    private final Chatbot bot;

    public Parser(Chatbot bot) {
        this.bot = bot;
    }

    public void parseCommand(String userCommand) throws TurtleException {
        String[] sections = userCommand.split(" ");
        if (sections[0].equals("bye")) {
            throw new ByeTurtleException();
        } else if (sections[0].equals("list")) {
            this.bot.list();
        } else if (sections[0].equals("mark")) {
            if (sections.length != 2) {
                throw new CommandTurtleException("Invalid syntax", "mark <index>");
            }
            try {
                int idx = Integer.parseInt(sections[1]);
                this.bot.mark(idx);
            } catch (NumberFormatException e) {
                throw new CommandTurtleException("Unable to parse <index> '" + sections[1] + "'", "mark <index>");
            }
        } else if (sections[0].equals("unmark")) {
            if (sections.length != 2) {
                throw new CommandTurtleException("Invalid syntax", "unmark <index>");
            }
            try {
                int idx = Integer.parseInt(sections[1]);
                this.bot.unmark(idx);
            } catch (NumberFormatException e) {
                throw new CommandTurtleException("Unable to parse <index> '" + sections[1] + "'", "unmark <index>");
            }
        } else if (sections[0].equals("delete")) {
            if (sections.length != 2) {
                throw new CommandTurtleException("Invalid syntax", "delete <index>");
            }
            try {
                int idx = Integer.parseInt(sections[1]);
                this.bot.delete(idx);
            } catch (NumberFormatException e) {
                throw new CommandTurtleException("Unable to parse <index> '" + sections[1] + "'", "delete <index>");
            }
        } else if (sections[0].equals("todo")) {
            if (sections.length == 1) {
                throw new CommandTurtleException("Invalid syntax", "todo <task_name>");
            }
            String remainingStr = String.join(" ", Arrays.copyOfRange(sections, 1, sections.length));
            TodoTask task = new TodoTask(remainingStr);
            this.bot.addTask(task);
        } else if (sections[0].equals("deadline")) {
            int bySectionIdx = Arrays.asList(sections).indexOf("/by");
            if (bySectionIdx == -1) {
                throw new CommandTurtleException("Unable to find '/by' section", "deadline <task_name> /by <deadline>");
            }
            if (bySectionIdx == 1) {
                throw new CommandTurtleException("Unable to find <task_name>", "deadline <task_name> /by <deadline>");
            }
            if (bySectionIdx == sections.length - 1) {
                throw new CommandTurtleException("Unable to find <deadline>", "deadline <task_name> /by <deadline>");
            }
            String taskName = String.join(" ", Arrays.copyOfRange(sections, 1, bySectionIdx));
            String taskDeadline = String.join(" ", Arrays.copyOfRange(sections, bySectionIdx + 1, sections.length));
            LocalDate parsedTaskDeadline = null;
            try {
                parsedTaskDeadline = LocalDate.parse(taskDeadline);
            } catch (DateTimeParseException e) {
                throw new CommandTurtleException("Unable to parse <deadline> '" + taskDeadline + "'",
                        "deadline <task_name> /by <deadline>");
            }
            DeadlineTask task = new DeadlineTask(taskName, parsedTaskDeadline);
            this.bot.addTask(task);
        } else if (sections[0].equals("event")) {
            int fromSectionIdx = Arrays.asList(sections).indexOf("/from");
            int toSectionIdx = Arrays.asList(sections).indexOf("/to");
            if (fromSectionIdx == -1) {
                throw new CommandTurtleException("Unable to find '/from' section",
                        "event <task_name> /from <from_date> /to <to_date>");
            }
            if (toSectionIdx == -1) {
                throw new CommandTurtleException("Unable to find '/to' section",
                        "event <task_name> /from <from_date> /to <to_date>");
            }
            if (fromSectionIdx >= toSectionIdx) {
                throw new CommandTurtleException("Expected '/from' before '/to'",
                        "event <task_name> /from <from_date> /to <to_date>");
            }
            if (fromSectionIdx == 1) {
                throw new CommandTurtleException("Unable to find <task_name>",
                        "event <task_name> /from <from_date> /to <to_date>");
            }
            if (fromSectionIdx + 1 == toSectionIdx) {
                throw new CommandTurtleException("Unable to find <from_date>",
                        "event <task_name> /from <from_date> /to <to_date>");
            }
            if (toSectionIdx == sections.length - 1) {
                throw new CommandTurtleException("Unable to find <to_date>",
                        "event <task_name> /from <from_date> /to <to_date>");
            }
            String taskName = String.join(" ", Arrays.copyOfRange(sections, 1, fromSectionIdx));
            String taskFromDate = String.join(" ", Arrays.copyOfRange(sections, fromSectionIdx + 1, toSectionIdx));
            String taskToDate = String.join(" ", Arrays.copyOfRange(sections, toSectionIdx + 1, sections.length));
            LocalDate parsedTaskFromDate = null;
            try {
                parsedTaskFromDate = LocalDate.parse(taskFromDate);
            } catch (DateTimeParseException e) {
                throw new CommandTurtleException("Unable to parse <from_date> '" + taskFromDate + "'",
                        "event <task_name> /from <from_date> /to <to_date>");
            }
            LocalDate parsedTaskToDate = null;
            try {
                parsedTaskToDate = LocalDate.parse(taskToDate);
            } catch (DateTimeParseException e) {
                throw new CommandTurtleException("Unable to parse <to_date> '" + taskToDate + "'",
                        "event <task_name> /from <from_date> /to <to_date>");
            }
            EventTask task = new EventTask(taskName, parsedTaskFromDate, parsedTaskToDate);
            this.bot.addTask(task);
        } else if (sections[0].equals("find")) {
            String remainingStr = String.join(" ", Arrays.copyOfRange(sections, 1, sections.length));
            this.bot.find(remainingStr);
        } else {
            throw new CommandTurtleException("Unknown command '" + sections[0] + "'", "help");
        }
    }

}
