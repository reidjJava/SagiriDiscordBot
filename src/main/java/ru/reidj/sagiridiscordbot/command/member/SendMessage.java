package ru.reidj.sagiridiscordbot.command.member;

import lombok.val;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import ru.reidj.sagiridiscordbot.command.ICommand;

import java.io.File;

public class SendMessage extends ListenerAdapter implements ICommand {
    @Override
    public File getPath() {
        return null;
    }

    @Override
    public String getCommand() {
        return "!send";
    }

    @Override
    public String getHelp() {
        return null;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        val channel = e.getChannel();
        val message = e.getMessage();

        if (message.getContentRaw().startsWith(getCommand())) {
            channel.deleteMessageById(message.getId()).queue();

            val pollMessage = new EmbedBuilder();

            pollMessage.setColor(0x32353c);
            pollMessage.setTitle(":shingenstar: Добро пожаловать :shingenstar:");
            pollMessage.setDescription(":shingenstar:  Добро пожаловать. :shingenstar: \n" +
                    "Мы очень рады, что вы решили посетить наш уютный сервер, каждый участник для нас очень важен! \n" +
                    "Мы пытаемся создать уютный и милый сервер. Наши цели - что бы вы нашли себе подходящего собеседника и хорошо провели время!\n" +
                    "\n" +
                    "Информация о каналах:\n" +
                    "Каналы которые стоит посетить:\n" +
                    "\n" +
                    "#・╸✰⸝\uD83D\uDEA9информация - информация о сервере и ролях.\n" +
                    "#・╸✰⸝\uD83D\uDCD2правила - Правила сервера, обязательно к прочтению.\n" +
                    "#・╸✰⸝\uD83D\uDCE2новости - Новости сервера.\n" +
                    "#・╸✰⸝\uD83D\uDC8Eроли - Роли сервера. Тут ты сможешь приобрести роли, так же, некоторые можно получить бесплатно.\n" +
                    "#・╸✰⸝\uD83D\uDCD1команды - Команды ботов, поможет вам если вы не знаете команд или забыли их.\n" +
                    "\n" +
                    "Каналы для общения:\n" +
                    "\n" +
                    "#╭★˚chat◞ - Главный канал для общения.\n" +
                    "#・✦⸝⸝cmds - Канал для использования команд.\n" +
                    "#・✦⸝⸝flood - Канал для особо общительных, неадекватных участников.\n" +
                    "#╰★˚selfies◞ - Канал где ты сможешь показать своё личико.\n" +
                    "\n" +
                    "Интересные каналы:\n" +
                    "\n" +
                    "#╭★˚\uD83C\uDF80эстетика - Канал с эстетикой.\n" +
                    "#・✦⸝⸝\uD83D\uDD1Ehentai - Здесь ты сможешь ... А кто знает :>.\n" +
                    "#╰★˚\uD83D\uDD25аватарки - Здесь ты сможешь найти себе аватарку.\n" +
                    "឵\n" +
                    "\n" +
                    " Информация о ролях.\n" +
                    " Administration:\n" +
                    "\n" +
                    "@ㅤㅤㅤ・ shingen developer ・ㅤㅤㅤ   ⸝⸝ ∿ - Разработчик сервера.\n" +
                    "@ㅤㅤㅤㅤㅤ・ founder ・ㅤㅤㅤㅤㅤ  ⸝⸝ ∿ - Роль владельца сервера.\n" +
                    "@ㅤㅤㅤㅤㅤ・ sendo ・ㅤㅤㅤㅤㅤ  ⸝⸝ ∿ - Администрация сервера, решают вопросы насчёт повышений, и понижений модерации.\n" +
                    "@ㅤㅤㅤㅤㅤ・ shino ・ㅤㅤㅤㅤㅤ  ⸝⸝ ∿ - Куратор, ответственный за набор персонала.\n" +
                    "@ㅤㅤㅤㅤㅤ・ control ・ㅤㅤㅤㅤㅤ  ឵឵឵⸝⸝ ∿ - Контролируют чаты,войс и модерацию.\n" +
                    "@ㅤㅤㅤㅤㅤ・ voice control ・ㅤㅤㅤㅤㅤ  ⸝⸝ ∿ - Следит за порядком в голосовом чате.\n" +
                    "@ㅤㅤㅤㅤㅤ・ chat control ・ㅤㅤㅤㅤㅤ  ⸝⸝ ∿ - Следит за порядком в чате.\n" +
                    "@ㅤㅤㅤㅤ・ event manager ・ㅤㅤㅤ  ឵឵឵ ឵឵឵⸝⸝ ∿ - Проводит ивенты.\n" +
                    "@ㅤㅤㅤㅤㅤ・ promotion ・ㅤㅤㅤㅤ ⸝⸝ ∿ - Пиар менеджер, ответственный за раскрутку сервера.\n" +
                    "\n" +
                    "Если ты это прочитал, то ты зайка :3");

            e.getGuild().getCategories()
                    .get(1)
                    .getTextChannels()
                    .get(0)
                    .sendMessage(pollMessage.build()).queue();
        }
    }
}
