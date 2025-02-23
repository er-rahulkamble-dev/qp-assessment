package com.dev.grocery_service.exception;

public class OutOfStockException extends RuntimeException {

    OutOfStockException(String message){
        super(message);
    }
}
