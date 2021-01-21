package ru.reidj.sagiridiscordbot.command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import ru.reidj.sagiridiscordbot.command.member.*;
import ru.reidj.sagiridiscordbot.command.moderation.*;

import java.util.HashMap;
import java.util.Map;

public class CommandManager extends ListenerAdapter {
    private final Map<String, ICommand> commandMap = new HashMap<>();

    public CommandManager() {
        addCommand(new AngryICommand());
        addCommand(new AvatarICommand());
        addCommand(new CryICommand());
        addCommand(new DailyCommand());
        addCommand(new DidderICommand());
        addCommand(new FacePawnICommand());
        addCommand(new GoodbyeICommand());
        addCommand(new HelloICommand());
        addCommand(new HighFiveICommand());
        addCommand(new HighFiveICommand());
        addCommand(new HugICommand());
        addCommand(new KissICommand());
        addCommand(new LaughICommand());
        addCommand(new PatICommand());
        addCommand(new PollICommand());
        addCommand(new SleepICommand());
        addCommand(new SorryICommand());
        addCommand(new ThreatenICommand());
        addCommand(new WowICommand());
        addCommand(new BanICommand());
        addCommand(new KickICommand());
        addCommand(new MuteICommand());
        addCommand(new WarnICommand());
        addCommand(new BalanceICommand());
        addCommand(new SendMessage());
        addCommand(new SetBalanceICommand());
        addCommand(new TakeBalanceICommand());
        addCommand(new SaveCommand());
    }

    private void addCommand(ICommand command) {
        if (!commandMap.containsKey(command.getCommand()))
            commandMap.put(command.getCommand(), command);
    }

    public void onMessageReceived(MessageReceivedEvent e) {
        String[] message = e.getMessage().getContentRaw().split(" ");
        String command = message[0].toLowerCase();

        if (commandMap.containsKey(command))
            commandMap.get(command).onMessageReceived(e);
    }
}
