package com.example.sprotte.errorhandling.Employee;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RoleNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public RoleNotFoundException(String message) {
		super(message, null, false, false);
	}	
}
