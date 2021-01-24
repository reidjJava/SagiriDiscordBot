package ru.reidj.sagiridiscordbot.command.moderation;

import lombok.val;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class ModerationCommand extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        val message = e.getMessage();
        val channel = e.getChannel();
        val member = e.getMember();
        String[] messages = e.getMessage().getContentRaw().split(" ");

        onKick(message, channel, member);
        onMute(message, channel, e.getGuild());
        onWarn(message, channel, member);

        try {
            if (messages[0].equalsIgnoreCase("!ban")) {
                if (Objects.requireNonNull(e.getMember()).hasPermission(Permission.BAN_MEMBERS)) {
                    channel.deleteMessageById(message.getId()).queue();

                    message.getMentionedMembers().forEach(asMention -> {
                        channel.sendMessage(asMention.getAsMention() + " был(-а) заблокирован(-а) на " + messages[2] + " день(-ней)!").queue();
                        asMention.ban(Integer.parseInt(messages[2])).complete();
                    });
                }
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            channel.sendMessage("Синтаксическая ошибка! Используйте !ban @имя время(в секундах)").queue();
        }
    }

    private void onKick(Message message, MessageChannel channel, Member member) {
        if (message.getContentRaw().startsWith("!kick")) {
            channel.deleteMessageById(message.getId()).queue();
            if (Objects.requireNonNull(member).hasPermission(Permission.KICK_MEMBERS)) {

                message.getMentionedMembers().forEach(asMention -> {
                    channel.sendMessage(asMention.getAsMention() + " был(-а) выгнан(-а) с сервера!").queue();
                    asMention.kick().queue();
                });

            }
        }
    }

    private void onMute(Message message, MessageChannel channel, Guild guild) {
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

    private void onWarn(Message message, MessageChannel channel, Member member) {
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
