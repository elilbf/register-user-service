package com.gft.registeruserservice.hander;

import com.gft.registeruserservice.exception.UserAlreadyExistsException;
import com.gft.registeruserservice.exception.UserNotFoundException;
import com.gft.registeruserservice.model.ErrorCustomModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorCustomModel userAlreadyExistsException(UserAlreadyExistsException ex) {
        return ErrorCustomModel.builder()
                .message(ex.getBody().getDetail())
                .build();
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorCustomModel userNotFoundException(UserNotFoundException ex) {
        return ErrorCustomModel.builder()
                .message(ex.getBody().getDetail())
                .build();
    }

}