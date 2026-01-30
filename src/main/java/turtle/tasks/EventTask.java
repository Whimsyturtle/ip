package turtle.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// TODO: Fix variable naming
/** Task which represents an event, and additionally stores a start and end date. */
public class EventTask extends Task {

    protected final LocalDate fromDateTime;
    protected final LocalDate toDateTime;

    /**
     * Creates an event task with the specified name, start date, and end date, that is initially marked as not done.
     *
     * @param name Task name.
     * @param fromDateTime Task start date.
     * @param toDateTime Task end date.
     */
    public EventTask(String name, LocalDate fromDateTime, LocalDate toDateTime) {
        this(name, false, fromDateTime, toDateTime);
    }

    /**
     * Creates an event task with the specified name, start date, end date, and done status.
     *
     * @param name Task name.
     * @param isDone Task done status.
     * @param fromDateTime Task start date.
     * @param toDateTime Task end date.
     */
    public EventTask(String name, boolean isDone, LocalDate fromDateTime, LocalDate toDateTime) {
        super(name, isDone);
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + this.fromDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", to: "
                + this.toDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String serialize() {
        return "event|name=" + this.name + "|isDone=" + this.isDone + "|fromDateTime=" + this.fromDateTime
                + "|toDateTime=" + this.toDateTime;
    }

}
