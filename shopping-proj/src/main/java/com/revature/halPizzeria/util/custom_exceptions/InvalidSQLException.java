package com.revature.halPizzeria.util.custom_exceptions;

public class InvalidSQLException extends RuntimeException{
    public InvalidSQLException(String message){
        super(message);
    }
}
