package com.example.sprotte.errorhandling.Bar;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class IllegalBarSegmentException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public IllegalBarSegmentException(String message) {super(message, null, false, false);}
}
