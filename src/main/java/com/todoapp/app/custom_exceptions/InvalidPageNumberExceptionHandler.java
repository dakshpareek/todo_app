package com.todoapp.app.custom_exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class InvalidPageNumberExceptionHandler {

    @ExceptionHandler( value = InvalidPageNumberException.class )
    public ResponseEntity<Object> handleInvalidPageNumber( InvalidPageNumberException exception ){

        List<String> errors = new ArrayList<String>();

        errors.add( exception.message );

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");

        MessageBody messageBody = new MessageBody( df.format(new Date()) , 404 , exception.message, errors );

        return new ResponseEntity<>( messageBody , HttpStatus.NOT_FOUND );
    }
}
