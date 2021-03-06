package com.example.sprotte.errorhandling.Bar;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BarNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public BarNotFoundException(String message) {
        super(message, null, false, false);
    }
}
