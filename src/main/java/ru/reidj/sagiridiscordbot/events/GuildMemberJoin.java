package ru.reidj.sagiridiscordbot.events;

import lombok.val;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.List;

public class GuildMemberJoin extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        val guild = event.getGuild();
        val user = event.getUser();

        List<TextChannel> channels = guild.getTextChannelsByName("прибывшие", true);

        val joinMessage = new EmbedBuilder();

        joinMessage.setColor(Color.PINK);
        joinMessage.setTitle("♡˚ Встречаем нового участника! ♡˚");
        joinMessage.setDescription(user.getAsMention() + ", добро пожаловать на наш уютный сервер!឵឵឵ " +
                "\nСолнышко, чувствуй себя как дома :3");
        joinMessage.setImage("https://i.gifer.com/7bkF.gif");

        if (!user.isBot())
            channels.get(0).sendMessage(joinMessage.build()).queue();
    }
}
