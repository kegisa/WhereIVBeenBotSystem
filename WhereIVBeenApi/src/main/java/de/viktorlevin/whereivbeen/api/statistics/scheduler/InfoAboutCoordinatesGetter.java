package de.viktorlevin.whereivbeen.api.statistics.scheduler;


import de.viktorlevin.whereivbeen.api.entity.GeoPoint;
import de.viktorlevin.whereivbeen.api.repository.GeoPointRepository;
import de.viktorlevin.whereivbeen.api.statistics.coordinatesapi.client.GoogleMapClient;
import de.viktorlevin.whereivbeen.api.statistics.coordinatesapi.client.dto.MapCountryResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InfoAboutCoordinatesGetter {
    private final GeoPointRepository geoPointRepository;
    private final GoogleMapClient googleMapClient;

    @Value("${google.apikey}")
    private String apiKey;

    @Scheduled(fixedRate = 10000)
    public void getInfoAboutCoordinates() {
        List<GeoPoint> geoPoints = geoPointRepository.findAllByCountryIsNullOrCountryIs("");
        if (geoPoints == null || geoPoints.isEmpty()) {
            log.info("No geo points found for defining country");
            return;
        }
        GeoPoint first = geoPoints.get(0);
        String ltlg = first.getLatitude() + "," + first.getLongitude();
        log.info("Trying to define country name for point {}, {}", first.getLatitude(), first.getLongitude());
        log.info("Going to google API...");
        MapCountryResult result = googleMapClient.getCountryInfoByCoordinates(ltlg, apiKey, "country");
        log.info("Received response from Google APi {}", result);

        if (result.getResults() != null //TODO optional
                && !result.getResults().isEmpty()
                && result.getResults().get(0).getAddressComponents() != null
                && !result.getResults().get(0).getAddressComponents().isEmpty()) {
            String country = result.getResults().get(0).getAddressComponents().get(0).getLongName();
            first.setCountry(country);
            log.info("Country {} defined successfully", country);
        } else {
            first.setCountry("Occupied territory");
        }

        geoPointRepository.save(first);
        log.info("Point was successfully saved to DB with country");
    }
}
