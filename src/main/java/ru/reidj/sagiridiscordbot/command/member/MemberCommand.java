package ru.reidj.sagiridiscordbot.command.member;

import lombok.val;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import ru.reidj.sagiridiscordbot.command.ICommand;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MemberCommand extends ListenerAdapter {
    private final Map<String, ICommand> commands = new HashMap<>();

    public MemberCommand() {
        commands.put("!avatar", new AvatarICommand());
        commands.put("!bal", new BalanceICommand());
        commands.put("!buy", new BuyICommand());
        commands.put("!daily", new DailyICommand());
        commands.put("!poll", new PollICommand());
        commands.put("!setbal", new SetBalanceICommand());
        commands.put("!takebal", new TakeBalanceICommand());
        commands.put("!zxc", new ZxcICommand());
    }

    public void onMessageReceived(MessageReceivedEvent e) {
        val message = e.getMessage();
        val channel = e.getChannel();
        val member = e.getMember();
        String[] messages = message.getContentRaw().split(" ");

        if (commands.containsKey(messages[0]))
            commands.get(messages[0]).onMessageReceived(e);

        for (val commands : Commands.values()) {
            if (messages[0].equalsIgnoreCase(commands.getCommand())) {
                channel.deleteMessageById(message.getId()).queue();
                if (commands.isMention() == Boolean.FALSE) {
                    channel.sendMessage(Objects.requireNonNull(member).getAsMention() + commands.getMessage()).queue();
                    channel.sendFile(commands.getPath()).queue();
                } else
                    message.getMentionedMembers().forEach(mention -> {
                        channel.sendMessage(Objects.requireNonNull(member).getAsMention() + commands.getMessage() + mention.getAsMention()).queue();
                        channel.sendFile(commands.getPath()).queue();
                    });
            }
        }
    }
}
