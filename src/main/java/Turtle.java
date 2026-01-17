import java.util.Scanner;

public class Turtle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Chatbot bot = new Chatbot();
        bot.greet();
        while (true) {
            String userCommand = scanner.nextLine();
            if (userCommand.equals("bye")) {
                break;
            } else if (userCommand.equals("list")) {
                bot.list();
            } else if (userCommand.startsWith("mark")) {
                // TODO: Handle invalid command syntax
                String[] sections = userCommand.split(" ");
                int idx = Integer.parseInt(sections[1]);
                bot.mark(idx);
            } else if (userCommand.startsWith("unmark")) {
                String[] sections = userCommand.split(" ");
                int idx = Integer.parseInt(sections[1]);
                bot.unmark(idx);
            } else {
                bot.echo(userCommand);
            }
        }
        bot.bye();
    }

}
