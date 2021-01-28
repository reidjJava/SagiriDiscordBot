package ru.reidj.sagiridiscordbot.event;

import lombok.val;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bson.Document;
import ru.reidj.sagiridiscordbot.Main;
import ru.reidj.sagiridiscordbot.user.User;

import java.awt.*;
import java.util.Objects;

public class GuildMemberJoin extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent e) {
        val guild = e.getGuild();
        val member = e.getMember();

        Main.getInstance().getUserStatistic().put(member.getId(), new User(1, 0, 0));

        val userDoc = new Document("member", Objects.requireNonNull(member).getId());
        val founded = Main.getInstance().getCollection().find(userDoc).first();

        if (founded == null) {
            userDoc.append("level", 1);
            userDoc.append("money", 0);
            userDoc.append("messages", 0);
            Main.getInstance().getCollection().insertOne(userDoc);
        }

        val joinMessage = new EmbedBuilder();

        joinMessage.setColor(Color.PINK);
        joinMessage.setTitle("♡˚ Встречаем нового участника! ♡˚");
        joinMessage.setDescription(member.getAsMention() + ", добро пожаловать на наш уютный сервер!឵឵឵ " +
                "\nСолнышко, чувствуй себя как дома :3");
        joinMessage.setImage("https://i.gifer.com/7bkF.gif");

        guild.getCategories()
                .get(0)
                .getTextChannels()
                .get(0)
                .sendMessage(joinMessage.build()).queue();

        guild.addRoleToMember(member, Objects.requireNonNull(guild.getRoleById("799936896389742592"))).complete();
    }
}
