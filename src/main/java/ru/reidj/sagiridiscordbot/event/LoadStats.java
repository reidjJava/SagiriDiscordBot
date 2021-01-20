package ru.reidj.sagiridiscordbot.event;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import ru.reidj.sagiridiscordbot.Main;
import ru.reidj.sagiridiscordbot.user.User;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class LoadStats extends ListenerAdapter {
    public void onMessageReceived(MessageReceivedEvent e) {
        // Загружаю статистику из файла в мапу
        try (Stream<String> lines = Files.lines(Paths.get("C:\\Users\\Рейдж\\Desktop\\UserStats.txt"))) {
            lines.filter(line -> line.contains(" ")).forEach(
                    line ->
                            Main.getInstance().getUserStatistic().putIfAbsent(line.split(" ")[0],
                                    new User(Integer.parseInt(line.split(" ")[1]),
                                            Integer.parseInt(line.split(" ")[2]),
                                            Integer.parseInt(line.split(" ")[3]))
                            ));
            System.out.println("Статистика успешно загружена в мапу");
        } catch (Exception exception) {
            System.out.println("Произошла ошибка при загрузке статистики  " + exception);
        }
    }
}
