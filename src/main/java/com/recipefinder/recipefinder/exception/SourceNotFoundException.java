package com.recipefinder.recipefinder.exception;

import org.springframework.web.bind.annotation.ResponseStatus;


public class SourceNotFoundException extends RuntimeException {
    public SourceNotFoundException(String message) {
        super(message);
    }
}
