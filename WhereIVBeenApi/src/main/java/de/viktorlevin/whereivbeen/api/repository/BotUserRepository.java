package de.viktorlevin.whereivbeen.api.repository;

import de.viktorlevin.whereivbeen.api.entity.BotUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BotUserRepository extends JpaRepository<BotUser, Integer> {
    Optional<BotUser> findByChatId(Long chatId);
}
