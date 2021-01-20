package ru.reidj.sagiridiscordbot;

import lombok.Getter;
import lombok.val;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import ru.reidj.sagiridiscordbot.command.CommandManager;
import ru.reidj.sagiridiscordbot.event.GuildMemberJoin;
import ru.reidj.sagiridiscordbot.event.GuildMemberLeave;
import ru.reidj.sagiridiscordbot.event.LoadStats;
import ru.reidj.sagiridiscordbot.level.LvlSystem;
import ru.reidj.sagiridiscordbot.user.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Getter
public class Main {
    @Getter
    private final Map<String, User> userStatistic = new HashMap<>();
    @Getter
    private static final Main instance = new Main();

    public static void main(String[] args) throws Exception {
        JDA jda = JDABuilder.createDefault("Nzk5NzI5MjE2NTMwNjc3ODEx.YAHz3w.ZaES44Oy064nNdCS_DwZZMT3rpg")
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .setActivity(Activity.watching("на тебя"))
                .build();

        jda.addEventListener(new CommandManager());

        Arrays.asList(
                new GuildMemberJoin(),
                new GuildMemberLeave(),
                new LvlSystem(),
                new LoadStats()
        ).forEach(jda::addEventListener);

        // Создаю файл и загружаю в него статистику
        while (true) {
            try {
                Main.getInstance().file();
                System.out.println("Статистика загружена в файл");
            } catch (Exception exception) {
                System.out.println("Произошла ошибка при выгрузки статистики " + exception);
            }
            Thread.sleep(60000);
        }
    }

    public void file() throws IOException {
        val file = new File("C:\\Users\\Рейдж\\Desktop\\UserStats.txt");
        val writer = new FileWriter(file, true);

        for (User value : Main.getInstance().getUserStatistic().values()) {
            for (String name : Main.getInstance().getUserStatistic().keySet())
                writer.write(name + " " + value.getLevel() + " " + value.getNumberOfMessage() + " " + value.getMoney() + "\n");
        }
        writer.flush();
        writer.close();
    }
}
