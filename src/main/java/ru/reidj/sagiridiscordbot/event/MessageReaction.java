package ru.reidj.sagiridiscordbot.event;

import lombok.val;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;

public class MessageReaction extends ListenerAdapter {

    public void onMessageReactionAdd(MessageReactionAddEvent e) {
        val guild = e.getGuild();
        val member = e.getMember();
        val emoji = e.getReactionEmote().getEmoji();

        if (emoji.equals("\uD83D\uDC9B"))
            guild.addRoleToMember(Objects.requireNonNull(member), Objects.requireNonNull(guild.getRoleById("799722146493759540"))).queue();
        else if (emoji.equals("\uD83D\uDC9C"))
            guild.addRoleToMember(Objects.requireNonNull(member), Objects.requireNonNull(guild.getRoleById("799720950416539658"))).queue();
    }
}
