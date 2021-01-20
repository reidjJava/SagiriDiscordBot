package ru.reidj.sagiridiscordbot.command.member;

import lombok.val;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import ru.reidj.sagiridiscordbot.Main;
import ru.reidj.sagiridiscordbot.command.ICommand;

import java.io.File;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class DailyCommand extends ListenerAdapter implements ICommand {
    private boolean isCountDown = true;

    @Override
    public File getPath() {
        return null;
    }

    @Override
    public String getCommand() {
        return "!daily";
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
        val userList = Main.getInstance().getUserStatistic().get(Objects.requireNonNull(member).getId());


        if (isCountDown) {
            if (message.getContentRaw().startsWith(getCommand())) {
                channel.deleteMessageById(message.getId()).queue();
                userList.setMoney(userList.getMoney() + 500);
                channel.sendMessage(member.getAsMention() + ", вы получили 500 кристаллов").queue();
                isCountDown = false;
            }
        } else {
            channel.sendMessage(member.getAsMention() + ", ещё рано для получения ежедневной награды!").queue();
            return;
        }

        new Timer().schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        channel.sendMessage(member.getAsMention() + ", снова может получить ежедневную награду!").queue();
                        isCountDown = true;
                    }
                }, 24 * 3600000
        );
    }
}
