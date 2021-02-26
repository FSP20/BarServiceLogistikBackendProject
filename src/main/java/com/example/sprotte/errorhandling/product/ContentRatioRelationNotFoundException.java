package com.example.sprotte.errorhandling.product;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ContentRatioRelationNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ContentRatioRelationNotFoundException(String message) {
        super(message, null, false, false);
    }
}
