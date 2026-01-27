package tasks;

public class DeadlineTask extends Task {

    protected final String deadline;

    public DeadlineTask(String name, String deadline) {
        this(name, false, deadline);
    }

    public DeadlineTask(String name, boolean isDone, String deadline) {
        super(name, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }

    @Override
    public String serialize() {
        return "deadline|name=" + this.name + "|isDone=" + this.isDone + "|deadline=" + this.deadline;
    }

}
