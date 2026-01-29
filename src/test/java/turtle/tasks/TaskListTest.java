package turtle.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// TODO: Use stub for Task, .add(), .remove(), etc?
// TODO: Remaining TaskList methods
public class TaskListTest {

    @Test
    public void get_emptyList_exceptionThrown() {
        TaskList tl = new TaskList();
        try {
            tl.get(0);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index 0 out of bounds for length 0", e.getMessage());
        }
    }

    @Test
    public void get_nonEmptyListInvalidIndex_exceptionThrown() {
        TaskList tl = new TaskList();
        for (int i = 0; i < 5; i++) {
            tl.add(new Task("Task" + i));
        }
        try {
            tl.get(6);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index 6 out of bounds for length 5", e.getMessage());
        }
    }

    @Test
    public void get_nonEmptyListValidIndices_correctTasks() {
        TaskList tl = new TaskList();
        Task[] tasks = new Task[5];
        for (int i = 0; i < 5; i++) {
            tasks[i] = new Task("Task" + i);
            tl.add(tasks[i]);
        }
        for (int i = 0; i < 5; i++) {
            assertEquals(tasks[i], tl.get(i));
        }
    }

    @Test
    public void remove_emptyList_exceptionThrown() {
        TaskList tl = new TaskList();
        try {
            tl.remove(0);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index 0 out of bounds for length 0", e.getMessage());
        }
    }

    @Test
    public void remove_nonEmptyListInvalidIndex_exceptionThrown() {
        TaskList tl = new TaskList();
        for (int i = 0; i < 5; i++) {
            tl.add(new Task("Task" + i));
        }
        try {
            tl.remove(6);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index 6 out of bounds for length 5", e.getMessage());
        }
    }

    @Test
    public void remove_nonEmptyListValidIndices_correctTasks() {
        TaskList tl = new TaskList();
        Task[] tasks = new Task[5];
        for (int i = 0; i < 5; i++) {
            tasks[i] = new Task("Task" + i);
            tl.add(tasks[i]);
        }
        tl.remove(0); // Remove Task0, i.e. tl = [Task1, Task2, Task3, Task4]
        tl.remove(1); // Remove Task2, i.e. tl = [Task1, Task3, Task4]
        assertEquals(tasks[1], tl.get(0));
        assertEquals(tasks[3], tl.get(1));
        assertEquals(tasks[4], tl.get(2));
    }

    @Test
    public void size_emptyList_zeroReturned() {
        TaskList tl = new TaskList();
        assertEquals(0, tl.size());
    }

    @Test
    public void size_addFiveAndRemoveThree_twoReturned() {
        TaskList tl = new TaskList();
        for (int i = 0; i < 5; i++) {
            tl.add(new Task("Task" + i));
        }
        for (int i = 0; i < 3; i++) {
            tl.remove(0);
        }
        assertEquals(2, tl.size());
    }

}
