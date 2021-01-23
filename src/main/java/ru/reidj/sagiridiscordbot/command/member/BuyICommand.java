package ru.reidj.sagiridiscordbot.command.member;

import lombok.val;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import ru.reidj.sagiridiscordbot.Main;
import ru.reidj.sagiridiscordbot.command.ICommand;
import ru.reidj.sagiridiscordbot.role.RoleShop;

import java.io.File;
import java.util.Objects;

public class BuyICommand extends ListenerAdapter implements ICommand {
    @Override
    public File getPath() {
        return null;
    }

    @Override
    public String getCommand() {
        return "!buy";
    }

    @Override
    public String getHelp() {
        return null;
    }

    public void onMessageReceived(MessageReceivedEvent e) {
        val message = e.getMessage();
        val member = e.getMember();
        val guild = e.getGuild();
        val channel = e.getChannel();
        val userStats = Main.getInstance().getUserStatistic().get(Objects.requireNonNull(member).getId());
        String[] messages = message.getContentRaw().split(" ");

        if (messages[0].equalsIgnoreCase(getCommand())) {
            for (val roles : RoleShop.values()) {
                if (messages[1].equalsIgnoreCase(roles.getId()))
                    if (userStats.getMoney() >= roles.getMoney()) {
                        guild.addRoleToMember(member, Objects.requireNonNull(guild.getRoleById(roles.getRole()))).complete();
                        userStats.setMoney(userStats.getMoney() - roles.getMoney());
                        channel.sendMessage(member.getAsMention() + ", успешно приобрел роль").queue();
                    } else
                        channel.sendMessage("Ошибка! На вашем счёте недостаточно средств для совершения покупки").queue();
            }
        }
    }
}
