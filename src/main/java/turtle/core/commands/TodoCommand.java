package turtle.core.commands;

import java.util.Arrays;

import turtle.core.Chatbot;
import turtle.exceptions.CommandTurtleException;
import turtle.tasks.TodoTask;

public class TodoCommand extends Command {

    private final TodoTask task;

    public TodoCommand(String[] sections, String correctSyntax) throws CommandTurtleException {
        super(sections, correctSyntax);
        if (sections.length == 1) {
            throw new CommandTurtleException("Invalid syntax", correctSyntax);
        }
        String remainingStr = String.join(" ", Arrays.copyOfRange(sections, 1, sections.length));
        this.task = new TodoTask(remainingStr);
    }

    @Override
    public void executeCommand(Chatbot bot) {
        bot.addTask(task);
    }

}
