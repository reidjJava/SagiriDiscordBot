package ru.reidj.sagiridiscordbot.command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface ICommand {
    void onMessageReceived(MessageReceivedEvent e);
}
