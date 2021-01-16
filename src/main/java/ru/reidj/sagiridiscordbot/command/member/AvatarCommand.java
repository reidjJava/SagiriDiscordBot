package ru.reidj.sagiridiscordbot.command.member;

import lombok.val;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class AvatarCommand extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        val message = e.getMessage();
        val channel = e.getChannel();

        if (message.getContentRaw().startsWith("!avatar")) {
            channel.deleteMessageById(message.getId()).queue();
            for (Member member : e.getMessage().getMentionedMembers()) {
                channel.sendMessage("Аватарка " + member.getUser().getName() + "\n"  + member.getUser().getAvatarUrl()).queue();
            }
        }
    }
}
