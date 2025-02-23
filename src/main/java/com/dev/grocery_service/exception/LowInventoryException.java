package com.dev.grocery_service.exception;

public class LowInventoryException extends RuntimeException{

    public LowInventoryException(String message){
        super(message);
    }
}
