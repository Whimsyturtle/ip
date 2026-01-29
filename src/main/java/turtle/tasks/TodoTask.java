package turtle.tasks;

public class TodoTask extends Task {

    public TodoTask(String name) {
        this(name, false);
    }

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
