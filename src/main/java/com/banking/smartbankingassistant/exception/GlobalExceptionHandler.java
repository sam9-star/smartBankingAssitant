package com.banking.smartbankingassistant.exception;
 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
 
import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;
 
@RestControllerAdvice
public class GlobalExceptionHandler {
 
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(
            MethodArgumentNotValidException ex) {
 
        String details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": "
                        + error.getDefaultMessage())
                .collect(Collectors.joining("; "));
 
        return errorResponse(
                HttpStatus.BAD_REQUEST,
                "VALIDATION_ERROR",
                details
        );
    }
 
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<Map<String, Object>> handleMethodValidation(
            HandlerMethodValidationException ex) {
 
        return errorResponse(
                HttpStatus.BAD_REQUEST,
                "VALIDATION_ERROR",
                "One or more request values are invalid."
        );
    }
 
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidJson(
            HttpMessageNotReadableException ex) {
 
        return errorResponse(
                HttpStatus.BAD_REQUEST,
                "INVALID_REQUEST",
                "The request body is missing or contains invalid JSON."
        );
    }
 
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String, Object>> handleServiceFailure(
            IllegalStateException ex) {
 
        // Do not return the exception message: it may contain
        // implementation details that should remain private.
        return errorResponse(
                HttpStatus.BAD_GATEWAY,
                "AI_SERVICE_ERROR",
                "The AI service could not complete the request. "
                        + "Please try again later."
        );
    }
 
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleUnexpected(
            Exception ex) {
 
        return errorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "INTERNAL_ERROR",
                "An unexpected error occurred."
        );
    }
 
    private ResponseEntity<Map<String, Object>> errorResponse(
            HttpStatus status,
            String code,
            String message) {
 
        return ResponseEntity.status(status).body(Map.of(
                "timestamp", Instant.now().toString(),
                "status", status.value(),
                "error", code,
                "message", message
        ));
    }
}
 