package turtle.tasks;

import java.util.ArrayList;

/** TaskList represents an ordered sequence of tasks. */
public class TaskList {

    private final ArrayList<Task> list;

    /**
     * Creates an initially empty task list.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Creates a pre-filled task list.
     *
     * @param list ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Adds the specified task to the back of the task list.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        assert task != null;
        this.list.add(task);
    }

    /**
     * Gets the task at the specified index of the task list.
     *
     * @param idx Index of the task to be retrieved.
     * @return Retrieved task.
     */
    public Task get(int idx) {
        return this.list.get(idx);
    }

    /**
     * Removes the task at the specified index of the task list.
     *
     * @param idx Index of the task to be removed.
     * @return Removed task.
     */
    public Task remove(int idx) {
        return this.list.remove(idx);
    }

    /**
     * Returns the current size of the task list.
     *
     * @return Size of task list.
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Makes a copy of the current task list, sorts it, and returns the sorted copy.
     *
     * @return Sorted task list.
     */
    public TaskList sort() {
        ArrayList<Task> sortedTasks = new ArrayList<>(this.list);
        sortedTasks.sort(null);
        return new TaskList(sortedTasks);
    }

    /**
     * Returns a pretty-print representation of the TaskList, intended for the user to view.
     *
     * @return Pretty-print representation of the task list.
     */
    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < this.list.size(); i++) {
            Task task = this.list.get(i);
            res += (i + 1) + ". " + task.toString() + "\n";
        }
        return res;
    }

}
