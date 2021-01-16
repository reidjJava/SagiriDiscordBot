package ru.reidj.sagiridiscordbot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import ru.reidj.sagiridiscordbot.command.moderation.BanCommand;
import ru.reidj.sagiridiscordbot.command.moderation.KickCommand;
import ru.reidj.sagiridiscordbot.command.moderation.MuteCommand;
import ru.reidj.sagiridiscordbot.event.GuildMemberJoin;
import ru.reidj.sagiridiscordbot.event.GuildMemberLeave;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        JDA jda = JDABuilder.createDefault("Nzk5NzI5MjE2NTMwNjc3ODEx.YAHz3w.ZaES44Oy064nNdCS_DwZZMT3rpg")
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .build();

        Arrays.asList(
                new GuildMemberJoin(),
                new GuildMemberLeave(),
                new BanCommand(),
                new KickCommand(),
                new MuteCommand()
        ).forEach(jda::addEventListener);
    }
}
