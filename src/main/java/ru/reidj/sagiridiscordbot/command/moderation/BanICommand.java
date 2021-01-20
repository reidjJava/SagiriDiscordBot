package ru.reidj.sagiridiscordbot.command.moderation;

import lombok.val;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import ru.reidj.sagiridiscordbot.command.ICommand;

import java.io.File;
import java.util.Objects;

public class BanICommand extends ListenerAdapter implements ICommand {

    @Override
    public File getPath() {
        return null;
    }

    @Override
    public String getCommand() {
        return "!ban";
    }

    @Override
    public String getHelp() {
        return null;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        val message = e.getMessage();
        val channel = e.getChannel();
        String[] messages = e.getMessage().getContentRaw().split(" ");

        try {
            if (messages[0].equalsIgnoreCase(getCommand())) {
                if (Objects.requireNonNull(e.getMember()).hasPermission(Permission.BAN_MEMBERS)) {
                    channel.deleteMessageById(message.getId()).queue();
                    for (Member member : e.getMessage().getMentionedMembers()) {
                        channel.sendMessage(member.getAsMention() + " был(-а) заблокирован(-а) на " + messages[2] + " день(-ней)!").queue();
                        member.ban(Integer.parseInt(messages[2])).complete();
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            channel.sendMessage("Синтаксическая ошибка! Используйте !mute @имя время(в секундах)").queue();
        }
    }
}