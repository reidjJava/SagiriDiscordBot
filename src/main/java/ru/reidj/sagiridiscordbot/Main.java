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
import ru.reidj.sagiridiscordbot.command.CommandManager;
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
    private static final String MONGO_URI = "mongodb://root:azxaewef345t@cluster0-shard-00-00.0h5nu.mongodb.net:27017,cluster0-shard-00-01.0h5nu.mongodb.net:27017,cluster0-shard-00-02.0h5nu.mongodb.net:27017/test?replicaSet=atlas-me5rlq-shard-0&ssl=true&authSource=admin";

    public static void main(String[] args) throws Exception {
        val jda = JDABuilder.createDefault("Nzk5NzI5MjE2NTMwNjc3ODEx.YAHz3w.ZaES44Oy064nNdCS_DwZZMT3rpg")
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .setActivity(Activity.playing("IntelliJ IDEA"))
                .build();

        Arrays.asList(
                new GuildMemberJoin(),
                new GuildMemberLeave(),
                new LvlSystem(),
                new SaveStats(),
                new MessageReaction()
        ).forEach(jda::addEventListener);

        jda.addEventListener(new CommandManager());

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
