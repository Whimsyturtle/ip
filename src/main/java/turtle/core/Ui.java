package turtle.core;

import turtle.tasks.Task;
import turtle.tasks.TaskList;

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
        this.printStream = printStream;
        this.scanner = new Scanner(inputStream);
    }

    /**
     * Uses Scanner to read the user's command.
     *
     * @return User's command.
     */
    public String getCommand() {
        return this.scanner.nextLine();
    }

    /**
     * Outputs a greeting message to the user.
     */
    public void greet() {
        this.printStream.println("Hello! My name is Turtle.\nWhat can I do for you?\n");
    }

    /**
     * Outputs the specified error message to the user.
     *
     * @param msg Error message.
     */
    public void error(String msg) {
        this.printStream.println("[ERROR] " + msg + "\n");
    }

    /**
     * Outputs a message confirming the newly-added task.
     *
     * @param newTask Newly-added task.
     */
    public void addTask(Task newTask) {
        this.printStream.println("Added: " + newTask + "\n");
    }

    /**
     * Outputs the user's list of tasks.
     *
     * @param taskList List of tasks.
     */
    public void list(TaskList taskList) {
        this.printStream.println("Here are your tasks:\n" + taskList);
    }

    /**
     * Outputs a message confirming the task was marked as done.
     *
     * @param task Task to be marked.
     */
    public void mark(Task task) {
        this.printStream.println("I've marked the following task as done:\n" + task + "\n");
    }

    /**
     * Outputs a message confirming the task was marked as not done.
     *
     * @param task Task to be unmarked.
     */
    public void unmark(Task task) {
        this.printStream.println("I've marked the following task as not done:\n" + task + "\n");
    }

    /**
     * Outputs a message confirming the deleted task.
     *
     * @param task Deleted task.
     */
    public void delete(Task task) {
        this.printStream.println("I've deleted the following task:\n" + task + "\n");
    }

    /**
     * Outputs a bye message to the user.
     */
    public void bye() {
        this.printStream.println("Bye. Hope to see you again soon!\n");
    }

}
