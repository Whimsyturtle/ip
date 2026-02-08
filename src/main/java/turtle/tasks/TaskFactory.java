package turtle.tasks;

import java.time.LocalDate;

/** TaskFactory supports the creation of Task objects. */
public class TaskFactory {

    /**
     * Converts the given serialized task string into its corresponding task object, by parsing the task type first,
     * followed by each of the task's various components.
     *
     * @param serializedStr Serialized, machine-readable representation of the task.
     * @return Corresponding task object.
     */
    public static Task deserialize(String serializedStr) {
        assert serializedStr != null;
        String[] parts = serializedStr.split("\\|");
        assert parts.length > 0;
        String taskType = parts[0];
        return switch (taskType) {
            case "todo" -> deserializeTodoTask(parts);
            case "deadline" -> deserializeDeadlineTask(parts);
            case "event" -> deserializeEventTask(parts);
            default -> throw new IllegalStateException("Unable to deserialize Task: unknown type " + taskType);
        };
    }

    private static Task deserializeTodoTask(String[] parts) {
        assert parts != null;
        assert parts.length > 0;
        String name = null;
        Boolean isDone = null;
        for (int i = 1; i < parts.length; i++) {
            String[] kvPair = parts[i].split("=");
            if (kvPair.length != 2) {
                throw new IllegalStateException("Unable to deserialize TodoTask: invalid kvPair " + parts[i]);
            }
            String key = kvPair[0];
            String value = kvPair[1];
            if (key.equals("name")) {
                name = value;
            } else if (key.equals("isDone")) {
                isDone = Boolean.valueOf(value);
            } else {
                throw new IllegalStateException("Unable to deserialize TodoTask: invalid key " + key);
            }
        }
        if (name == null) {
            throw new IllegalStateException("Unable to deserialize TodoTask: missing name");
        }
        if (isDone == null) {
            throw new IllegalStateException("Unable to deserialize TodoTask: missing isDone");
        }
        return new TodoTask(name, isDone);
    }

    private static DeadlineTask deserializeDeadlineTask(String[] parts) {
        assert parts != null;
        assert parts.length > 0;
        String name = null;
        Boolean isDone = null;
        String deadline = null;
        for (int i = 1; i < parts.length; i++) {
            String[] kvPair = parts[i].split("=");
            if (kvPair.length != 2) {
                throw new IllegalStateException("Unable to deserialize DeadlineTask: invalid kvPair " + parts[i]);
            }
            String key = kvPair[0];
            String value = kvPair[1];
            if (key.equals("name")) {
                name = value;
            } else if (key.equals("isDone")) {
                isDone = Boolean.valueOf(value);
            } else if (key.equals("deadline")) {
                deadline = value;
            } else {
                throw new IllegalStateException("Unable to deserialize DeadlineTask: invalid key " + key);
            }
        }
        if (name == null) {
            throw new IllegalStateException("Unable to deserialize DeadlineTask: missing name");
        }
        if (isDone == null) {
            throw new IllegalStateException("Unable to deserialize DeadlineTask: missing isDone");
        }
        if (deadline == null) {
            throw new IllegalStateException("Unable to deserialize DeadlineTask: missing deadline");
        }
        // TODO: Custom error message for LocalDate.parse()?
        LocalDate parsedDeadline = LocalDate.parse(deadline);
        return new DeadlineTask(name, isDone, parsedDeadline);
    }

    private static EventTask deserializeEventTask(String[] parts) {
        assert parts != null;
        assert parts.length > 0;
        String name = null;
        Boolean isDone = null;
        String fromDate = null;
        String toDate = null;
        for (int i = 1; i < parts.length; i++) {
            String[] kvPair = parts[i].split("=");
            if (kvPair.length != 2) {
                throw new IllegalStateException("Unable to deserialize EventTask: invalid kvPair " + parts[i]);
            }
            String key = kvPair[0];
            String value = kvPair[1];
            if (key.equals("name")) {
                name = value;
            } else if (key.equals("isDone")) {
                isDone = Boolean.valueOf(value);
            } else if (key.equals("fromDate")) {
                fromDate = value;
            } else if (key.equals("toDate")) {
                toDate = value;
            } else {
                throw new IllegalStateException("Unable to deserialize EventTask: invalid key " + key);
            }
        }
        if (name == null) {
            throw new IllegalStateException("Unable to deserialize EventTask: missing name");
        }
        if (isDone == null) {
            throw new IllegalStateException("Unable to deserialize EventTask: missing isDone");
        }
        if (fromDate == null) {
            throw new IllegalStateException("Unable to deserialize EventTask: missing fromDate");
        }
        if (toDate == null) {
            throw new IllegalStateException("Unable to deserialize EventTask: missing toDate");
        }
        // TODO: Custom error message for LocalDate.parse()?
        LocalDate parsedFromDate = LocalDate.parse(fromDate);
        LocalDate parsedToDate = LocalDate.parse(toDate);
        return new EventTask(name, isDone, parsedFromDate, parsedToDate);
    }

}
