package com.mtu.codeiumproject.controller.exception;

import com.mtu.codeiumproject.service.exception.HouseholdException;
import com.mtu.codeiumproject.service.exception.HouseholdNotFoundException;
import com.mtu.codeiumproject.service.exception.PetException;
import com.mtu.codeiumproject.service.exception.PetNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiError> handleException(Exception e) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @ExceptionHandler(value = PetException.class)
    public ResponseEntity<ApiError> handlePetException(PetException e) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @ExceptionHandler(value = HouseholdException.class)
    public ResponseEntity<ApiError> handleHouseholdException(HouseholdException e) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @ExceptionHandler(value = HouseholdNotFoundException.class)
    public ResponseEntity<ApiError> handleHouseholdNotFoundException(HouseholdNotFoundException e) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, e.getMessage());
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @ExceptionHandler(value = PetNotFoundException.class)
    public ResponseEntity<ApiError> handlePetNotFoundException(PetNotFoundException e) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, e.getMessage());
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationException(MethodArgumentNotValidException e) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Validation error");
        String message = e.getBindingResult().getFieldErrors().stream()
                .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
                .collect(Collectors.joining("; "));
        apiError.setMessage(message);
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        ApiError apiError = new ApiError(HttpStatus.CONFLICT, "Data integrity violation");
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }
}
