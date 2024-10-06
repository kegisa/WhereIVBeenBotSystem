package de.viktorlevin.whereivbeen.api.utils;

import de.viktorlevin.whereivbeen.api.entity.GeoPoint;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class RedirectUtils {
    public static String generateMapForRedirect(List<GeoPoint> points) {
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

    private static String generateOtherPoints(List<GeoPoint> points) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < points.size(); i++) {
            sb.append("~%f,%f".formatted(points.get(i).getLongitude(), points.get(i).getLatitude()));
        }
        return sb.toString();
    }
}
