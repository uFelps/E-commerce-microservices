package com.services.order_ms.controllers.exceptions;

import com.services.order_ms.services.exceptions.DataNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<StandardError> dataNotFound(DataNotFoundException e, HttpServletRequest request){
        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());

        HttpStatus status = HttpStatus.BAD_REQUEST;
        error.setStatus(status.value());
        error.setMessage(e.getMessage());
        error.setError("Data Not Found!");
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validationError(MethodArgumentNotValidException e, HttpServletRequest request){

        ValidationError error = new ValidationError();
        error.setTimestamp(Instant.now());

        HttpStatus status = HttpStatus.BAD_REQUEST;
        error.setStatus(status.value());
        error.setMessage(e.getMessage());
        error.setError("Validation Error!");
        error.setPath(request.getRequestURI());

        for (FieldError err : e.getBindingResult().getFieldErrors()){
            error.addError(err.getField(), err.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(error);
    }
}
