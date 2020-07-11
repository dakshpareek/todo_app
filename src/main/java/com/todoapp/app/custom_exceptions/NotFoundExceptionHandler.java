package com.todoapp.app.custom_exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class NotFoundExceptionHandler {

    @ExceptionHandler( value = NotFoundException.class )
    public ResponseEntity<Object> handleNotFoundException(NotFoundException exception ){

        List<String> errors = new ArrayList<String>();

        errors.add( exception.message_info );

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");

        MessageBody messageBody = new MessageBody( df.format(new Date()) , exception.status , exception.message_info, errors );

        return new ResponseEntity<>( messageBody , HttpStatus.NOT_FOUND );
    }
}
