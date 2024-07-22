package com.services.order_ms.controllers.exceptions;

import com.services.order_ms.services.exceptions.DataNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<StandardError> dadoNaoEncontrado(DataNotFoundException e, HttpServletRequest request){
        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());

        HttpStatus status = HttpStatus.BAD_REQUEST;
        error.setStatus(status.value());
        error.setMessage(e.getMessage());
        error.setError("Dado não encontrado!");
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }
}
