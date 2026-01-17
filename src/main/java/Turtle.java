import java.util.Scanner;

public class Turtle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();
        greet();
        while (true) {
            String userCommand = scanner.nextLine();
            if (userCommand.equals("bye")) {
                break;
            } else if (userCommand.equals("list")) {
                list(taskList);
                continue;
            }
            Task newTask = new Task(userCommand);
            taskList.add(newTask);
            echo(userCommand);
        }
        bye();
    }

    private static void greet() {
        System.out.println("Hello! My name is Turtle.\nWhat can I do for you?\n");
    }

    private static void echo(String userCommand) {
        System.out.println("Added: " + userCommand + "\n");
    }

    private static void list(TaskList taskList) {
        System.out.println(taskList.toString());
    }

    private static void bye() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
