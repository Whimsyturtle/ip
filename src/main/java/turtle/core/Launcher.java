package turtle.core;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Runs the Turtle Chatbot (i.e. this is the entrypoint method).
     *
     * @param args Currently unused.
     */
    public static void main(String[] args) {
        Application.launch(Turtle.class, args);
    }

}
