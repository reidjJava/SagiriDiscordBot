package ru.reidj.sagiridiscordbot.event;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import lombok.val;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bson.Document;
import ru.reidj.sagiridiscordbot.Main;

import java.util.Objects;

public class SaveStats extends ListenerAdapter {
    public void onMessageReceived(MessageReceivedEvent e) {
        val member = e.getMember();
        val userStatistic = Main.getInstance().getUserStatistic().get(Objects.requireNonNull(member).getId());

        if (e.getAuthor().isBot())
            return;

        // Сохраняю статистику в бд
        val document = new Document()
                .append("member", Objects.requireNonNull(member).getId())
                .append("level", userStatistic.getLevel())
                .append("messages", userStatistic.getNumberOfMessage())
                .append("money", userStatistic.getMoney());

        Main.getInstance().getCollection()
                .updateOne(Filters.eq("member", Objects.requireNonNull(member).getId()),
                        new Document("$set", document), new UpdateOptions().upsert(true));
    }
}
