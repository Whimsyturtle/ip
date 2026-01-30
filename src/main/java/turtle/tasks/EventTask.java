package turtle.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {

    protected final LocalDate fromDateTime;
    protected final LocalDate toDateTime;

    public EventTask(String name, LocalDate fromDateTime, LocalDate toDateTime) {
        this(name, false, fromDateTime, toDateTime);
    }

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
