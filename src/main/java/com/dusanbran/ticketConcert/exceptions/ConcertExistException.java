package com.dusanbran.ticketConcert.exceptions;

public class ConcertExistException extends RuntimeException{

    public ConcertExistException(String message){
        super(message);
    }

}
