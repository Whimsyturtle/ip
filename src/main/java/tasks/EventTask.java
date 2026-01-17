package tasks;

public class EventTask extends Task {

    protected final String fromDateTime;
    protected final String toDateTime;

    public EventTask(String name, String fromDateTime, String toDateTime) {
        super(name);
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.fromDateTime
                + ", to: " + this.toDateTime + ")";
    }

}
