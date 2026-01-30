package turtle.tasks;

/** Task represents a named item which can be marked as done. */
public class Task {

    protected final String name;
    protected boolean isDone;

    /**
     * Creates a task with the specified name, that is initially marked as not done.
     *
     * @param name Task name.
     */
    public Task(String name) {
        this(name, false);
    }

    /**
     * Creates a task with the specified name and done status.
     *
     * @param name Task name.
     * @param isDone Task done status.
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    private String getDoneStatusString() {
        return this.isDone ? "[X]" : "[ ]";
    }

    /**
     * Returns a pretty-print representation of the Task, intended for the user to view.
     *
     * @return Pretty-print representation of the task.
     */
    @Override
    public String toString() {
        return this.getDoneStatusString() + " " + this.name;
    }

    /**
     * Returns a machine-readable representation of the Task, intended for saving/loading tasks using Storage.
     *
     * @return Machine-readable representation of the task.
     */
    public String serialize() {
        return "base|name=" + this.name + "|isDone=" + this.isDone;
    }

}
