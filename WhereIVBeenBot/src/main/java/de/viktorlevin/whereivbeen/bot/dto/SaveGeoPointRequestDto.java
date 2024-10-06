package de.viktorlevin.whereivbeen.bot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveGeoPointRequestDto {//extract to lib
    private Double latitude;
    private Double longitude;
    private Long chatId;
    private String userName;
}
