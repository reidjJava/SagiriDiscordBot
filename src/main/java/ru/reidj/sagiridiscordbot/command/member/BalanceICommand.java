package ru.reidj.sagiridiscordbot.command.member;

import lombok.val;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import ru.reidj.sagiridiscordbot.Main;
import ru.reidj.sagiridiscordbot.command.ICommand;

import java.io.File;
import java.util.Objects;

public class BalanceICommand extends ListenerAdapter implements ICommand {
    @Override
    public File getPath() {
        return null;
    }

    @Override
    public String getCommand() {
        return "!bal";
    }

    @Override
    public String getHelp() {
        return null;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        val message = e.getMessage();
        val channel = e.getChannel();
        val member = e.getMember();

        if (message.getContentRaw().startsWith(getCommand())) {
            channel.deleteMessageById(message.getId()).queue();
            channel.sendMessage(Objects.requireNonNull(member).getAsMention() +
                    ", ваш баланс " + Main.getInstance().getUserStatistic().get(member.getId()).getMoney() + " кристаллов").queue();
        }
    }
}
