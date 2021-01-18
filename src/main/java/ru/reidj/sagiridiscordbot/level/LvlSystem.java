package ru.reidj.sagiridiscordbot.level;

import lombok.val;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LvlSystem extends ListenerAdapter {
    private final Map<Member, Integer> levels = new HashMap<>();
    private final Map<Member, Integer> messageAmount = new HashMap<>();

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        val guild = e.getGuild();
        val member = e.getMember();
        val author = e.getAuthor();

        if (!author.isBot()) {
            messageAmount.putIfAbsent(member, 0);
            messageAmount.put(member, messageAmount.get(member) + 1);

            if (messageAmount.get(member) % 60 == 0 && !author.isBot()) {
                levels.putIfAbsent(member, 1);
                levels.put(member, levels.get(member) + 1);

                for (RoleLevels roleLevels : RoleLevels.values())
                    if (levels.get(member).equals(roleLevels.getLevel()))
                        guild.addRoleToMember(Objects.requireNonNull(member), Objects.requireNonNull(guild.getRoleById(roleLevels.getRole()))).complete();

                val levelMessage = new EmbedBuilder();

                levelMessage.setColor(Color.PINK);
                levelMessage.setTitle("⸝⸝˚ Участник повысил уровень! ⸝");
                assert member != null;
                levelMessage.setDescription("・✦ Поздравляем " + member.getAsMention() + " с " + levels.get(member) + " уровнем!" +
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
