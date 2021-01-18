package ru.reidj.sagiridiscordbot.command.moderation;

import com.mongodb.client.model.Filters;
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
            Main.getInstance().getCollection().updateOne(Filters.eq("member", Objects.requireNonNull(member).getEffectiveName()), new Document(
                    "$set",
                    new Document("level", Main.getInstance().getUserStatistic().get(member).getLevel())
            ));
            if (Objects.requireNonNull(member).hasPermission(Permission.ADMINISTRATOR)) {
                channel.sendMessage("Ой, кажется я умираю :(").queue();
                channel.deleteMessageById(message.getId()).queue();
                System.exit(0);
            }
        }
    }
}
