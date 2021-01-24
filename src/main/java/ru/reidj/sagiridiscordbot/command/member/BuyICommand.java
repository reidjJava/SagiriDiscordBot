package ru.reidj.sagiridiscordbot.command.member;

import lombok.val;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import ru.reidj.sagiridiscordbot.Main;
import ru.reidj.sagiridiscordbot.command.ICommand;
import ru.reidj.sagiridiscordbot.role.RoleShop;

import java.util.Objects;
import java.util.stream.Stream;

public class BuyICommand implements ICommand {

    public void onMessageReceived(MessageReceivedEvent e) {
        val message = e.getMessage();
        val member = e.getMember();
        val guild = e.getGuild();
        val channel = e.getChannel();
        val userStats = Main.getInstance().getUserStatistic().get(Objects.requireNonNull(member).getId());
        String[] messages = message.getContentRaw().split(" ");

        if (messages[0].equalsIgnoreCase("!buy")) {
            Stream.of(RoleShop.values()).forEach(roleShop -> {
                if (messages[1].equalsIgnoreCase(roleShop.getId()))
                    if (userStats.getMoney() >= roleShop.getMoney()) {
                        guild.addRoleToMember(member, Objects.requireNonNull(guild.getRoleById(roleShop.getRole()))).complete();
                        userStats.setMoney(userStats.getMoney() - roleShop.getMoney());
                        channel.sendMessage(member.getAsMention() + ", успешно приобрел роль").queue();
                    } else
                        channel.sendMessage("Ошибка! На вашем счёте недостаточно средств для совершения покупки").queue();
            });
        }
    }
}
