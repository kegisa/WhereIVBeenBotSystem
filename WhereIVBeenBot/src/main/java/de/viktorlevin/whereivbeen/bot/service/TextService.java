package de.viktorlevin.whereivbeen.bot.service;

import de.viktorlevin.whereivbeen.bot.dto.GeoPoint;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TextService {
    public String startBot() {
        return """
                Hi, just send me a geo point on the map where you've been. Or send /get command to show your map.
                """;
    }

    public String generateAllInfo(Long chatId, int countries) {
        String mapMessage = """
                You have visited %d countries. To see the list of countries use:
                /listCountries
                                    
                To see points on the map use the link below 
                %s
                """;

        String linkMap = "http://map.victorlevin.com/map/" + chatId;

        return mapMessage.formatted(countries, linkMap);
    }

    public String showMap(List<GeoPoint> points) {
        GeoPoint firstPoint = points.get(0);
        return """
                https://yandex.ru/maps/?ll=%f,%f&pt=%f,%f%s&z=2&l=map
                """.formatted(
                firstPoint.getLongitude(),
                firstPoint.getLatitude(),
                firstPoint.getLongitude(),
                firstPoint.getLatitude(),
                generateOtherPoints(points));
    }

    private String generateOtherPoints(List<GeoPoint> points) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < points.size(); i++) {
            sb.append("~%f,%f".formatted(points.get(i).getLongitude(), points.get(i).getLatitude()));
        }
        return sb.toString();
    }

    public String savePoint() {
        return """
                Point was saved
                """;
    }

    public String generateListCountries(List<String> countries) {
        StringBuilder sb = new StringBuilder();
        countries.forEach(country -> sb.append(country + '\n'));

        String message = """
                Your list:
                %s
                return to the begining:
                /get
                """;
        return message.formatted(sb.toString());
    }
}
