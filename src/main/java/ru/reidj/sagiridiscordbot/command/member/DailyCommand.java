package ru.reidj.sagiridiscordbot.command.member;

import lombok.val;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import ru.reidj.sagiridiscordbot.Main;
import ru.reidj.sagiridiscordbot.command.ICommand;

import java.io.File;

public class DailyCommand extends ListenerAdapter implements ICommand {

    @Override
    public File getPath() {
        return null;
    }

    @Override
    public String getCommand() {
        return "!daily";
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        val message = e.getMessage();
        val channel = e.getChannel();
        val member = e.getMember();
        val userList = Main.getInstance().getUserStatistic().get(member);

        if (message.getContentRaw().startsWith(getCommand())) {
            channel.deleteMessageById(message.getId()).queue();
            userList.setMoney(userList.getMoney() + 500);
            channel.sendMessage("Вы получили 500 монет").queue();
        }
    }
}
