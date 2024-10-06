package de.viktorlevin.whereivbeen.api.statistics.coordinatesapi.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    @JsonProperty("address_components")
    private List<AddressComponents> addressComponents;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddressComponents {
        @JsonProperty("long_name")
        private String longName;

        @JsonProperty("short_name")
        private String shortName;
    }
}
