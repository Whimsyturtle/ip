package turtle.core.commands;

import turtle.core.Chatbot;

/** ListCommand represents the command `list` */
public class ListCommand extends Command {

    /**
     * Lists all tasks stored in the Turtle Chatbot's task list.
     *
     * @param bot Turtle Chatbot.
     */
    @Override
    public void executeCommand(Chatbot bot) {
        bot.list();
    }

}
