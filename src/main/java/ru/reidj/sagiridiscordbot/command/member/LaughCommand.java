package ru.reidj.sagiridiscordbot.command.member;

import lombok.val;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.File;

public class LaughCommand extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        val message = e.getMessage();
        val channel = e.getChannel();

        if (message.getContentRaw().startsWith("!laugh")) {
            channel.deleteMessageById(message.getId()).queue();
            channel.sendMessage(e.getAuthor().getAsMention() + " посмеялся(ась)").queue();
            channel.sendFile(new File("src/main/resources/image.png")).queue();
        }
    }
}
