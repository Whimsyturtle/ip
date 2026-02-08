package turtle.core;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;

import turtle.commands.Command;
import turtle.exceptions.ByeTurtleException;
import turtle.exceptions.CommandTurtleException;
import turtle.exceptions.TurtleException;
import turtle.tasks.Task;
import turtle.tasks.TaskList;

/** Chatbot maintains a list of tasks, automatically syncs them with a storage file, and supports various methods. */
public class Chatbot {

    private final ByteArrayOutputStream baos;
    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;
    private final Parser parser;

    /**
     * Creates Chatbot object which can interact with user via command-line interface, maintain a list of tasks, and
     * automatically sync them with a storage file.
     *
     * @param storagePath Path of storage file.
     */
    public Chatbot(Path storagePath) {
        // The following section of code is adapted from:
        // https://stackoverflow.com/questions/1207281/java-how-do-i-read-from-printstream
        this.baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos, true);
        this.ui = new Ui(ps, System.in);

        this.storage = new Storage(storagePath);

        TaskList tmpTaskList = null;
        try {
            tmpTaskList = this.storage.loadTasksFromFile();
        } catch (IOException e) {
            this.ui.displayError("Unable to load chatbot data! Defaulting to empty task list...");
            tmpTaskList = new TaskList();
        }
        this.taskList = tmpTaskList;

        this.parser = new Parser();
    }

    /**
     * Adds the given task into the task list, and displays the newly-added task.
     *
     * @param newTask Task to be added.
     */
    public void addTask(Task newTask) {
        this.taskList.add(newTask);
        this.ui.display("Added: " + newTask + "\n");
    }

    /**
     * Displays all tasks currently stored in the task list.
     */
    public void list() {
        this.ui.display("Here are your tasks:\n" + this.taskList);
    }

    /**
     * Marks the task at the given index as done, and displays the task.
     *
     * @param idx Index of the task to be marked.
     * @throws CommandTurtleException If the given index is invalid.
     */
    public void mark(int idx) throws CommandTurtleException {
        if (idx < 1 || idx > this.taskList.size()) {
            throw new CommandTurtleException("Invalid task index " + idx, "mark <index>");
        }
        Task task = this.taskList.get(idx - 1);
        task.markDone();
        this.ui.display("I've marked the following task as done:\n" + task + "\n");
    }

    /**
     * Marks the task at the given index as not done, and displays the task.
     *
     * @param idx Index of the task to be unmarked.
     * @throws CommandTurtleException If the given index is invalid.
     */
    public void unmark(int idx) throws CommandTurtleException {
        if (idx < 1 || idx > this.taskList.size()) {
            throw new CommandTurtleException("Invalid task index " + idx, "unmark <index>");
        }
        Task task = this.taskList.get(idx - 1);
        task.unmarkDone();
        this.ui.display("I've marked the following task as not done:\n" + task + "\n");
    }

    /**
     * Deletes the task at the given index, and displays the task.
     *
     * @param idx Index of the task to be deleted.
     * @throws CommandTurtleException If the given index is invalid.
     */
    public void delete(int idx) throws CommandTurtleException {
        if (idx < 1 || idx > this.taskList.size()) {
            throw new CommandTurtleException("Invalid task index " + idx, "delete <index>");
        }
        Task task = this.taskList.remove(idx - 1);
        this.ui.display("I've deleted the following task:\n" + task + "\n");
    }

    /**
     * Searches for all tasks containing the given search string, and displays them.
     *
     * @param str Search string.
     */
    public void find(String str) {
        TaskList filteredTaskList = new TaskList();
        for (int i = 0; i < this.taskList.size(); i++) {
            Task task = this.taskList.get(i);
            if (task.toString().contains(str)) {
                filteredTaskList.add(task);
            }
        }
        this.ui.display("Here are your tasks:\n" + filteredTaskList);
    }

    /**
     * Returns the Turtle Chatbot's response to the given user command, and automatically syncs task changes (if any)
     * to storage.
     *
     * @param userCommand User's command.
     * @return Turtle Chatbot's response.
     * @throws ByeTurtleException If the user says bye.
     */
    public String getResponse(String userCommand) throws ByeTurtleException {
        try {
            Command command = this.parser.parseCommand(userCommand);
            command.executeCommand(this);
        } catch (ByeTurtleException e) {
            throw e; // Re-throw exception
        } catch (TurtleException e) {
            this.ui.displayError(e.toString());
        }

        try {
            this.storage.saveTasksToFile(this.taskList);
        } catch (IOException e) {
            this.ui.displayError("Unable to save chatbot data!");
        }

        String response = this.baos.toString();
        this.baos.reset();
        return response;
    }

}
