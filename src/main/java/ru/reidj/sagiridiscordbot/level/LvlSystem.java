package ru.reidj.sagiridiscordbot.level;

import lombok.val;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import ru.reidj.sagiridiscordbot.Main;

import java.awt.*;
import java.util.Objects;

import static com.sun.javafx.binding.Logging.getLogger;

public class LvlSystem extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        val guild = e.getGuild();
        val member = e.getMember();
        val author = e.getAuthor();
        val userStatistic = Main.getInstance().getUserStatistic().get(Objects.requireNonNull(member).getId());

        try {
            if (!author.isBot()) {
                userStatistic.setNumberOfMessage(userStatistic.getNumberOfMessage() + 1);

                if (userStatistic.getNumberOfMessage() % 60 == 0) {
                    userStatistic.setLevel(userStatistic.getLevel() + 1);

                    for (val roleLevels : RoleLevels.values())
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
        } catch (NullPointerException exception) {
            getLogger().info("Произошла ошибка" + exception);
        }
    }
}
