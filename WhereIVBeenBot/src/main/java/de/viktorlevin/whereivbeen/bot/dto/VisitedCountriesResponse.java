package de.viktorlevin.whereivbeen.bot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitedCountriesResponse {
    private List<String> countries = new ArrayList<>();
}