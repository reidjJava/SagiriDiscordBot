package ru.reidj.sagiridiscordbot.command;

import lombok.val;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import ru.reidj.sagiridiscordbot.command.member.*;

import java.util.HashMap;
import java.util.Map;

public class CommandManager extends ListenerAdapter {
    private final Map<String, ICommand> commandMap = new HashMap<>();

    public CommandManager() {
        addCommand(new AvatarICommand());
        addCommand(new DailyICommand());
        addCommand(new PollICommand());
        addCommand(new BalanceICommand());
        addCommand(new SetBalanceICommand());
        addCommand(new TakeBalanceICommand());
        addCommand(new BuyICommand());
        addCommand(new ZxcICommand());
    }

    private void addCommand(ICommand command) {
        if (!commandMap.containsKey(command.getCommand()))
            commandMap.put(command.getCommand(), command);
    }

    public void onMessageReceived(MessageReceivedEvent e) {
        String[] message = e.getMessage().getContentRaw().split(" ");
        val command = message[0].toLowerCase();

        if (commandMap.containsKey(command))
            commandMap.get(command).onMessageReceived(e);
    }
}
