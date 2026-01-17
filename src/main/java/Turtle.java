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
                // TODO: Handle invalid command syntax
                int idx = Integer.parseInt(sections[1]);
                bot.mark(idx);
            } else if (sections[0].equals("unmark")) {
                int idx = Integer.parseInt(sections[1]);
                bot.unmark(idx);
            } else if (sections[0].equals("todo")) {
                String remainingStr = String.join(" ",
                        Arrays.copyOfRange(sections, 1, sections.length));
                TodoTask task = new TodoTask(remainingStr);
                bot.addTask(task);
            } else if (sections[0].equals("deadline")) {
                // TODO: Handle missing "/by", or missing section
                int bySectionIdx = Arrays.asList(sections).indexOf("/by");
                String taskName = String.join(" ",
                        Arrays.copyOfRange(sections, 1, bySectionIdx));
                String taskDeadline = String.join(" ",
                        Arrays.copyOfRange(sections, bySectionIdx+1, sections.length));
                DeadlineTask task = new DeadlineTask(taskName, taskDeadline);
                bot.addTask(task);
            } else if (sections[0].equals("event")) {
                // TODO: Ensure "/from" comes before "/to"
                int fromSectionIdx = Arrays.asList(sections).indexOf("/from");
                int toSectionIdx = Arrays.asList(sections).indexOf("/to");
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
