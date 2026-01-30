package turtle.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** Task which represents an event, and additionally stores a start and end date. */
public class EventTask extends Task {

    protected final LocalDate fromDate;
    protected final LocalDate toDate;

    /**
     * Creates an event task with the specified name, start date, and end date, that is initially marked as not done.
     *
     * @param name Task name.
     * @param fromDate Task start date.
     * @param toDate Task end date.
     */
    public EventTask(String name, LocalDate fromDate, LocalDate toDate) {
        this(name, false, fromDate, toDate);
    }

    /**
     * Creates an event task with the specified name, start date, end date, and done status.
     *
     * @param name Task name.
     * @param isDone Task done status.
     * @param fromDate Task start date.
     * @param toDate Task end date.
     */
    public EventTask(String name, boolean isDone, LocalDate fromDate, LocalDate toDate) {
        super(name, isDone);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ", to: " + this.toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String serialize() {
        return "event|name=" + this.name + "|isDone=" + this.isDone + "|fromDate=" + this.fromDate + "|toDate="
                + this.toDate;
    }

}
