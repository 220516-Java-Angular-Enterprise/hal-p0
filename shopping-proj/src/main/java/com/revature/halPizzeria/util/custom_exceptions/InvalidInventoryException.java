package com.revature.halPizzeria.util.custom_exceptions;

public class InvalidInventoryException extends RuntimeException{
    public InvalidInventoryException(String message){
        super(message);
    }
}
