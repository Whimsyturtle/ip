package turtle.core;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import turtle.tasks.TaskFactory;
import turtle.tasks.TaskList;

/** Storage supports the saving/loading of tasks to/from a specified storage file. */
public class Storage {

    private final Path path;

    /**
     * Creates Storage object which can save/load tasks to/from the specified storage file.
     *
     * @param path Path of storage file.
     */
    public Storage(Path path) {
        this.path = path;
    }

    /**
     * Saves tasks to storage file by serializing each task and writing them as a separate line in the storage file.
     *
     * @param taskList Tasks to save to storage file.
     * @throws IOException If an I/O exception occurs.
     */
    public void saveTasksToFile(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(this.path.toFile());
        for (int i = 0; i < taskList.size(); i++) {
            fw.write(taskList.get(i).serialize() + "\n");
        }
        fw.close();
    }

    /**
     * Loads tasks from storage file by reading each line and deserializing them into a task, before consolidating all
     * tasks into a TaskList. If the storage file and/or its parent folders don't exist, they are automatically created.
     *
     * @return Tasks loaded from storage file.
     * @throws IOException If an I/O exception occurs.
     */
    public TaskList loadTasksFromFile() throws IOException {
        // Create parent folders & file if they don't exist yet
        Files.createDirectories(this.path.getParent());
        if (Files.notExists(this.path)) {
            Files.createFile(this.path);
        }

        // Read and parse file accordingly
        TaskList taskList = new TaskList();
        Scanner scanner = new Scanner(this.path);
        while (scanner.hasNext()) {
            taskList.add(TaskFactory.deserialize(scanner.nextLine()));
        }
        return taskList;
    }

}
