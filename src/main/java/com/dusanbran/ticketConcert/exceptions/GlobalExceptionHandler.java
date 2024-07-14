package com.dusanbran.ticketConcert.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementFoundExceptions.class)
    public ResponseEntity<ErrorObject> handleNoSuchElementFoundException(NoSuchElementFoundExceptions ex){
        ErrorObject errorObject = new ErrorObject();

        errorObject.setMessage(ex.getMessage());
        errorObject.setTimestamp(new Date());
        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConcertExistException.class)
    public ResponseEntity<ErrorObject> handleConcertExistException(ConcertExistException ex){
        ErrorObject errorObject = new ErrorObject();

        errorObject.setMessage(ex.getMessage());
        errorObject.setTimestamp(new Date());
        errorObject.setStatusCode(HttpStatus.CONFLICT.value());

        return new ResponseEntity<>(errorObject, HttpStatus.CONFLICT);
    }

}
