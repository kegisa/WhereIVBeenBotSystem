package de.viktorlevin.whereivbeen.api.service;


import de.viktorlevin.whereivbeen.api.entity.BotUser;
import de.viktorlevin.whereivbeen.api.repository.BotUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final BotUserRepository userRepository;

    @Transactional(readOnly = true)
    public Optional<BotUser> findUserByChatId(Long chatId) {
        log.info("Trying to find user by chatId {}", chatId);
        return userRepository.findByChatId(chatId);
    }
}
