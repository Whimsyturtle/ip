package tasks;

public class Task {

    protected final String name;
    protected boolean isDone;

    public Task(String name) {
        this(name, false);
    }

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

    @Override
    public String toString() {
        return this.getDoneStatusString() + " " + this.name;
    }

    public String serialize() {
        return "base|name=" + this.name + "|isDone=" + this.isDone;
    }

}
