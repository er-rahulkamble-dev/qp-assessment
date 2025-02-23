package com.dev.grocery_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LowInventoryException.class)
    public ResponseEntity<String> lowInventoryException(LowInventoryException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.PARTIAL_CONTENT);
    }

    @ExceptionHandler(OutOfStockException.class)
    public ResponseEntity<String> outOfStockException(OutOfStockException e){
        return ResponseEntity.unprocessableEntity().body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
