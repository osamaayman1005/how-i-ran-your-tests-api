package com.howiranyourtests.global.exception;

import org.springframework.http.HttpStatus;

public class GenericApiException extends RuntimeException{

    private final HttpStatus httpStatus;

    public GenericApiException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}


