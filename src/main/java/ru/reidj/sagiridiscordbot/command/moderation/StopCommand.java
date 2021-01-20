package ru.reidj.sagiridiscordbot.command.moderation;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import lombok.val;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bson.Document;
import ru.reidj.sagiridiscordbot.Main;

import java.util.Objects;

public class StopCommand extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        val message = e.getMessage();
        val channel = e.getChannel();
        val member = e.getMember();

        if (message.getContentRaw().startsWith("!stop")) {
            val document = new Document()
                    .append("member", Objects.requireNonNull(member).getId())
                    .append("level", Main.getInstance().getUserStatistic().get(member).getLevel())
                    .append("messages", Main.getInstance().getUserStatistic().get(member).getNumberOfMessage())
                    .append("money", Main.getInstance().getUserStatistic().get(member).getMoney());

            Main.getInstance().getCollection()
                    .updateOne(Filters.eq("member", Objects.requireNonNull(member).getId()),
                            new Document("$set", document), new UpdateOptions().upsert(true));
            if (Objects.requireNonNull(member).hasPermission(Permission.ADMINISTRATOR)) {
                channel.sendMessage("Ой, кажется я умираю :(").queue();
                channel.deleteMessageById(message.getId()).queue();
                System.exit(0);
            }
        }
    }
}
