package br.com.hackaton.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class ExceptionAdviceHandler {

    @ExceptionHandler(ExceptionAdvice.class)
    public ResponseEntity<ErrorResponse> handleException(ExceptionAdvice e) {
        var errorResponse = new ErrorResponse(e.getHttpStatus(), e.getMessage());

        if (e.getCodigoError().isExibirError()) {
            log.error("Error", e);
        } else {
            log.error("Error: {}", errorResponse.getMensagem());
        }

        return new ResponseEntity<>(errorResponse, e.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        var errorMessage = new ErrorResponse(HttpStatus.BAD_REQUEST, errors.toString());
        log.error("Error: {}", errorMessage.getMensagem());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

}