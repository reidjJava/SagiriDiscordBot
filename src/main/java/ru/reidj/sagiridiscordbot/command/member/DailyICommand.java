package ru.reidj.sagiridiscordbot.command.member;

import lombok.val;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import ru.reidj.sagiridiscordbot.Main;
import ru.reidj.sagiridiscordbot.command.ICommand;

import java.util.*;

public class DailyICommand implements ICommand {
    private final Map<String, Boolean> isCountDown = new HashMap<>();

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        val message = e.getMessage();
        val channel = e.getChannel();
        val member = e.getMember();
        val userList = Main.getInstance().getUserStatistic().get(Objects.requireNonNull(member).getId());

        if (isCountDown.get(member.getId()) == null)
            addCountDown(member.getId(), true);

        if (message.getContentRaw().startsWith("!daily")) {
            if (isCountDown.get(member.getId()) == Boolean.TRUE) {
                channel.deleteMessageById(message.getId()).queue();
                addCountDown(member.getId(), false);
                userList.setMoney(userList.getMoney() + 500);
                channel.sendMessage(member.getAsMention() + ", вы получили 500 кристаллов!").queue();
            } else {
                channel.sendMessage(member.getAsMention() + ", ещё рано для получения ежедневной награды!").queue();
                return;
            }

            new Timer().schedule(
                    new TimerTask() {
                        @Override
                        public void run() {
                            channel.sendMessage(member.getAsMention() + ", снова может получить ежедневную награду!").queue();
                            addCountDown(member.getId(), true);
                        }
                    }, 24 * 3600000
            );
        }
    }

    private void addCountDown(String member, boolean isCountDown) {
        this.isCountDown.put(member, isCountDown);
    }
}
