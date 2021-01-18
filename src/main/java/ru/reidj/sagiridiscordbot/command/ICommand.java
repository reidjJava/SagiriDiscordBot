package ru.reidj.sagiridiscordbot.command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.File;

public interface ICommand {

    File getPath();

    String getCommand();

    void onMessageReceived(MessageReceivedEvent e);
}
