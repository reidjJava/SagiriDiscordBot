package ru.reidj.sagiridiscordbot.command.moderation;

import lombok.val;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;

public class BanCommand extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        val message = e.getMessage();
        val channel = e.getChannel();
        val guild = e.getGuild();
        String[] messages = e.getMessage().getContentRaw().split(" ");

        /*if (message.getContentRaw().startsWith("!ban")) {
            channel.deleteMessageById(message.getId()).queue();
            if (Objects.requireNonNull(e.getMember()).hasPermission(Permission.BAN_MEMBERS)) {
                for (Member member : e.getMessage().getMentionedMembers()) {
                    channel.sendMessage(member.getAsMention() + " был(а) забанен(а) на сервере!").queue();
                    member.ban(1).queue();
                }
            }
        }*/

        try {
            if (messages[0].equalsIgnoreCase("!ban")) {
                if (Objects.requireNonNull(e.getMember()).hasPermission(Permission.BAN_MEMBERS)) {
                    channel.deleteMessageById(message.getId()).queue();
                    for (Member member : e.getMessage().getMentionedMembers()) {
                        channel.sendMessage(member.getAsMention() + " был(а) заблокирован(а) на " + messages[2] + " день(ней)!").queue();
                        member.ban(Integer.parseInt(messages[2])).complete();
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            channel.sendMessage("Синтаксическая ошибка! Используйте !mute @имя время(в секундах)").queue();
        }
    }
}