package de.viktorlevin.whereivbeen.bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class WhereIVBeenBotApplication {
    public static void main(String[] args) {
        SpringApplication.run(WhereIVBeenBotApplication.class, args);
    }
}