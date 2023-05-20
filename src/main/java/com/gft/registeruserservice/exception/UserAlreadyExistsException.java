package com.gft.registeruserservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyExistsException extends ResponseStatusException {

    public UserAlreadyExistsException(HttpStatus statusCode, String message) {
        super(statusCode, message);
    }

}
