package de.viktorlevin.whereivbeen.api.statistics.coordinatesapi.client;

import de.viktorlevin.whereivbeen.api.statistics.coordinatesapi.client.dto.MapCountryResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "google-client", url = "${google.url}")
public interface GoogleMapClient {
    @GetMapping
    MapCountryResult getCountryInfoByCoordinates(
            @RequestParam("latlng") String latlng,
            @RequestParam("key") String key,
            @RequestParam("result_type") String resultType);
}
