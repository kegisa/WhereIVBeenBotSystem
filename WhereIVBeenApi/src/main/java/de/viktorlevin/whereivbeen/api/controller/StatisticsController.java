package de.viktorlevin.whereivbeen.api.controller;

import de.viktorlevin.whereivbeen.api.dto.VisitedCountriesResponse;
import de.viktorlevin.whereivbeen.api.service.ProcessService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {
    private final ProcessService processService;

    @GetMapping("/visitedcontries")
    //TODO check validatoin of the param
    public VisitedCountriesResponse getVisitedCountries(@RequestParam @NotNull Long chatId) {
        log.info("Request for receiving visited countries for user {}", chatId);
        VisitedCountriesResponse response = processService.getCountriesOfUser(chatId);
        log.info("Visited countries for user {} received successfully", chatId);
        return response;
    }
}
