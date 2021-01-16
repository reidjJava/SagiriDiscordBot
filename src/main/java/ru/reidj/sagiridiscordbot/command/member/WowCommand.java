package ru.reidj.sagiridiscordbot.command.member;

import lombok.val;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.File;

public class WowCommand extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        val message = e.getMessage();
        val channel = e.getChannel();

        if (message.getContentRaw().startsWith("!wow")) {
            channel.deleteMessageById(message.getId()).queue();
            channel.sendMessage(e.getAuthor().getAsMention() + " удивился(ась)").queue();
            channel.sendFile(new File("src/main/resources/image.png")).queue();
        }
    }
}
