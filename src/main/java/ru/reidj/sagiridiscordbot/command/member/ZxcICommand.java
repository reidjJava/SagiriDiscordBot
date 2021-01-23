package ru.reidj.sagiridiscordbot.command.member;

import lombok.val;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import ru.reidj.sagiridiscordbot.Main;
import ru.reidj.sagiridiscordbot.command.ICommand;

import java.io.File;
import java.util.*;

public class ZxcICommand extends ListenerAdapter implements ICommand {
    private final Map<String, Boolean> isCountDown = new HashMap<>();

    @Override
    public File getPath() {
        return new File("src/main/resources/zxc.gif");
    }

    @Override
    public String getCommand() {
        return "!zxc";
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        val message = e.getMessage();
        val channel = e.getChannel();
        val member = e.getMember();
        val userList = Main.getInstance().getUserStatistic().get(Objects.requireNonNull(member).getId());
        val money = Math.random() * 1500;

        if (message.getContentRaw().startsWith(getCommand())) {
            if (isCountDown.get(member.getId()) == null)
                addCountDown(member.getId(), true);
            else if (isCountDown.get(member.getId()) == Boolean.TRUE) {
                channel.deleteMessageById(message.getId()).queue();
                addCountDown(member.getId(), false);
                userList.setMoney((int) (userList.getMoney() + money));
                channel.sendMessage(member.getAsMention() + ", вы сломали палец. \n+" + (int) money + " кристаллов").queue();
                channel.sendFile(getPath()).queue();
            } else {
                channel.sendMessage(member.getAsMention() + ", ещё рано для получения награды!").queue();
                return;
            }

            new Timer().schedule(
                    new TimerTask() {
                        @Override
                        public void run() {
                            channel.sendMessage(member.getAsMention() + ", снова может получить награду!").queue();
                            addCountDown(member.getId(), true);
                        }
                    }, 4 * 3600000
            );
        }
    }

    private void addCountDown(String member, boolean isCountDown) {
        this.isCountDown.put(member, isCountDown);
    }
}