package de.viktorlevin.whereivbeen.api.repository;

import de.viktorlevin.whereivbeen.api.entity.GeoPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GeoPointRepository extends JpaRepository<GeoPoint, Integer> {
    List<GeoPoint> findAllByCountryIsNullOrCountryIs(String emptyCountry);
}
