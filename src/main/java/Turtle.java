import java.util.Scanner;

public class Turtle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        greet();
        while (true) {
            String userCommand = scanner.nextLine();
            if (userCommand.equals("bye")) {
                break;
            }
            echo(userCommand);
        }
        bye();
    }

    private static void greet() {
        System.out.println("Hello! My name is Turtle.\nWhat can I do for you?\n");
    }

    private static void echo(String userCommand) {
        System.out.println("Command: " + userCommand + "\n");
    }

    private static void bye() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
