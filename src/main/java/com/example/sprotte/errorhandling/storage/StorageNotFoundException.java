package com.example.sprotte.errorhandling.storage;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StorageNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public StorageNotFoundException(String message) {
        super(message, null, false, false);
    }
}
