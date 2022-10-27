package com.todo.controller.advice;


import com.todo.exceptions.EntityNotAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(EntityNotAvailableException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleEntityNotAvailableException(final EntityNotAvailableException e) {
        return e.getMessage();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleException(final Exception e) {
        return e.getMessage();
    }
}
