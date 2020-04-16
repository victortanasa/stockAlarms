package com.victor.stockalarms.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handleDataAccessException(final DataAccessException e) {
        return ResponseEntity.badRequest().body(e.getMostSpecificCause().getMessage());
    }

}