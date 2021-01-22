package ru.reidj.sagiridiscordbot.command.member;

import lombok.val;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import ru.reidj.sagiridiscordbot.command.ICommand;

import java.io.File;
import java.util.Objects;

public class LaughICommand extends ListenerAdapter implements ICommand {

    @Override
    public File getPath() {
        return new File("src/main/resources/laugh.gif");
    }

    @Override
    public String getCommand() {
        return "!laugh";
    }

    @Override
    public String getHelp() {
        return null;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        val message = e.getMessage();
        val channel = e.getChannel();

        if (message.getContentRaw().startsWith(getCommand())) {
            channel.deleteMessageById(message.getId()).queue();
            channel.sendMessage(Objects.requireNonNull(e.getMember()).getAsMention() + " посмеялся(-ась)").queue();
            channel.sendFile(getPath()).queue();
        }
    }
}
