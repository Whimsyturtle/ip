package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {

    protected final LocalDate deadline;

    public DeadlineTask(String name, LocalDate deadline) {
        this(name, false, deadline);
    }

    public DeadlineTask(String name, boolean isDone, LocalDate deadline) {
        super(name, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String serialize() {
        return "deadline|name=" + this.name + "|isDone=" + this.isDone + "|deadline=" + this.deadline;
    }

}
