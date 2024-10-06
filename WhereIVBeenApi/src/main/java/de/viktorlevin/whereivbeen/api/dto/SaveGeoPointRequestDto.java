package de.viktorlevin.whereivbeen.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveGeoPointRequestDto {
    private Double latitude;
    private Double longitude;
    private Long chatId;
    private String userName;
}
