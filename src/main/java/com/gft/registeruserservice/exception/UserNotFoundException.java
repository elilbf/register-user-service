package com.gft.registeruserservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFoundException extends ResponseStatusException {

    public UserNotFoundException(HttpStatus statusCode, String message) {
        super(statusCode, message);
    }

}
