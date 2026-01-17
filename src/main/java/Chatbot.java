public class Chatbot {

    private final TaskList taskList;

    public Chatbot() {
        this.taskList = new TaskList();
    }

    public void greet() {
        System.out.println("Hello! My name is Turtle.\nWhat can I do for you?\n");
    }

    public void echo(String userCommand) {
        Task newTask = new Task(userCommand);
        taskList.add(newTask);
        System.out.println("Added: " + userCommand + "\n");
    }

    public void list() {
        System.out.println(this.taskList);
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

}
