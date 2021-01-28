package ru.reidj.sagiridiscordbot.command.moderation;

import lombok.val;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import ru.reidj.sagiridiscordbot.command.ICommand;

import java.util.Objects;

public class BanICommand implements ICommand {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        val message = e.getMessage();
        val channel = e.getChannel();
        String[] messages = e.getMessage().getContentRaw().split(" ");

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
}
