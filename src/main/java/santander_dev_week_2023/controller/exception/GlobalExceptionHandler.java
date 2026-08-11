package santander_dev_week_2023.controller.exception;

import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Trata regras de negócio / validações do usuário (HTTP 422)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handlebusinessException(@NonNull IllegalArgumentException businessException){
        return new ResponseEntity<>(businessException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // Trata especificamente quando um ID/Recurso não é encontrado no banco (HTTP 404)
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handlenotFoundExeception(NoSuchElementException notFoundExeception){
        return new ResponseEntity<>("Resource ID not found", HttpStatus.NOT_FOUND);
    }

    // CORREÇÃO AQUI: Trata qualquer outra exceção genérica/inesperada (HTTP 500)
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> handleUnexpectedException(Throwable unexpectedException) {
        var message = "Unexpected server error, see the logs.";
        logger.error(message, unexpectedException);
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}