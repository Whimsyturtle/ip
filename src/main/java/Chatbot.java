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

    public Chatbot() {
        this.taskList = new TaskList();
    }

    public void greet() {
        System.out.println("Hello! My name is Turtle.\nWhat can I do for you?\n");
    }

    public void error(String msg) {
        System.out.println("[ERROR] " + msg + "\n");
    }

    public void addTask(Task newTask) {
        taskList.add(newTask);
        System.out.println("Added: " + newTask + "\n");
    }

    public void list() {
        System.out.println("Here are your tasks:\n" + this.taskList);
    }

    public void mark(int idx) throws TurtleException {
        if (idx < 1 || idx > this.taskList.size()) {
            throw new TurtleException("Invalid task index " + idx, "mark <index>");
        }
        Task task = this.taskList.get(idx-1);
        task.markDone();
        System.out.println("I've marked the following task as done:\n" + task + "\n");
    }

    public void unmark(int idx) throws TurtleException {
        if (idx < 1 || idx > this.taskList.size()) {
            throw new TurtleException("Invalid task index " + idx, "unmark <index>");
        }
        Task task = this.taskList.get(idx-1);
        task.unmarkDone();
        System.out.println("I've marked the following task as not done:\n" + task + "\n");
    }

    public void delete(int idx) throws TurtleException {
        if (idx < 1 || idx > this.taskList.size()) {
            throw new TurtleException("Invalid task index " + idx, "delete <index>");
        }
        Task task = this.taskList.remove(idx-1);
        System.out.println("I've deleted the following task:\n" + task + "\n");
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!\n");
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
