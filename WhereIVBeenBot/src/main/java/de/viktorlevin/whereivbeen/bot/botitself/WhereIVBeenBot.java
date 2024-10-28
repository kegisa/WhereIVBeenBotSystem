package de.viktorlevin.whereivbeen.bot.botitself;


import de.viktorlevin.whereivbeen.bot.configuration.BotConfig;
import de.viktorlevin.whereivbeen.bot.service.MainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
public class WhereIVBeenBot extends TelegramLongPollingBot {
    private final BotConfig config;
    private final MainService mainService;

    public WhereIVBeenBot(BotConfig config, MainService mainService) {
        super(config.getToken());
        this.config = config;
        this.mainService = mainService;
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public void onUpdateReceived(Update update) {
        String message = mainService.process(update);
        sendToUser(message, update.getMessage().getChatId());
    }

    private void sendToUser(String message, Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);

        try {
            execute(sendMessage);
            log.info("Reply sent");
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }
}
