package com.example.sprotte.errorhandling.Employee;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class IllegalEmployeeRoleException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public IllegalEmployeeRoleException(String message) {super(message, null, false, false);}
}
