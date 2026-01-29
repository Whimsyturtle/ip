package turtle.tasks;

import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void add(Task task) {
        this.list.add(task);
    }

    public Task get(int idx) {
        return this.list.get(idx);
    }

    public Task remove(int idx) {
        return this.list.remove(idx);
    }

    public int size() {
        return this.list.size();
    }

    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < this.list.size(); i++) {
            Task task = this.list.get(i);
            res += i+1 + ". " + task.toString() + "\n";
        }
        return res;
    }

}
