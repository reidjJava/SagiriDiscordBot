package ru.reidj.sagiridiscordbot.command.moderation;

import lombok.val;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import ru.reidj.sagiridiscordbot.command.ICommand;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class MuteICommand implements ICommand {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        val message = e.getMessage();
        val channel = e.getChannel();
        val guild = e.getGuild();
        String[] messages = message.getContentRaw().split(" ");

        try {
            if (messages[0].equalsIgnoreCase("!mute")) {
                channel.deleteMessageById(message.getId()).queue();

                message.getMentionedMembers().forEach(asMention -> {
                    val role = guild.getRoleById("800029682572984320");

                    channel.sendMessage("Участнику(-це) " + asMention.getAsMention() + " было запрещено писать в чат на " + messages[2] + " секунд!").queue();
                    guild.addRoleToMember(asMention, Objects.requireNonNull(guild.getRoleById("800029682572984320"))).complete();

                    new Timer().schedule(
                            new TimerTask() {
                                @Override
                                public void run() {
                                    channel.sendMessage(messages[1] + " теперь снова может писать в чат!").queue();
                                    if (role != null)
                                        guild.removeRoleFromMember(asMention, role).complete();
                                }
                            }, Integer.parseInt(messages[2]) * 1000
                    );
                });
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            channel.sendMessage("Синтаксическая ошибка! Используйте !mute @имя время(в секундах)").queue();
        }
    }
}
