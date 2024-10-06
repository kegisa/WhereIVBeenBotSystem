package de.viktorlevin.whereivbeen.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitedCountriesResponse {
    private List<String> countries;
}