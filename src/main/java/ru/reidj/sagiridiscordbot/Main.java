package ru.reidj.sagiridiscordbot;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bson.Document;
import ru.reidj.sagiridiscordbot.command.member.*;
import ru.reidj.sagiridiscordbot.command.moderation.*;
import ru.reidj.sagiridiscordbot.event.GuildMemberJoin;
import ru.reidj.sagiridiscordbot.event.GuildMemberLeave;
import ru.reidj.sagiridiscordbot.level.LvlSystem;
import ru.reidj.sagiridiscordbot.user.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.sun.javafx.binding.Logging.getLogger;

@Getter
public class Main {
    @Getter
    private final Map<Member, User> userStatistic = new HashMap<>();
    @Getter
    private MongoCollection<Document> collection;
    @Getter
    private static final Main instance = new Main();
    private static final String MONGO_URI = "mongodb://root:azxaewef345t@cluster0-shard-00-00.0h5nu.mongodb.net:27017,cluster0-shard-00-01.0h5nu.mongodb.net:27017,cluster0-shard-00-02.0h5nu.mongodb.net:27017/test?replicaSet=atlas-me5rlq-shard-0&ssl=true&authSource=admin";

    public static void main(String[] args) throws Exception {
        JDA jda = JDABuilder.createDefault("Nzk5NzI5MjE2NTMwNjc3ODEx.YAHz3w.ZaES44Oy064nNdCS_DwZZMT3rpg")
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .build();

        MongoClient client = new MongoClient(new MongoClientURI(MONGO_URI));
        Main.getInstance().collection =  client.getDatabase("data").getCollection("users");

        getLogger().info("Connecting to database");


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
                new DailyCommand(),
                new StopCommand()
        ).forEach(jda::addEventListener);
    }
}
