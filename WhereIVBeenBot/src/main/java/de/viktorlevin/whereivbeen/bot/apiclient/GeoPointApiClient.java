package de.viktorlevin.whereivbeen.bot.apiclient;

import de.viktorlevin.whereivbeen.bot.dto.SaveGeoPointRequestDto;
import de.viktorlevin.whereivbeen.bot.dto.VisitedCountriesResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "geo-point-api-client", url = "${geo-point-api.url}")
public interface GeoPointApiClient {
    @GetMapping("/statistics/visitedcontries")
    VisitedCountriesResponse getVisitedCountries(@RequestParam Long chatId);

    @PostMapping("/points")
    void saveGeoPoint(SaveGeoPointRequestDto saveGeoPointRequest);
}
