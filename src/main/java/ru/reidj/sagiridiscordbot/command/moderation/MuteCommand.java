package ru.reidj.sagiridiscordbot.command.moderation;

import lombok.val;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class MuteCommand extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        val message = e.getMessage();
        val channel = e.getChannel();
        val guild = e.getGuild();
        String[] messages = message.getContentRaw().split(" ");

            if (messages[0].equalsIgnoreCase("!mute")) {
                for (Member member : e.getMessage().getMentionedMembers()) {
                    val role = e.getGuild().getRoleById("800029682572984320");

                    channel.sendMessage("Участнику " + member.getAsMention() + " было запрещено писать в чат на " + messages[2] + " секунд").queue();
                    guild.addRoleToMember(member, Objects.requireNonNull(e.getGuild().getRoleById("800029682572984320"))).complete();

                    new Timer().schedule(
                            new TimerTask() {
                                @Override
                                public void run() {
                                    channel.sendMessage(messages[1] + " теперь снова может писать в чат!").queue();
                                    if (role != null)
                                        guild.removeRoleFromMember(member, role).complete();
                                }
                            }, Integer.parseInt(messages[2]) * 1000
                    );
                }
            }
        }
}
