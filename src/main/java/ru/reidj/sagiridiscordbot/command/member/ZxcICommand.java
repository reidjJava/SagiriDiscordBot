package ru.reidj.sagiridiscordbot.command.member;

import lombok.val;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import ru.reidj.sagiridiscordbot.Main;
import ru.reidj.sagiridiscordbot.command.ICommand;

import java.io.File;
import java.util.*;

public class ZxcICommand implements ICommand {
    private final Map<String, Boolean> isCountDown = new HashMap<>();

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        val message = e.getMessage();
        val channel = e.getChannel();
        val member = e.getMember();
        val userList = Main.getInstance().getUserStatistic().get(Objects.requireNonNull(member).getId());
        val money = Math.random() * 1500;

        isCountDown.putIfAbsent(member.getId(), true);

        if (message.getContentRaw().startsWith("!zxc")) {
            if (isCountDown.get(member.getId()) == Boolean.TRUE) {
                channel.deleteMessageById(message.getId()).queue();
                addCountDown(member.getId(), false);
                userList.setMoney((int) (userList.getMoney() + money));
                channel.sendMessage(member.getAsMention() + ", вы сломали палец. \n+" + (int) money + " кристаллов").queue();
                channel.sendFile(new File("src/main/resources/zxc.gif")).queue();
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
