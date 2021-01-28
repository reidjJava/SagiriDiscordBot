package ru.reidj.sagiridiscordbot.command.member;

import lombok.val;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import ru.reidj.sagiridiscordbot.Main;
import ru.reidj.sagiridiscordbot.command.ICommand;

import java.util.Objects;

public class BalanceICommand implements ICommand {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        val message = e.getMessage();
        val channel = e.getChannel();
        val member = e.getMember();
        val userStatistic = Main.getInstance().getUserStatistic().get(Objects.requireNonNull(member).getId());
        String[] messages = message.getContentRaw().split(" ");

        if (messages[0].equalsIgnoreCase("!bal")) {
            channel.deleteMessageById(message.getId()).queue();
            channel.sendMessage(Objects.requireNonNull(member).getAsMention() +
                    ", ваш баланс " + userStatistic.getMoney() + " кристаллов").queue();
        }
    }
}
