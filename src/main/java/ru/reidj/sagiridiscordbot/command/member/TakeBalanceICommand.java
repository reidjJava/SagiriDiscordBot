package ru.reidj.sagiridiscordbot.command.member;

import lombok.val;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import ru.reidj.sagiridiscordbot.Main;
import ru.reidj.sagiridiscordbot.command.ICommand;

import java.util.Objects;

public class TakeBalanceICommand implements ICommand {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        val message = e.getMessage();
        val channel = e.getChannel();
        String[] messages = message.getContentRaw().split(" ");

        if (messages[0].equalsIgnoreCase("!takebal")) {
            channel.deleteMessageById(message.getId()).queue();
            if (Objects.requireNonNull(e.getMember()).hasPermission(Permission.ADMINISTRATOR)) {
                message.getMentionedMembers().stream()
                        .map(userStatistic -> Main.getInstance().getUserStatistic().get(userStatistic.getId()))
                        .forEach(member -> member.setMoney(member.getMoney() - Integer.parseInt(messages[2])));
            }
        }
    }
}
