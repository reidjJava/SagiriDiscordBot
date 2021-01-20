package ru.reidj.sagiridiscordbot.level;

import lombok.val;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import ru.reidj.sagiridiscordbot.Main;

import java.awt.*;
import java.util.Objects;

public class LvlSystem extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        val guild = e.getGuild();
        val member = e.getMember();
        val author = e.getAuthor();
        val userStatistic = Main.getInstance().getUserStatistic().get(Objects.requireNonNull(member).getId());

        if (!author.isBot()) {
            userStatistic.setNumberOfMessage(userStatistic.getNumberOfMessage() + 1);

            if (userStatistic.getNumberOfMessage() % 2 == 0 && !author.isBot()) {
                userStatistic.setLevel(userStatistic.getLevel() + 1);

                for (RoleLevels roleLevels : RoleLevels.values())
                    if (userStatistic.getLevel() == roleLevels.getLevel())
                        guild.addRoleToMember(Objects.requireNonNull(member), Objects.requireNonNull(guild.getRoleById(roleLevels.getRole()))).complete();

                val levelMessage = new EmbedBuilder();

                levelMessage.setColor(Color.PINK);
                levelMessage.setTitle("⸝⸝˚ Участник повысил уровень! ⸝");
                levelMessage.setDescription("・✦ Поздравляем " + Objects.requireNonNull(member).getAsMention() + " с " + userStatistic.getLevel() + " уровнем!" +
                        "\n・Зайка, продолжай в том же духе ♡");

                guild.getCategories()
                        .get(1)
                        .getTextChannels()
                        .get(5)
                        .sendMessage(levelMessage.build()).queue();
            }
        }
    }
}
