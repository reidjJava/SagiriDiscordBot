package ru.reidj.sagiridiscordbot.command.member;

import lombok.val;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.File;

public class HugCommand extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        val message = e.getMessage();
        val channel = e.getChannel();

        if (message.getContentRaw().startsWith("!hug")) {
            channel.deleteMessageById(message.getId()).queue();
            for (Member member : e.getMessage().getMentionedMembers()) {
                channel.sendMessage(e.getAuthor().getAsMention() + " обнял(а) " + member.getAsMention()).queue();
                channel.sendFile(new File("src/main/resources/image.png")).queue();
            }
        }
    }
}