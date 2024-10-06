package de.viktorlevin.whereivbeen.api.service;

import de.viktorlevin.whereivbeen.api.entity.BotUser;
import de.viktorlevin.whereivbeen.api.entity.GeoPoint;
import de.viktorlevin.whereivbeen.api.repository.GeoPointRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class GeoPointService {
    private final GeoPointRepository pointRepository;

    public void savePoint(Double longitude, Double latitude, BotUser user) {
        GeoPoint geoPoint = new GeoPoint();
        geoPoint.setUser(user);
        geoPoint.setLongitude(longitude);
        geoPoint.setLatitude(latitude);
        pointRepository.save(geoPoint);
    }
}
