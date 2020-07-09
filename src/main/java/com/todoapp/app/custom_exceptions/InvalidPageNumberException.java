package com.todoapp.app.custom_exceptions;

public class InvalidPageNumberException extends RuntimeException {

    public String message;

    public InvalidPageNumberException(){}

    public InvalidPageNumberException( String msg ){

        message = msg;
    }
}
