package ru.reidj.sagiridiscordbot.command.moderation;

import lombok.val;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import ru.reidj.sagiridiscordbot.command.ICommand;

import java.util.Objects;

public class KickICommand implements ICommand {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        val message = e.getMessage();
        val channel = e.getChannel();
        val member = e.getMember();

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
}
