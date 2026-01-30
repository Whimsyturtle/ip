package turtle.tasks;

/** Task which represents a todo. */
public class TodoTask extends Task {

    /**
     * Creates a todo task with the specified name, that is initially marked as not done.
     *
     * @param name Task name.
     */
    public TodoTask(String name) {
        this(name, false);
    }

    /**
     * Creates a todo task with the specified name, and done status.
     *
     * @param name Task name.
     * @param isDone Task done status.
     */
    public TodoTask(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String serialize() {
        return "todo|name=" + this.name + "|isDone=" + this.isDone;
    }

}
