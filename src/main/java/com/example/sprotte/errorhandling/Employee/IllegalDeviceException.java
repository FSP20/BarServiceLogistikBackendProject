package com.example.sprotte.errorhandling.Employee;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class IllegalDeviceException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public IllegalDeviceException(String message) {super(message, null, false, false);}
}
