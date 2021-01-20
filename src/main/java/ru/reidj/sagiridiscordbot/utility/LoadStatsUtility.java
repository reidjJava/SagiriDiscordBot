package ru.reidj.sagiridiscordbot.utility;

import lombok.experimental.UtilityClass;
import lombok.val;
import net.dv8tion.jda.api.entities.Member;
import org.bson.Document;
import ru.reidj.sagiridiscordbot.Main;
import ru.reidj.sagiridiscordbot.user.User;

import java.util.Objects;

@UtilityClass
public class LoadStatsUtility {

    public void load(Member member) {
        val memberDoc = new Document("member", Objects.requireNonNull(member).getId());
        val founded = Main.getInstance().getCollection().find(memberDoc).first();

        if (founded == null) {
            memberDoc.put("level", Main.getInstance().getUserStatistic().get(member).getLevel());
            memberDoc.put("messages", Main.getInstance().getUserStatistic().get(member).getNumberOfMessage());
            memberDoc.put("money", Main.getInstance().getUserStatistic().get(member).getMoney());

            Main.getInstance().getCollection().insertOne(memberDoc);
        } else {
            int level = founded.getInteger("level");
            int money = founded.getInteger("money");
            int numberOfMessages = founded.getInteger("messages");

            Main.getInstance().getUserStatistic().put(member, new User(level, money, numberOfMessages));
        }
    }
}
