package ru.reidj.sagiridiscordbot.command.moderation;

import lombok.val;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import ru.reidj.sagiridiscordbot.command.ICommand;

import java.util.HashMap;
import java.util.Map;

public class ModerationCommand extends ListenerAdapter {
    private final Map<String, ICommand> commands = new HashMap<>();

    public ModerationCommand() {
        commands.put("!ban", new BanICommand());
        commands.put("!kick", new KickICommand());
        commands.put("!mute", new MuteICommand());
        commands.put("!warn", new WarnICommand());
    }

    public void onMessageReceived(MessageReceivedEvent e) {
        val message = e.getMessage();
        String[] messages = message.getContentRaw().split(" ");

        if (commands.containsKey(messages[0]))
            commands.get(messages[0]).onMessageReceived(e);
    }
}
