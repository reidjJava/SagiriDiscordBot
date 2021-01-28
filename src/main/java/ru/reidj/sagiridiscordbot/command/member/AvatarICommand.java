package ru.reidj.sagiridiscordbot.command.member;

import lombok.val;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import ru.reidj.sagiridiscordbot.command.ICommand;

public class AvatarICommand implements ICommand {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        val message = e.getMessage();
        val channel = e.getChannel();

        if (message.getContentRaw().startsWith("!avatar")) {
            channel.deleteMessageById(message.getId()).queue();
            message.getMentionedMembers()
                    .forEach(member -> channel.sendMessage("Аватарка " + member.getEffectiveName() + "\n" + member.getUser().getAvatarUrl()).queue());
        }
    }
}
