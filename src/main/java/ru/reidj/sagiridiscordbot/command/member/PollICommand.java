package ru.reidj.sagiridiscordbot.command.member;

import lombok.val;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import ru.reidj.sagiridiscordbot.command.ICommand;

import java.awt.*;
import java.io.File;

public class PollICommand extends ListenerAdapter implements ICommand {

    @Override
    public File getPath() {
        return null;
    }

    @Override
    public String getCommand() {
        return "!poll";
    }

    @Override
    public String getHelp() {
        return null;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        val channel = e.getChannel();
        val message = e.getMessage();

        if (message.getContentRaw().startsWith(getCommand())) {
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
