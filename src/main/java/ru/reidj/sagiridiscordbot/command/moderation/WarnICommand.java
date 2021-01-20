package ru.reidj.sagiridiscordbot.command.moderation;

import lombok.val;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import ru.reidj.sagiridiscordbot.command.ICommand;

import java.awt.*;
import java.io.File;
import java.util.Objects;

public class WarnICommand extends ListenerAdapter implements ICommand {

    @Override
    public File getPath() {
        return null;
    }

    @Override
    public String getCommand() {
        return "!warn";
    }

    @Override
    public String getHelp() {
        return null;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        val message = e.getMessage();
        val channel = e.getChannel();
        String[] messages = message.getContentRaw().split(" ");

        try {
            if (messages[0].equalsIgnoreCase(getCommand())) {
                channel.deleteMessageById(message.getId()).queue();
                if (Objects.requireNonNull(e.getMember()).hasPermission(Permission.MANAGE_CHANNEL))
                    for (Member member : e.getMessage().getMentionedMembers()) {
                        val warnMessage = new EmbedBuilder();

                        warnMessage.setColor(Color.red);
                        warnMessage.setTitle("Участник(-ца) " + member.getAsMention().replace(member.getAsMention(), member.getEffectiveName()) + " получил(-а) предупреждение!");
                        warnMessage.setDescription("Причина: " + messages[2] + "\n");

                        channel.sendMessage(warnMessage.build()).queue();
                    }
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            channel.sendMessage("Синтаксическая ошибка! Используйте !warn @имя причина").queue();
        }
    }
}
