package com.revature.halPizzeria.util.custom_exceptions;

public class InvalidPizzaException extends RuntimeException{
    public InvalidPizzaException(String message){
        super(message);
    }
}
