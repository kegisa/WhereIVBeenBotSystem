package de.viktorlevin.whereivbeen.api.service;


import de.viktorlevin.whereivbeen.api.dto.SaveGeoPointRequestDto;
import de.viktorlevin.whereivbeen.api.dto.VisitedCountriesResponse;
import de.viktorlevin.whereivbeen.api.entity.BotUser;
import de.viktorlevin.whereivbeen.api.entity.GeoPoint;
import de.viktorlevin.whereivbeen.api.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessService {
    private final GeoPointService geoPointService;
    private final UserService userService;

    @Transactional
    public void processGeoPoint(SaveGeoPointRequestDto saveGeoPointRequestDto) {
        String username = saveGeoPointRequestDto.getUserName();
        Long chatId = saveGeoPointRequestDto.getChatId();
        log.info("Saving point to DB: {}", saveGeoPointRequestDto);
        geoPointService.savePoint(
                saveGeoPointRequestDto.getLongitude(),
                saveGeoPointRequestDto.getLatitude(),
                getUserByChatId(chatId, username));
        log.info("Point {}, {} saved to DB successfuly", saveGeoPointRequestDto.getLatitude(), saveGeoPointRequestDto.getLongitude());
    }


    @Transactional(readOnly = true)
    public List<GeoPoint> getGeoPoints(Long chatId) {
        Optional<BotUser> optionalBotUser = userService.findUserByChatId(chatId);
        if (optionalBotUser.isEmpty()) {
            throw new NotFoundException("ChatId %s not found".formatted(chatId));
        }
        BotUser user = optionalBotUser.get();
        List<GeoPoint> points = user.getPoints();

        if (points.isEmpty()) {
            throw new NotFoundException("User %l does not have any points".formatted(chatId));
        }
        return points;
    }

    @Transactional(readOnly = true)
    public VisitedCountriesResponse getCountriesOfUser(Long chatId) {
        return new VisitedCountriesResponse(getGeoPoints(chatId).stream()
                .map(GeoPoint::getCountry)
                .distinct()
                .filter(Objects::nonNull)
                .filter(s -> !s.isEmpty())
                .toList());
    }

    private BotUser getUserByChatId(Long chatId, String username) {
        Optional<BotUser> optionalBotUser = userService.findUserByChatId(chatId);
        return optionalBotUser.orElseGet(() -> new BotUser(username, chatId));
    }
}