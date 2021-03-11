package com.example.sprotte.errorhandling.product;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class IllegalRecipeException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public IllegalRecipeException(String message) {super(message, null, false, false);}
}
