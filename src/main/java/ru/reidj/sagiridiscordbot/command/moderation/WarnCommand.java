package ru.reidj.sagiridiscordbot.command.moderation;

import lombok.val;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.Objects;

public class WarnCommand extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        val message = e.getMessage();
        val channel = e.getChannel();
        String[] messages = message.getContentRaw().split(" ");

        if (messages[0].equalsIgnoreCase("!warn")) {
            channel.deleteMessageById(message.getId()).queue();
            if (Objects.requireNonNull(e.getMember()).hasPermission(Permission.MANAGE_CHANNEL))
                for (Member member : e.getMessage().getMentionedMembers()) {
                    val warnMessage = new EmbedBuilder();

                    warnMessage.setColor(Color.red);
                    warnMessage.setTitle("Участник " + member.getAsMention().replace(member.getAsMention(), member.getEffectiveName()) + " получил предупреждение!");
                    warnMessage.setDescription("Причина: " + messages[2] + "\n");

                    channel.sendMessage(warnMessage.build()).queue();
                }
        }
    }
}