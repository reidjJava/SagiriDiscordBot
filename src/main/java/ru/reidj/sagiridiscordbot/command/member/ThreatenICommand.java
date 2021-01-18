package ru.reidj.sagiridiscordbot.command.member;

import lombok.val;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import ru.reidj.sagiridiscordbot.command.ICommand;

import java.io.File;

public class ThreatenICommand extends ListenerAdapter implements ICommand {

    @Override
    public File getPath() {
        return new File("");
    }

    @Override
    public String getCommand() {
        return "!threaten";
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        val message = e.getMessage();
        val channel = e.getChannel();

        if (message.getContentRaw().startsWith(getCommand())) {
            channel.deleteMessageById(message.getId()).queue();
            for (Member member : e.getMessage().getMentionedMembers()) {
                channel.sendMessage(e.getAuthor().getAsMention() + " пригрозил(а) " + member.getAsMention()).queue();
                channel.sendFile(getPath()).queue();
            }
        }
    }
}
