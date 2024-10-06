package de.viktorlevin.whereivbeen.api.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(new Error(exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public record Error(String message) {}
}
