package ru.reidj.sagiridiscordbot.event;

import lombok.val;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;

public class GuildMemberLeave extends ListenerAdapter {

    public void onGuildMemberLeave(GuildMemberLeaveEvent e) {
        User user = e.getUser();
        Guild guild = e.getGuild();

        List<TextChannel> channels = guild.getTextChannelsByName("основной", true);

        val leaveMessage = new EmbedBuilder();

        leaveMessage.setColor(0xf48342);
        leaveMessage.setDescription(user.getName() + ", трахнул суку и бросил");

        channels.get(0).sendMessage(leaveMessage.build()).queue();
    }
}
