package turtle.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** Task which represents a deadline, and additionally stores a deadline date. */
public class DeadlineTask extends Task {

    protected final LocalDate deadline;

    /**
     * Creates a deadline task with the specified name and deadline date, that is initially marked as not done.
     *
     * @param name Task name.
     * @param deadline Task deadline date.
     */
    public DeadlineTask(String name, LocalDate deadline) {
        this(name, false, deadline);
    }

    /**
     * Creates a deadline task with the specified name, deadline date, and done status.
     *
     * @param name Task name.
     * @param isDone Task done status.
     * @param deadline Task deadline date.
     */
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
