package com.example.sprotte.errorhandling.Employee;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class UsernameAlreadyExistException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public UsernameAlreadyExistException(String message) {
		super(message, null, false, false);
	}
	
}