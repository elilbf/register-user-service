package com.gft.registeruserservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserAlreadyExistsException extends ResponseStatusException {

    public UserAlreadyExistsException(HttpStatus statusCode, String message) {
        super(statusCode, message);
    }

}
