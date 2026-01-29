package turtle.core;

import turtle.tasks.Task;
import turtle.tasks.TaskList;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {

    private final PrintStream printStream;
    private final Scanner scanner;

    public Ui(PrintStream printStream, InputStream inputStream) {
        this.printStream = printStream;
        this.scanner = new Scanner(inputStream);
    }

    public String getCommand() {
        return this.scanner.nextLine();
    }

    public void greet() {
        this.printStream.println("Hello! My name is Turtle.\nWhat can I do for you?\n");
    }

    public void error(String msg) {
        this.printStream.println("[ERROR] " + msg + "\n");
    }

    public void addTask(Task newTask) {
        this.printStream.println("Added: " + newTask + "\n");
    }

    public void list(TaskList taskList) {
        this.printStream.println("Here are your tasks:\n" + taskList);
    }

    public void mark(Task task) {
        this.printStream.println("I've marked the following task as done:\n" + task + "\n");
    }

    public void unmark(Task task) {
        this.printStream.println("I've marked the following task as not done:\n" + task + "\n");
    }

    public void delete(Task task) {
        this.printStream.println("I've deleted the following task:\n" + task + "\n");
    }

    public void bye() {
        this.printStream.println("Bye. Hope to see you again soon!\n");
    }

}
