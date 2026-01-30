package turtle.core;

import java.io.IOException;
import java.nio.file.Path;

import turtle.exceptions.ByeTurtleException;
import turtle.exceptions.CommandTurtleException;
import turtle.exceptions.TurtleException;
import turtle.tasks.Task;
import turtle.tasks.TaskList;

/** Chatbot maintains a list of tasks, automatically syncs them with a storage file, and supports various methods. */
public class Chatbot {

    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;

    /**
     * Creates Chatbot object which can interact with user via command-line interface, maintain a list of tasks, and
     * automatically sync them with a storage file.
     *
     * @param storagePath Path of storage file.
     */
    public Chatbot(Path storagePath) {
        this.ui = new Ui(System.out, System.in);
        this.storage = new Storage(storagePath);
        TaskList tmpTaskList = null;
        try {
            tmpTaskList = this.storage.loadTasksFromFile();
        } catch (IOException e) {
            this.ui.error("Unable to load chatbot data! Defaulting to empty task list...");
            tmpTaskList = new TaskList();
        }
        this.taskList = tmpTaskList;
    }

    public void addTask(Task newTask) {
        this.taskList.add(newTask);
        this.ui.addTask(newTask);
    }

    public void list() {
        this.ui.list(this.taskList);
    }

    public void mark(int idx) throws CommandTurtleException {
        if (idx < 1 || idx > this.taskList.size()) {
            throw new CommandTurtleException("Invalid task index " + idx, "mark <index>");
        }
        Task task = this.taskList.get(idx - 1);
        task.markDone();
        this.ui.mark(task);
    }

    public void unmark(int idx) throws CommandTurtleException {
        if (idx < 1 || idx > this.taskList.size()) {
            throw new CommandTurtleException("Invalid task index " + idx, "unmark <index>");
        }
        Task task = this.taskList.get(idx - 1);
        task.unmarkDone();
        this.ui.unmark(task);
    }

    public void delete(int idx) throws CommandTurtleException {
        if (idx < 1 || idx > this.taskList.size()) {
            throw new CommandTurtleException("Invalid task index " + idx, "delete <index>");
        }
        Task task = this.taskList.remove(idx - 1);
        this.ui.delete(task);
    }

    public void find(String str) {
        TaskList filteredTaskList = new TaskList();
        for (int i = 0; i < this.taskList.size(); i++) {
            Task task = this.taskList.get(i);
            if (task.toString().contains(str)) {
                filteredTaskList.add(task);
            }
        }
        this.ui.list(filteredTaskList);
    }

    /**
     * Runs chatbot, which involves greeting the user, followed by continually listening and responding to the user's
     * commands, and automatically syncing task changes (if any) to storage. If the user says bye, the chatbot will say
     * bye to the user and terminate.
     */
    public void run() {
        Parser parser = new Parser(this);
        this.ui.greet();
        while (true) {
            String userCommand = this.ui.getCommand();
            try {
                parser.parseCommand(userCommand);
                try {
                    this.storage.saveTasksToFile(this.taskList);
                } catch (IOException e) {
                    // TODO: More graceful handling
                    this.ui.error("Unable to save chatbot data!");
                    break;
                }
            } catch (ByeTurtleException e) {
                break;
            } catch (TurtleException e) {
                this.ui.error(e.toString());
            }
        }
        this.ui.bye();
    }

}
