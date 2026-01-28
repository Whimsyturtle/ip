import exceptions.CommandTurtleException;
import tasks.Task;
import tasks.TaskFactory;
import tasks.TaskList;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Chatbot {

    private final TaskList taskList;
    private final Ui ui;

    public Chatbot() {
        this.taskList = new TaskList();
        this.ui = new Ui(System.out);
    }

    public void greet() {
        ui.greet();
    }

    public void error(String msg) {
        ui.error(msg);
    }

    public void addTask(Task newTask) {
        taskList.add(newTask);
        ui.addTask(newTask);
    }

    public void list() {
        ui.list(taskList);
    }

    public void mark(int idx) throws CommandTurtleException {
        if (idx < 1 || idx > this.taskList.size()) {
            throw new CommandTurtleException("Invalid task index " + idx, "mark <index>");
        }
        Task task = this.taskList.get(idx-1);
        task.markDone();
        ui.mark(task);
    }

    public void unmark(int idx) throws CommandTurtleException {
        if (idx < 1 || idx > this.taskList.size()) {
            throw new CommandTurtleException("Invalid task index " + idx, "unmark <index>");
        }
        Task task = this.taskList.get(idx-1);
        task.unmarkDone();
        ui.unmark(task);
    }

    public void delete(int idx) throws CommandTurtleException {
        if (idx < 1 || idx > this.taskList.size()) {
            throw new CommandTurtleException("Invalid task index " + idx, "delete <index>");
        }
        Task task = this.taskList.remove(idx-1);
        ui.delete(task);
    }

    public void bye() {
        ui.bye();
    }

    public void saveChatbotToFile(Path path) throws IOException {
        FileWriter fw = new FileWriter(path.toFile());
        for (int i = 0; i < this.taskList.size(); i++) {
            fw.write(this.taskList.get(i).serialize() + "\n");
        }
        fw.close();
    }

    public static Chatbot loadChatbotFromFile(Path path) throws IOException {
        // Create parent folders & file if they don't exist yet
        Files.createDirectories(path.getParent());
        if (Files.notExists(path)) {
            Files.createFile(path);
        }

        // Read and parse file accordingly
        Chatbot bot = new Chatbot();
        Scanner scanner = new Scanner(path);
        while (scanner.hasNext()) {
            bot.addTask(TaskFactory.deserialize(scanner.nextLine()));
        }
        return bot;
    }

}
