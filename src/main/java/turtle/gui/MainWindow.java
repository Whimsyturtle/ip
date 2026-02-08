package turtle.gui;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import turtle.core.Chatbot;
import turtle.exceptions.ByeTurtleException;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Chatbot bot;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image turtleImage = new Image(this.getClass().getResourceAsStream("/images/turtle.png"));

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());
    }

    /**
     * Injects the Turtle Chatbot instance.
     *
     * @param bot Turtle Chatbot.
     */
    public void setTurtle(Chatbot bot) {
        assert bot != null;
        this.bot = bot;
        this.dialogContainer.getChildren().addAll(
                DialogBox.getTurtleDialog("Hello! My name is Turtle.\nWhat can I do for you?\n", this.turtleImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        boolean isExit = false;
        String userText = this.userInput.getText();
        String turtleText;
        try {
            turtleText = this.bot.getResponse(userText);
        } catch (ByeTurtleException e) {
            turtleText = "Bye. Hope to see you again soon!\n";
            isExit = true;
        }
        this.dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, this.userImage),
                DialogBox.getTurtleDialog(turtleText, this.turtleImage)
        );
        this.userInput.clear();

        if (isExit) {
            // The following code is adapted from:
            // https://github.com/NUS-CS2103-AY2526-S2/forum/issues/153#issuecomment-3852788558
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> {
                Stage stage = (Stage) userInput.getScene().getWindow();
                stage.close();
            });
            delay.play();
        }
    }

}
