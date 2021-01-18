package ru.reidj.sagiridiscordbot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import ru.reidj.sagiridiscordbot.command.game.RockPaperScissors;
import ru.reidj.sagiridiscordbot.command.member.*;
import ru.reidj.sagiridiscordbot.command.moderation.BanICommand;
import ru.reidj.sagiridiscordbot.command.moderation.KickICommand;
import ru.reidj.sagiridiscordbot.command.moderation.MuteICommand;
import ru.reidj.sagiridiscordbot.command.moderation.WarnICommand;
import ru.reidj.sagiridiscordbot.event.GuildMemberJoin;
import ru.reidj.sagiridiscordbot.event.GuildMemberLeave;
import ru.reidj.sagiridiscordbot.level.LvlSystem;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        JDA jda = JDABuilder.createDefault("Nzk5NzI5MjE2NTMwNjc3ODEx.YAHz3w.ZaES44Oy064nNdCS_DwZZMT3rpg")
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .build();

        Arrays.asList(
                new GuildMemberJoin(),
                new GuildMemberLeave(),
                new BanICommand(),
                new KickICommand(),
                new MuteICommand(),
                new WarnICommand(),
                new ThreatenICommand(),
                new HighFiveICommand(),
                new AngryICommand(),
                new CryICommand(),
                new DidderICommand(),
                new FacePawnICommand(),
                new GoodbyeICommand(),
                new HelloICommand(),
                new HugICommand(),
                new KissICommand(),
                new LaughICommand(),
                new PatICommand(),
                new SleepICommand(),
                new SorryICommand(),
                new WowICommand(),
                new HitICommand(),
                new AvatarICommand(),
                new PollICommand(),
                new LvlSystem(),
                new RockPaperScissors()
        ).forEach(jda::addEventListener);
    }
}
