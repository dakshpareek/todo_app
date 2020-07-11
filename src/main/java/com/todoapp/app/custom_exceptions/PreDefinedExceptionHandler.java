package com.todoapp.app.custom_exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@ControllerAdvice
public class PreDefinedExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<String> errors = new ArrayList<String>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");

        MessageBody messageBody = new MessageBody( df.format(new Date()) , HttpStatus.BAD_REQUEST.value() , "Validation Failed", errors );

        return new ResponseEntity<>( messageBody , HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler( value = DataIntegrityViolationException.class )
    public ResponseEntity<Object> handleDuplicateValues( DataIntegrityViolationException ex ){

        List<String> errors = new ArrayList<String>();

        errors.add( "User Already Exists" );

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");

        MessageBody messageBody = new MessageBody( df.format(new Date()) , HttpStatus.CONFLICT.value() , "User Already Exists", errors );

        return new ResponseEntity<>( messageBody , HttpStatus.CONFLICT );
    }
}
