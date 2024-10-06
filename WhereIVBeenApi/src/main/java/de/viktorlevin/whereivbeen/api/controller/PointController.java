package de.viktorlevin.whereivbeen.api.controller;

import de.viktorlevin.whereivbeen.api.dto.SaveGeoPointRequestDto;
import de.viktorlevin.whereivbeen.api.service.ProcessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/points")
@RequiredArgsConstructor
public class PointController {
    private final ProcessService processService;

    @PostMapping
    public void saveGeoPoint(@RequestBody SaveGeoPointRequestDto saveGeoPointRequestDto) {
        log.info("Request for saving point {}", saveGeoPointRequestDto);
        processService.processGeoPoint(saveGeoPointRequestDto);
        log.info("Request for saving point {}, {} proceed successfully", saveGeoPointRequestDto.getLatitude(), saveGeoPointRequestDto.getLongitude());
    }
}
