package football.manager.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Response> handleEntityNotFoundException(EntityNotFoundException ex,
                                                                  HttpServletRequest request) {
        return new ResponseEntity<>(
                new Response(HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getRequestURI()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PlayerAlreadyInTeamException.class)
    public ResponseEntity<Response> handlePlayerAlreadyInTeamException(PlayerAlreadyInTeamException ex,
                                                                       HttpServletRequest request) {
        return new ResponseEntity<>(
                new Response(HttpStatus.CONFLICT.value(), ex.getMessage(), request.getRequestURI()),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PlayerNotInTeamException.class)
    public ResponseEntity<Response> handlePlayerNotInTeamException(PlayerNotInTeamException ex,
                                                                   HttpServletRequest request) {
        return new ResponseEntity<>(
                new Response(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getRequestURI()),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<Response> handleInsufficientFundsException(InsufficientFundsException ex,
                                                                     HttpServletRequest request) {
        return new ResponseEntity<>(
                new Response(HttpStatus.CONFLICT.value(), ex.getMessage(), request.getRequestURI()),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleGeneralException(Exception ex,
                                                           HttpServletRequest request) {
        return new ResponseEntity<>(
                new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), request.getRequestURI()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex,
                                                                          HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        errors.put("timestamp", LocalDateTime.now().toString());
        errors.put("path", request.getRequestURI());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
