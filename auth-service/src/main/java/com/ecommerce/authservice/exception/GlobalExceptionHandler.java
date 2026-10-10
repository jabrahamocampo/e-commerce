package com.ecommerce.authservice.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecommerce.authservice.dto.response.ApiError;
import com.ecommerce.authservice.dto.response.FieldValidationError;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice 
public class GlobalExceptionHandler {

    private ResponseEntity<ApiError> buildError(
        HttpStatus status,
        String message,
        HttpServletRequest request ) {
            
            ApiError error = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(message)
                .path(request.getRequestURI())
                .build();

                return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler (ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(
        ResourceNotFoundException ex,
        HttpServletRequest request ) {

        return buildError(HttpStatus.NOT_FOUND,
                          ex.getMessage(), 
                          request);

    }

    @ExceptionHandler (ResourceAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleAlreadyExists(
        ResourceAlreadyExistsException ex,
        HttpServletRequest request ) {

        return buildError(HttpStatus.CONFLICT,ex.getMessage(), request);

    }

    @ExceptionHandler (InvalidCredentialsException.class)
    public ResponseEntity<ApiError> handleInvalidCredentials(
        InvalidCredentialsException ex,
        HttpServletRequest request ) {

        return buildError(HttpStatus.UNAUTHORIZED, ex.getMessage(), request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(
            Exception ex,
            HttpServletRequest request) {

        return buildError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(
                MethodArgumentNotValidException ex,
                HttpServletRequest request) {

        List<FieldValidationError> errors =
                ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new FieldValidationError(
                        error.getField(),
                        error.getDefaultMessage()))
                .toList();

        ApiError apiError = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message("Validation failed")
                .path(request.getRequestURI())
                .fieldErrors(errors)
                .build();

        return ResponseEntity.badRequest().body(apiError);

     }

}
