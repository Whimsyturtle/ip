import tasks.DeadlineTask;
import tasks.EventTask;
import tasks.TodoTask;

import java.util.Arrays;
import java.util.Scanner;

public class Turtle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Chatbot bot = new Chatbot();
        bot.greet();
        while (true) {
            String userCommand = scanner.nextLine();
            String[] sections = userCommand.split(" ");
            if (sections[0].equals("bye")) {
                break;
            } else if (sections[0].equals("list")) {
                bot.list();
            } else if (sections[0].equals("mark")) {
                if (sections.length != 2) {
                    bot.error("Invalid syntax for 'mark' command, expected: mark <index>");
                    continue;
                }
                try {
                    int idx = Integer.parseInt(sections[1]);
                    bot.mark(idx);
                } catch (NumberFormatException e) {
                    bot.error("Unable to parse index '" + sections[1] + "'");
                }
            } else if (sections[0].equals("unmark")) {
                if (sections.length != 2) {
                    bot.error("Invalid syntax for 'unmark' command, expected: unmark <index>");
                    continue;
                }
                try {
                    int idx = Integer.parseInt(sections[1]);
                    bot.unmark(idx);
                } catch (NumberFormatException e) {
                    bot.error("Unable to parse index '" + sections[1] + "'");
                }
            } else if (sections[0].equals("todo")) {
                if (sections.length == 1) {
                    bot.error("Invalid syntax for 'todo' command, expected: todo <task_name>");
                    continue;
                }
                String remainingStr = String.join(" ",
                        Arrays.copyOfRange(sections, 1, sections.length));
                TodoTask task = new TodoTask(remainingStr);
                bot.addTask(task);
            } else if (sections[0].equals("deadline")) {
                int bySectionIdx = Arrays.asList(sections).indexOf("/by");
                if (bySectionIdx == -1) {
                    bot.error("Invalid syntax for 'deadline' command, unable to find '/by' section");
                    continue;
                }
                if (bySectionIdx == 1) {
                    bot.error("Invalid syntax for 'deadline' command, unable to find <task_name>");
                    continue;
                }
                if (bySectionIdx == sections.length-1) {
                    bot.error("Invalid syntax for 'deadline' command, unable to find <deadline>");
                    continue;
                }
                String taskName = String.join(" ",
                        Arrays.copyOfRange(sections, 1, bySectionIdx));
                String taskDeadline = String.join(" ",
                        Arrays.copyOfRange(sections, bySectionIdx+1, sections.length));
                DeadlineTask task = new DeadlineTask(taskName, taskDeadline);
                bot.addTask(task);
            } else if (sections[0].equals("event")) {
                int fromSectionIdx = Arrays.asList(sections).indexOf("/from");
                int toSectionIdx = Arrays.asList(sections).indexOf("/to");
                if (fromSectionIdx == -1) {
                    bot.error("Invalid syntax for 'event' command, unable to find '/from' section");
                    continue;
                }
                if (toSectionIdx == -1) {
                    bot.error("Invalid syntax for 'event' command, unable to find '/to' section");
                    continue;
                }
                if (fromSectionIdx >= toSectionIdx) {
                    bot.error("Invalid syntax for 'event' command, expected '/from' before '/to'");
                    continue;
                }
                if (fromSectionIdx == 1) {
                    bot.error("Invalid syntax for 'event' command, unable to find <task_name>");
                    continue;
                }
                if (fromSectionIdx+1 == toSectionIdx) {
                    bot.error("Invalid syntax for 'event' command, unable to find <from_datetime>");
                    continue;
                }
                if (toSectionIdx == sections.length-1) {
                    bot.error("Invalid syntax for 'event' command, unable to find <to_datetime>");
                    continue;
                }
                String taskName = String.join(" ",
                        Arrays.copyOfRange(sections, 1, fromSectionIdx));
                String taskFromDateTime = String.join(" ",
                        Arrays.copyOfRange(sections, fromSectionIdx+1, toSectionIdx));
                String taskToDateTime = String.join(" ",
                        Arrays.copyOfRange(sections, toSectionIdx+1, sections.length));
                EventTask task = new EventTask(taskName, taskFromDateTime, taskToDateTime);
                bot.addTask(task);
            } else {
                bot.error("Unknown command '" + sections[0] + "'");
            }
        }
        bot.bye();
    }

}
