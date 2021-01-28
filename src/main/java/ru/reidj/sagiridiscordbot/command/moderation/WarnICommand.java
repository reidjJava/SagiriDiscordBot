package ru.reidj.sagiridiscordbot.command.moderation;

import lombok.val;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import ru.reidj.sagiridiscordbot.command.ICommand;

import java.awt.*;
import java.util.Objects;

public class WarnICommand implements ICommand {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        val message = e.getMessage();
        val channel = e.getChannel();
        val member = e.getMember();
        String[] messages = message.getContentRaw().split(" ");

        try {
            if (messages[0].equalsIgnoreCase("!warn")) {
                channel.deleteMessageById(message.getId()).queue();
                if (Objects.requireNonNull(member).hasPermission(Permission.MANAGE_CHANNEL))
                    message.getMentionedMembers().forEach(asMention -> {
                        val warnMessage = new EmbedBuilder();

                        warnMessage.setColor(Color.red);
                        warnMessage.setTitle("Участник(-ца) " + asMention.getAsMention().replace(asMention.getAsMention(), asMention.getEffectiveName()) + " получил(-а) предупреждение!");
                        warnMessage.setDescription("Причина: " + messages[2] + "\n");

                        channel.sendMessage(warnMessage.build()).queue();
                    });
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            channel.sendMessage("Синтаксическая ошибка! Используйте !warn @имя причина").queue();
        }
    }
}
