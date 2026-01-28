import java.nio.file.Path;
import java.nio.file.Paths;

public class Turtle {

    private static final Path STORED_CHATBOT_FILEPATH = Paths.get("./data/turtle.txt");

    public static void main(String[] args) {
        // TODO: Don't load chatbot from file for text-ui-test
        Chatbot bot = new Chatbot(STORED_CHATBOT_FILEPATH);
        bot.run();
    }

}
