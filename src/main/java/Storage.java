import tasks.TaskFactory;
import tasks.TaskList;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Storage {

    private final Path path;

    public Storage(Path path) {
        this.path = path;
    }

    public void saveTasksToFile(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(this.path.toFile());
        for (int i = 0; i < taskList.size(); i++) {
            fw.write(taskList.get(i).serialize() + "\n");
        }
        fw.close();
    }

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
