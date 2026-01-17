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
                continue;
            }
            bot.echo(userCommand);
        }
        bot.bye();
    }

}
