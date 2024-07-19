package com.services.payment_ms.controllers;

import com.services.payment_ms.services.exceptions.DataNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<StandardError> dataNotFound(DataNotFoundException e, HttpServletRequest request){

        StandardError error = new StandardError();
        HttpStatus status =  HttpStatus.BAD_REQUEST;

        error.setError("Data Not Found");
        error.setStatus(status.value());
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        error.setTimestamp(Instant.now());

        return ResponseEntity.status(status).body(error);
    }
}
