package ru.reidj.sagiridiscordbot.command.member;

import lombok.val;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class PollCommand extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        val channel = e.getChannel();
        val message = e.getMessage();

        if (message.getContentRaw().startsWith("!poll")) {
            channel.deleteMessageById(message.getId()).queue();

            val pollMessage = new EmbedBuilder();

            pollMessage.setColor(Color.YELLOW);
            pollMessage.setTitle("Опрос :pencil:");
            pollMessage.setDescription(message.getContentRaw().replace("!poll", ""));
            pollMessage.setFooter("\nОпрос провёл " + e.getAuthor().getName());

            channel.sendMessage(pollMessage.build()).queue(reaction -> {
                reaction.addReaction("\u2705").queue();
                reaction.addReaction("\u274C").queue();
            });
        }
    }
}
