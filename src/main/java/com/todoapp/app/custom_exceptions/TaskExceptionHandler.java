package com.todoapp.app.custom_exceptions;

import com.todoapp.app.custom_exceptions.TaskExceptions.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TaskExceptionHandler {

    @ExceptionHandler( value = TaskNotFoundException.class )
    public ResponseEntity< Object > exception( TaskNotFoundException exception ){

        return new ResponseEntity<>( "Task Not Found" , HttpStatus.BAD_REQUEST );
    }
}
