package de.viktorlevin.whereivbeen.bot.service;

import de.viktorlevin.whereivbeen.bot.apiclient.GeoPointApiClient;
import de.viktorlevin.whereivbeen.bot.dto.SaveGeoPointRequestDto;
import de.viktorlevin.whereivbeen.bot.dto.VisitedCountriesResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessService {
    private final GeoPointApiClient geoPointApiClient;

    public VisitedCountriesResponse getCountriesOfUser(Long chatId) {
        log.info("Send request to get visited countries for user {}", chatId);
        VisitedCountriesResponse response = geoPointApiClient.getVisitedCountries(chatId);
        log.info("Got response {} with visited countries for user {}", response, chatId);
        return response;
    }


    public void processGeoMessage(Update update) {
        Location location = update.getMessage().getLocation();
        String username = update.getMessage().getChat().getUserName();
        Long chatId = update.getMessage().getChat().getId();

        SaveGeoPointRequestDto requestDto = SaveGeoPointRequestDto.builder()
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .userName(username)
                .chatId(chatId).build();

        log.info("Send request to save geo point");
        geoPointApiClient.saveGeoPoint(requestDto);
        log.info("Geo point {} saved successfully", requestDto);
    }
}
