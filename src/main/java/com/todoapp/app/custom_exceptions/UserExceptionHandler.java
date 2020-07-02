package com.todoapp.app.custom_exceptions;

import com.todoapp.app.custom_exceptions.UserExceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler( value = UserNotFoundException.class )
    public ResponseEntity<Object> exception( UserNotFoundException exception ){

        return new ResponseEntity<>( "User Not Found" , HttpStatus.BAD_REQUEST );
    }
}
