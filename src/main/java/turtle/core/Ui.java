package turtle.core;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/** Ui supports interactions with the user (i.e. sending output to, and receiving input from the user). */
public class Ui {

    private final PrintStream printStream;
    private final Scanner scanner;

    /**
     * Creates Ui object capable of sending output to, and receiving input from the user.
     *
     * @param printStream Stream used to send output.
     * @param inputStream Stream used to receive input.
     */
    public Ui(PrintStream printStream, InputStream inputStream) {
        assert printStream != null;
        assert inputStream != null;
        this.printStream = printStream;
        this.scanner = new Scanner(inputStream);
    }

    /**
     * Uses Scanner to read the user's command.
     *
     * @return User's command.
     */
    @Deprecated
    public String getCommand() {
        return this.scanner.nextLine();
    }

    /**
     * Outputs the specified message to the user.
     *
     * @param msg Message.
     */
    public void display(String msg) {
        assert msg != null;
        this.printStream.println(msg);
    }

    /**
     * Outputs the specified error message to the user.
     *
     * @param msg Error message.
     */
    public void displayError(String msg) {
        assert msg != null;
        display("[ERROR] " + msg + "\n");
    }

}
