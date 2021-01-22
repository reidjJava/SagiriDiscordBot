package ru.reidj.sagiridiscordbot.command.member;

import lombok.val;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import ru.reidj.sagiridiscordbot.Main;
import ru.reidj.sagiridiscordbot.command.ICommand;

import java.io.File;
import java.util.Objects;

public class TakeBalanceICommand extends ListenerAdapter implements ICommand {
    @Override
    public File getPath() {
        return null;
    }

    @Override
    public String getCommand() {
        return "!takebal";
    }

    @Override
    public String getHelp() {
        return null;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        val message = e.getMessage();
        val channel = e.getChannel();
        String[] messages = message.getContentRaw().split(" ");

        if (messages[0].equalsIgnoreCase(getCommand())) {
            channel.deleteMessageById(message.getId()).queue();
            if (Objects.requireNonNull(e.getMember()).hasPermission(Permission.ADMINISTRATOR)) {
                for (val member : message.getMentionedMembers()) {
                    val userStatistic = Main.getInstance().getUserStatistic().get(member.getId());
                    userStatistic.setMoney(userStatistic.getMoney() - Integer.parseInt(messages[2]));
                }
            }
        }
    }
}
