import exceptions.ByeTurtleException;
import exceptions.TurtleException;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        Parser parser = new Parser(bot);
        bot.greet();
        while (true) {
            String userCommand = scanner.nextLine();
            try {
                parser.parseCommand(userCommand);
                try {
                    bot.saveChatbotToFile(STORED_CHATBOT_FILEPATH);
                } catch (IOException e) {
                    // TODO: More graceful handling
                    System.out.println("Unable to save chatbot data!");
                    return;
                }
            } catch (ByeTurtleException e) {
                break;
            } catch (TurtleException e) {
                bot.error(e.toString());
            }
        }
        bot.bye();
    }

}
