package tasks;

public class EventTask extends Task {

    protected final String fromDateTime;
    protected final String toDateTime;

    public EventTask(String name, String fromDateTime, String toDateTime) {
        this(name, false, fromDateTime, toDateTime);
    }

    public EventTask(String name, boolean isDone, String fromDateTime, String toDateTime) {
        super(name, isDone);
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.fromDateTime
                + ", to: " + this.toDateTime + ")";
    }

    @Override
    public String serialize() {
        return "event|name=" + this.name + "|isDone=" + this.isDone
                + "|fromDateTime=" + this.fromDateTime + "|toDateTime=" + this.toDateTime;
    }

}
