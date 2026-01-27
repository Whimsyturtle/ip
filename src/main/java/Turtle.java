import tasks.DeadlineTask;
import tasks.EventTask;
import tasks.TodoTask;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;

public class Turtle {

    private static final Path STORED_CHATBOT_FILEPATH = Paths.get("./data/turtle.txt");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Chatbot bot = null;
        try {
            // TODO: Don't load chatbot from file for text-ui-test
            bot = Chatbot.loadChatbotFromFile(STORED_CHATBOT_FILEPATH);
        } catch (IOException e) {
            System.out.println("Unable to load chatbot data!");
            return;
        }
        bot.greet();
        while (true) {
            String userCommand = scanner.nextLine();
            try {
                String[] sections = userCommand.split(" ");
                if (sections[0].equals("bye")) {
                    break;
                } else if (sections[0].equals("list")) {
                    bot.list();
                } else if (sections[0].equals("mark")) {
                    if (sections.length != 2) {
                        throw new TurtleException("Invalid syntax", "mark <index>");
                    }
                    try {
                        int idx = Integer.parseInt(sections[1]);
                        bot.mark(idx);
                    } catch (NumberFormatException e) {
                        throw new TurtleException("Unable to parse <index> '" + sections[1] + "'",
                                "mark <index>");
                    }
                } else if (sections[0].equals("unmark")) {
                    if (sections.length != 2) {
                        throw new TurtleException("Invalid syntax", "unmark <index>");
                    }
                    try {
                        int idx = Integer.parseInt(sections[1]);
                        bot.unmark(idx);
                    } catch (NumberFormatException e) {
                        throw new TurtleException("Unable to parse <index> '" + sections[1] + "'",
                                "unmark <index>");
                    }
                } else if (sections[0].equals("delete")) {
                    if (sections.length != 2) {
                        throw new TurtleException("Invalid syntax", "delete <index>");
                    }
                    try {
                        int idx = Integer.parseInt(sections[1]);
                        bot.delete(idx);
                    } catch (NumberFormatException e) {
                        throw new TurtleException("Unable to parse <index> '" + sections[1] + "'",
                                "delete <index>");
                    }
                } else if (sections[0].equals("todo")) {
                    if (sections.length == 1) {
                        throw new TurtleException("Invalid syntax", "todo <task_name>");
                    }
                    String remainingStr = String.join(" ",
                            Arrays.copyOfRange(sections, 1, sections.length));
                    TodoTask task = new TodoTask(remainingStr);
                    bot.addTask(task);
                } else if (sections[0].equals("deadline")) {
                    int bySectionIdx = Arrays.asList(sections).indexOf("/by");
                    if (bySectionIdx == -1) {
                        throw new TurtleException("Unable to find '/by' section",
                                "deadline <task_name> /by <deadline>");
                    }
                    if (bySectionIdx == 1) {
                        throw new TurtleException("Unable to find <task_name>",
                                "deadline <task_name> /by <deadline>");
                    }
                    if (bySectionIdx == sections.length-1) {
                        throw new TurtleException("Unable to find <deadline>",
                                "deadline <task_name> /by <deadline>");
                    }
                    String taskName = String.join(" ",
                            Arrays.copyOfRange(sections, 1, bySectionIdx));
                    String taskDeadline = String.join(" ",
                            Arrays.copyOfRange(sections, bySectionIdx+1, sections.length));
                    LocalDate parsedTaskDeadline = null;
                    try {
                        parsedTaskDeadline = LocalDate.parse(taskDeadline);
                    } catch (DateTimeParseException e) {
                        throw new TurtleException("Unable to parse <deadline> '" + taskDeadline + "'",
                                "deadline <task_name> /by <deadline>");
                    }
                    DeadlineTask task = new DeadlineTask(taskName, parsedTaskDeadline);
                    bot.addTask(task);
                } else if (sections[0].equals("event")) {
                    int fromSectionIdx = Arrays.asList(sections).indexOf("/from");
                    int toSectionIdx = Arrays.asList(sections).indexOf("/to");
                    if (fromSectionIdx == -1) {
                        throw new TurtleException("Unable to find '/from' section",
                                "event <task_name> /from <from_datetime> /to <to_datetime>");
                    }
                    if (toSectionIdx == -1) {
                        throw new TurtleException("Unable to find '/to' section",
                                "event <task_name> /from <from_datetime> /to <to_datetime>");
                    }
                    if (fromSectionIdx >= toSectionIdx) {
                        throw new TurtleException("Expected '/from' before '/to'",
                                "event <task_name> /from <from_datetime> /to <to_datetime>");
                    }
                    if (fromSectionIdx == 1) {
                        throw new TurtleException("Unable to find <task_name>",
                                "event <task_name> /from <from_datetime> /to <to_datetime>");
                    }
                    if (fromSectionIdx+1 == toSectionIdx) {
                        throw new TurtleException("Unable to find <from_datetime>",
                                "event <task_name> /from <from_datetime> /to <to_datetime>");
                    }
                    if (toSectionIdx == sections.length-1) {
                        throw new TurtleException("Unable to find <to_datetime>",
                                "event <task_name> /from <from_datetime> /to <to_datetime>");
                    }
                    String taskName = String.join(" ",
                            Arrays.copyOfRange(sections, 1, fromSectionIdx));
                    String taskFromDateTime = String.join(" ",
                            Arrays.copyOfRange(sections, fromSectionIdx+1, toSectionIdx));
                    String taskToDateTime = String.join(" ",
                            Arrays.copyOfRange(sections, toSectionIdx+1, sections.length));
                    LocalDate parsedTaskFromDateTime = null;
                    try {
                        parsedTaskFromDateTime = LocalDate.parse(taskFromDateTime);
                    } catch (DateTimeParseException e) {
                        throw new TurtleException("Unable to parse <from_datetime> '" + taskFromDateTime + "'",
                                "event <task_name> /from <from_datetime> /to <to_datetime>");
                    }
                    LocalDate parsedTaskToDateTime = null;
                    try {
                        parsedTaskToDateTime = LocalDate.parse(taskToDateTime);
                    } catch (DateTimeParseException e) {
                        throw new TurtleException("Unable to parse <to_datetime> '" + taskToDateTime + "'",
                                "event <task_name> /from <from_datetime> /to <to_datetime>");
                    }
                    EventTask task = new EventTask(taskName, parsedTaskFromDateTime, parsedTaskToDateTime);
                    bot.addTask(task);
                } else {
                    throw new TurtleException("Unknown command '" + sections[0] + "'", "help");
                }
                try {
                    bot.saveChatbotToFile(STORED_CHATBOT_FILEPATH);
                } catch (IOException e) {
                    // TODO: More graceful handling
                    System.out.println("Unable to save chatbot data!");
                    return;
                }
            } catch (TurtleException e) {
                bot.error(e.toString());
            }
        }
        bot.bye();
    }

}
