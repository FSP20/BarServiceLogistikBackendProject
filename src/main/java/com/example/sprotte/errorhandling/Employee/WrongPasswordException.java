package com.example.sprotte.errorhandling.Employee;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WrongPasswordException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public WrongPasswordException(String message) {
		super(message, null, false, false);
	}
}
