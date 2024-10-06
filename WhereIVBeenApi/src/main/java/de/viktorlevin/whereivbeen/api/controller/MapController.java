package de.viktorlevin.whereivbeen.api.controller;

import de.viktorlevin.whereivbeen.api.service.ProcessService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static de.viktorlevin.whereivbeen.api.utils.RedirectUtils.generateMapForRedirect;

@Slf4j
@RestController
@RequestMapping("/map")
@RequiredArgsConstructor
public class MapController {
    private final ProcessService processService;

    @GetMapping("/{chatId}")
    public void redirect(HttpServletResponse response, @PathVariable("chatId") Long chatId) throws IOException {
        log.info("Redirecting for user {}", chatId);
        response.sendRedirect(generateMapForRedirect(processService.getGeoPoints(chatId)));
    }
}