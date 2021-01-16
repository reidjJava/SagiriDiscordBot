package ru.reidj.sagiridiscordbot.command.moderation;

import lombok.val;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;

public class KickCommand extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        val message = e.getMessage();

        if (message.getContentRaw().startsWith("!kick")) {
            if (Objects.requireNonNull(e.getMember()).hasPermission(Permission.KICK_MEMBERS)) {
                for (Member member : e.getMessage().getMentionedMembers()) {
                    e.getChannel().sendMessage("Вы успешно выгнали " + member.getAsMention() + " с сервера").queue();
                    member.kick().queue();
                }
            }
        }
    }
}
