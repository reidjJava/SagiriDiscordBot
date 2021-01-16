package ru.reidj.sagiridiscordbot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;

public class Command extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");

        if (args[0].equalsIgnoreCase("+info")) {
            EmbedBuilder info = new EmbedBuilder();
            info.setTitle("Я шлюха");
            info.setDescription("Ох ебать раскачегарьте мне очко");
            info.setColor(0xf45642);
            info.setFooter("Не пиши сюда от тебя говной воняет", Objects.requireNonNull(event.getMember()).getUser().getAvatarUrl());
            event.getChannel().sendMessage(info.build()).queue();
        }
    }
}
