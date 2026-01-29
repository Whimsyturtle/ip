package turtle.tasks;

import java.time.LocalDate;

public class TaskFactory {

    public static Task deserialize(String serializedStr) {
        String[] parts = serializedStr.split("\\|");
        if (parts.length == 0) {
            throw new IllegalStateException("Unable to deserialize Task: can't determine type");
        }
        String taskType = parts[0];
        if (taskType.equals("base")) {
            return deserializeBaseTask(parts);
        } else if (taskType.equals("todo")) {
            return deserializeTodoTask(parts);
        } else if (taskType.equals("deadline")) {
            return deserializeDeadlineTask(parts);
        } else if (taskType.equals("event")) {
            return deserializeEventTask(parts);
        } else {
            throw new IllegalStateException("Unable to deserialize Task: unknown type " + taskType);
        }
    }

    private static Task deserializeBaseTask(String[] parts) {
        String name = null;
        Boolean isDone = null;
        for (int i = 1; i < parts.length; i++) {
            String[] kvPair = parts[i].split("=");
            if (kvPair.length != 2) {
                throw new IllegalStateException("Unable to deserialize BaseTask: invalid kvPair " + parts[i]);
            }
            String key = kvPair[0];
            String value = kvPair[1];
            if (key.equals("name")) {
                name = value;
            } else if (key.equals("isDone")) {
                isDone = Boolean.valueOf(value);
            } else {
                throw new IllegalStateException("Unable to deserialize BaseTask: invalid key " + key);
            }
        }
        if (name == null) {
            throw new IllegalStateException("Unable to deserialize BaseTask: missing name");
        }
        if (isDone == null) {
            throw new IllegalStateException("Unable to deserialize BaseTask: missing isDone");
        }
        return new Task(name, isDone);
    }

    private static Task deserializeTodoTask(String[] parts) {
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
        String name = null;
        Boolean isDone = null;
        String fromDateTime = null;
        String toDateTime = null;
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
            } else if (key.equals("fromDateTime")) {
                fromDateTime = value;
            } else if (key.equals("toDateTime")) {
                toDateTime = value;
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
        if (fromDateTime == null) {
            throw new IllegalStateException("Unable to deserialize EventTask: missing fromDateTime");
        }
        if (toDateTime == null) {
            throw new IllegalStateException("Unable to deserialize EventTask: missing toDateTime");
        }
        // TODO: Custom error message for LocalDate.parse()?
        LocalDate parsedFromDateTime = LocalDate.parse(fromDateTime);
        LocalDate parsedToDateTime = LocalDate.parse(toDateTime);
        return new EventTask(name, isDone, parsedFromDateTime, parsedToDateTime);
    }

}
