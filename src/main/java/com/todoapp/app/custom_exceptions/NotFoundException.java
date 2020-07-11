package com.todoapp.app.custom_exceptions;

public class NotFoundException extends RuntimeException{

    public String message_info;
    public int status;

    public NotFoundException(){}

    public NotFoundException(String msg , int status ){

        this.message_info = msg;
        this.status = status;
    }

}
