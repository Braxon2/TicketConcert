package com.dusanbran.ticketConcert.exceptions;

import java.util.Date;

public class ErrorObject {

    private String message;
    private Integer statusCode;
    private Date timestamp;

    public ErrorObject() {
    }

    public ErrorObject(String message, Integer statusCode, Date timestamp) {
        this.message = message;
        this.statusCode = statusCode;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }


}
