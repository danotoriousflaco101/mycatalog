package com.flaco.mycatalog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Gestore globale delle eccezioni per l'applicazione.
 * Cattura eccezioni specifiche e le formatta in una risposta HTTP consistente.
 * L'annotazione @ControllerAdvice lo rende applicabile a tutti i @Controller.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Gestisce l'eccezione ResourceNotFoundException.
     * @return una risposta con stato 404 Not Found e il messaggio dell'eccezione.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        Map<String, String> body = new HashMap<>();
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    /**
     * Gestisce gli errori di validazione (es. @NotBlank).
     * @return una risposta con stato 400 Bad Request e una mappa dei campi e degli errori.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Gestisce l'eccezione lanciata quando il tipo di media non corrisponde.
     * @return una risposta con stato 400 Bad Request e il messaggio dell'eccezione.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        Map<String, String> body = new HashMap<>();
        body.put("error", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    /**
     * Gestore generico per tutte le altre eccezioni non catturate.
     * @return una risposta con stato 500 Internal Server Error.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
        Map<String, String> body = new HashMap<>();
        body.put("message", "Si è verificato un errore interno: " + ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
