package de.viktorlevin.whereivbeen.api.statistics.coordinatesapi.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MapCountryResult {
    @JsonProperty("results")
    private List<Result> results;
}
