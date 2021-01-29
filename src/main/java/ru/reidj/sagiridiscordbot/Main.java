package ru.reidj.sagiridiscordbot;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import lombok.Getter;
import lombok.val;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bson.Document;
import ru.reidj.sagiridiscordbot.command.member.MemberCommand;
import ru.reidj.sagiridiscordbot.command.moderation.ModerationCommand;
import ru.reidj.sagiridiscordbot.event.GuildMemberJoin;
import ru.reidj.sagiridiscordbot.event.GuildMemberLeave;
import ru.reidj.sagiridiscordbot.event.MessageReaction;
import ru.reidj.sagiridiscordbot.event.SaveStats;
import ru.reidj.sagiridiscordbot.level.LvlSystem;
import ru.reidj.sagiridiscordbot.user.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Getter
public class Main {
    @Getter
    private final Map<String, User> userStatistic = new HashMap<>();
    @Getter
    private static final Main instance = new Main();
    @Getter
    private MongoCollection<Document> collection;
    // Строка подключения к бд
    private static final String MONGO_URI = "";

    public static void main(String[] args) throws Exception {
        // Здесь ваш токен
        val jda = JDABuilder.createDefault("")
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .setActivity(Activity.playing("IntelliJ IDEA"))
                .build();

        // Регистрация модерских команд
        jda.addEventListener(new ModerationCommand());

        // Инициализация команд
        jda.addEventListener(new MemberCommand());

        Arrays.asList(
                new GuildMemberJoin(),
                new GuildMemberLeave(),
                new LvlSystem(),
                new SaveStats(),
                new MessageReaction()
        ).forEach(jda::addEventListener);

        val client = new MongoClient(new MongoClientURI(MONGO_URI));
        Main.getInstance().collection = client.getDatabase("data").getCollection("users");

        // Загружаю стаистику из бд
        for (val document : Main.getInstance().getCollection().find()) {
            val id = document.getString("member");
            val money = document.getInteger("money");
            val level = document.getInteger("level");
            val messages = document.getInteger("messages");
            Main.getInstance().getUserStatistic().put(id, new User(level, money, messages));
        }
    }
}
