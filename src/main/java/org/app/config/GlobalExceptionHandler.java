package org.app.config;

import org.app.config.Exceptions.InvalidCredentialsException;
import org.app.config.Exceptions.UserAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //Bij een verkeerde datatype of regels opgesteld
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBadRequest(IllegalArgumentException e) {
        logger.error("Error", e);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Invalid request");
    }
    //Bij een overtreding van een constraint in de database
    @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
    public ResponseEntity<String> handleConflict(DataIntegrityViolationException e) {
        logger.error("Error", e);
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("Database constraint error");
    }
    //Bij foute authorizate
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<String> handleAuthorize(InvalidCredentialsException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Geen toegang");
    }
    //Bij fouten die optreden via validatie
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    //Bij een onverwachte error
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleUnexpected(Exception e) {
        logger.error("Error", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Onverwachte error");
    }
    //Bij een gebruiker bestaat al error
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handle(UserAlreadyExistsException ex) {
        return switch (ex.getField()) {
            case USERNAME -> ResponseEntity.badRequest().body("Username already exists");
            case EMAIL -> ResponseEntity.badRequest().body("Email already exists");
        };
    }
}
