package ru.reidj.sagiridiscordbot.event;

import lombok.val;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import ru.reidj.sagiridiscordbot.Main;
import ru.reidj.sagiridiscordbot.user.User;
import ru.reidj.sagiridiscordbot.utility.LoadStatsUtility;

import java.awt.*;
import java.util.Objects;

public class GuildMemberJoin extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent e) {
        val guild = e.getGuild();
        val user = e.getUser();
        val member = e.getMember();

        Main.getInstance().getUserStatistic().put(member, new User(1, 0, 0));

        LoadStatsUtility.load(member);

        val joinMessage = new EmbedBuilder();

        joinMessage.setColor(Color.PINK);
        joinMessage.setTitle("♡˚ Встречаем нового участника! ♡˚");
        joinMessage.setDescription(user.getAsMention() + ", добро пожаловать на наш уютный сервер!឵឵឵ " +
                "\nСолнышко, чувствуй себя как дома :3");
        joinMessage.setImage("https://i.gifer.com/7bkF.gif");

        if (!user.isBot())
            guild.getCategories()
                    .get(0)
                    .getTextChannels()
                    .get(0)
                    .sendMessage(joinMessage.build()).queue();

        guild.addRoleToMember(member, Objects.requireNonNull(guild.getRoleById("799936896389742592"))).complete();
    }
}
