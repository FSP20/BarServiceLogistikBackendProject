package com.example.sprotte.errorhandling.Bar;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class IllegalBarContainerException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public IllegalBarContainerException(String message) {super(message, null, false, false);}
}
