package turtle.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class TaskFactoryTest {

    @Test
    public void deserialize_todoTaskNotDoneValid_success() {
        Task t = TaskFactory.deserialize("todo|name=abc def|isDone=false");
        assertInstanceOf(TodoTask.class, t);
        assertEquals("[T][ ] abc def", t.toString());
    }

    @Test
    public void deserialize_todoTaskDoneValid_success() {
        Task t = TaskFactory.deserialize("todo|name=abc def|isDone=true");
        assertInstanceOf(TodoTask.class, t);
        assertEquals("[T][X] abc def", t.toString());
    }

    @Test
    public void deserialize_todoTaskMissingName_exceptionThrown() {
        try {
            TaskFactory.deserialize("todo|isDone=true");
            fail();
        } catch (IllegalStateException e) {
            assertEquals("Unable to deserialize TodoTask: missing name", e.getMessage());
        }
    }

    @Test
    public void deserialize_todoTaskMissingIsDone_exceptionThrown() {
        try {
            TaskFactory.deserialize("todo|name=abc def");
            fail();
        } catch (IllegalStateException e) {
            assertEquals("Unable to deserialize TodoTask: missing isDone", e.getMessage());
        }
    }

    @Test
    public void deserialize_deadlineTaskNotDoneValid_success() {
        Task t = TaskFactory.deserialize("deadline|name=abc def|isDone=false|deadline=2021-12-31");
        assertInstanceOf(DeadlineTask.class, t);
        assertEquals("[D][ ] abc def (by: Dec 31 2021)", t.toString());
    }

    @Test
    public void deserialize_deadlineTaskDoneValid_success() {
        Task t = TaskFactory.deserialize("deadline|name=abc def|isDone=true|deadline=2021-12-31");
        assertInstanceOf(DeadlineTask.class, t);
        assertEquals("[D][X] abc def (by: Dec 31 2021)", t.toString());
    }

    @Test
    public void deserialize_deadlineTaskMissingName_exceptionThrown() {
        try {
            TaskFactory.deserialize("deadline|isDone=true|deadline=2021-12-31");
            fail();
        } catch (IllegalStateException e) {
            assertEquals("Unable to deserialize DeadlineTask: missing name", e.getMessage());
        }
    }

    @Test
    public void deserialize_deadlineTaskMissingIsDone_exceptionThrown() {
        try {
            TaskFactory.deserialize("deadline|name=abc def|deadline=2021-12-31");
            fail();
        } catch (IllegalStateException e) {
            assertEquals("Unable to deserialize DeadlineTask: missing isDone", e.getMessage());
        }
    }

    @Test
    public void deserialize_deadlineTaskMissingDeadline_exceptionThrown() {
        try {
            TaskFactory.deserialize("deadline|name=abc def|isDone=true");
            fail();
        } catch (IllegalStateException e) {
            assertEquals("Unable to deserialize DeadlineTask: missing deadline", e.getMessage());
        }
    }

    @Test
    public void deserialize_deadlineTaskInvalidDeadline_exceptionThrown() {
        try {
            TaskFactory.deserialize("deadline|name=abc def|isDone=false|deadline=pqr");
            fail();
        } catch (DateTimeParseException e) {
            assertEquals("Text 'pqr' could not be parsed at index 0", e.getMessage());
        }
    }

    @Test
    public void deserialize_eventTaskNotDoneValid_success() {
        Task t = TaskFactory.deserialize("event|name=abc def|isDone=false|fromDate=2021-12-31|toDate=2022-02-28");
        assertInstanceOf(EventTask.class, t);
        assertEquals("[E][ ] abc def (from: Dec 31 2021, to: Feb 28 2022)", t.toString());
    }

    @Test
    public void deserialize_eventTaskDoneValid_success() {
        Task t = TaskFactory.deserialize("event|name=abc def|isDone=true|fromDate=2021-12-31|toDate=2022-02-28");
        assertInstanceOf(EventTask.class, t);
        assertEquals("[E][X] abc def (from: Dec 31 2021, to: Feb 28 2022)", t.toString());
    }

    @Test
    public void deserialize_eventTaskMissingName_exceptionThrown() {
        try {
            TaskFactory.deserialize("event|isDone=true|fromDate=2021-12-31|toDate=2022-02-28");
            fail();
        } catch (IllegalStateException e) {
            assertEquals("Unable to deserialize EventTask: missing name", e.getMessage());
        }
    }

    @Test
    public void deserialize_eventTaskMissingIsDone_exceptionThrown() {
        try {
            TaskFactory.deserialize("event|name=abc def|fromDate=2021-12-31|toDate=2022-02-28");
            fail();
        } catch (IllegalStateException e) {
            assertEquals("Unable to deserialize EventTask: missing isDone", e.getMessage());
        }
    }

    @Test
    public void deserialize_eventTaskMissingFromDate_exceptionThrown() {
        try {
            TaskFactory.deserialize("event|name=abc def|isDone=true|toDate=2022-02-28");
            fail();
        } catch (IllegalStateException e) {
            assertEquals("Unable to deserialize EventTask: missing fromDate", e.getMessage());
        }
    }

    @Test
    public void deserialize_eventTaskMissingToDate_exceptionThrown() {
        try {
            TaskFactory.deserialize("event|name=abc def|isDone=true|fromDate=2021-12-31");
            fail();
        } catch (IllegalStateException e) {
            assertEquals("Unable to deserialize EventTask: missing toDate", e.getMessage());
        }
    }

    @Test
    public void deserialize_deadlineTaskInvalidFromDate_exceptionThrown() {
        try {
            TaskFactory.deserialize("event|name=abc def|isDone=true|fromDate=pqr|toDate=2022-02-28");
            fail();
        } catch (DateTimeParseException e) {
            assertEquals("Text 'pqr' could not be parsed at index 0", e.getMessage());
        }
    }

    @Test
    public void deserialize_deadlineTaskInvalidToDate_exceptionThrown() {
        try {
            TaskFactory.deserialize("event|name=abc def|isDone=true|fromDate=2021-12-31|toDate=pqr");
            fail();
        } catch (DateTimeParseException e) {
            assertEquals("Text 'pqr' could not be parsed at index 0", e.getMessage());
        }
    }

}
