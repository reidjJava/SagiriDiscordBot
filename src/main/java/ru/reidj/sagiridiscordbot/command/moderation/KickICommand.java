package ru.reidj.sagiridiscordbot.command.moderation;

import lombok.val;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import ru.reidj.sagiridiscordbot.command.ICommand;

import java.io.File;
import java.util.Objects;

public class KickICommand extends ListenerAdapter implements ICommand {
    @Override
    public File getPath() {
        return null;
    }

    @Override
    public String getCommand() {
        return "!kick";
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        val message = e.getMessage();
        val channel = e.getChannel();

        if (message.getContentRaw().startsWith(getCommand())) {
            channel.deleteMessageById(message.getId()).queue();
            if (Objects.requireNonNull(e.getMember()).hasPermission(Permission.KICK_MEMBERS)) {
                for (Member member : e.getMessage().getMentionedMembers()) {
                    channel.sendMessage(member.getAsMention() + " был(а) выгнан(-а) с сервера!").queue();
                    member.kick().queue();
                }
            }
        }
    }
}
