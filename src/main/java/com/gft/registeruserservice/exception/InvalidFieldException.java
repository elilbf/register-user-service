package com.gft.registeruserservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidFieldException extends ResponseStatusException {

    public InvalidFieldException(HttpStatus statusCode, String message) {
        super(statusCode, message);
    }

}
