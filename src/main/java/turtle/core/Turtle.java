package turtle.core;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import turtle.gui.MainWindow;

/** Main class that launches Turtle Chatbot. */
public class Turtle extends Application {

    // TODO: Don't load chatbot from file for text-ui-test
    private static final Path STORED_CHATBOT_FILEPATH = Paths.get("./data/turtle.txt");
    private final Chatbot bot = new Chatbot(STORED_CHATBOT_FILEPATH);

    /**
     * Runs the Turtle Chatbot's JavaFX GUI and other initialization steps.
     *
     * @param stage JavaFX primary stage.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Turtle.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setTurtle(bot);  // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
