package turtle.core.commands;

import turtle.core.Chatbot;

public class ListCommand extends Command {

    @Override
    public void executeCommand(Chatbot bot) {
        bot.list();
    }

}
