package ru.reidj.sagiridiscordbot.event;

import lombok.val;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class GuildMemberLeave extends ListenerAdapter {

    public void onGuildMemberRemove(GuildMemberRemoveEvent e) {
        val guild = e.getGuild();
        val user = e.getUser();

        val leaveMessage = new EmbedBuilder();

        leaveMessage.setColor(Color.PINK);
        leaveMessage.setTitle("Один из участников покинул сервер :(");
        leaveMessage.setDescription(user.getAsMention() + ", надеемся тебе у нас понравилось");
        leaveMessage.setImage("https://i.gifer.com/7bkF.gif");

        if (!user.isBot())
            guild.getCategories()
                    .get(0)
                    .getTextChannels()
                    .get(0)
                    .sendMessage(leaveMessage.build()).queue();

    }
}
