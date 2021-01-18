package ru.reidj.sagiridiscordbot.command.game;

import lombok.val;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.Random;

public class RockPaperScissors extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        val message = e.getMessage();
        val channel = e.getChannel();
        val random = new Random();
        String[] answer = {"А у меня камень", "А у меня ножницы", "А у меня бумага"};
        int pos = random.nextInt(answer.length);


        if (message.getContentRaw().startsWith("!камень") ||
                message.getContentRaw().startsWith("!бумага") ||
                message.getContentRaw().startsWith("!ножницы")) {
            channel.deleteMessageById(message.getId()).queue();

            val answerMessage = new EmbedBuilder();

            answerMessage.setColor(Color.PINK);
            answerMessage.setTitle("⸝⸝⋅✿ Камень, ножницы, бумага ・");
            answerMessage.setDescription(answer[pos]);

            channel.sendMessage(answerMessage.build()).queue();
        }
    }
}
