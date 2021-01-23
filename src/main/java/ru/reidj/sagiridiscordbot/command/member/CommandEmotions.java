package ru.reidj.sagiridiscordbot.command.member;

import lombok.val;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;

public class CommandEmotions extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent e) {
        val message = e.getMessage();
        val channel = e.getChannel();
        val member = e.getMember();
        String[] messages = message.getContentRaw().split(" ");

        for (val commands : Commands.values()) {
            if (messages[0].equalsIgnoreCase(commands.getCommand())) {
                channel.deleteMessageById(message.getId()).queue();
                if (commands.isMention() == Boolean.FALSE) {
                    channel.sendMessage(Objects.requireNonNull(member).getAsMention() + commands.getMessage()).queue();
                    channel.sendFile(commands.getPath()).queue();
                } else
                    for (val mention : message.getMentionedMembers()) {
                        channel.sendMessage(Objects.requireNonNull(member).getAsMention() + commands.getMessage() + mention.getAsMention()).queue();
                        channel.sendFile(commands.getPath()).queue();
                    }
            }
        }
    }
}
