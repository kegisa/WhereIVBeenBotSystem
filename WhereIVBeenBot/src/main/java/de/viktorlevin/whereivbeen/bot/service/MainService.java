package de.viktorlevin.whereivbeen.bot.service;

import de.viktorlevin.whereivbeen.bot.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class MainService {

    private final ProcessService processService;
    private final TextService textService;

    public static final String UNEXPECTED_MESSAGE = "Unexpected message";

    public String process(Update update) {
        try {
            Objects.requireNonNull(update.getMessage());
            String member = update.getMessage().getChat().getUserName();
            if (isTextMessage(update)) {
                log.info("Got text message from {}", member);
                return processTextMessage(update);
            } else if (isGeoMessage(update)) {
                log.info("Got geo message from {}", member);
                processService.processGeoMessage(update);
                return textService.savePoint();
            }
            return UNEXPECTED_MESSAGE;
        } catch (NotFoundException exception) {
            return exception.getMessage();
        }

    }

    private boolean isTextMessage(Update update) {
        return update.getMessage().hasText();
    }

    private boolean isGeoMessage(Update update) {
        return update.getMessage().hasLocation();
    }

    public String processTextMessage(Update update) {
        String messageText = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();

        switch (messageText) {
            case "/start":
                return textService.startBot();
            case "/get":
                int countriesQuantity = processService.getCountriesOfUser(chatId).getCountries().size();
                return textService.generateAllInfo(chatId, countriesQuantity);
            case "/listCountries":
                var countriesResponse = processService.getCountriesOfUser(chatId);
                return textService.generateListCountries(countriesResponse.getCountries());
            default:
                log.info(UNEXPECTED_MESSAGE);
                return UNEXPECTED_MESSAGE;
        }
    }


}
