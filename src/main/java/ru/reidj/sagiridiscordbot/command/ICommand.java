package ru.reidj.sagiridiscordbot.command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.File;

public interface ICommand {

    File getPath();

    String getCommand();

    String getHelp();

    void onMessageReceived(MessageReceivedEvent e);
}
