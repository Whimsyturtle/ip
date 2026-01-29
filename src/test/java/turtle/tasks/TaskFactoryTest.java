package turtle.tasks;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskFactoryTest {

    @Test
    public void deserialize_todoTaskValid_success() {
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
    public void deserialize_deadlineTaskValid_success() {
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

}
