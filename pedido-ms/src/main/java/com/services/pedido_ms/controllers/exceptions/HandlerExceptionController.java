package com.services.pedido_ms.controllers.exceptions;

import com.services.pedido_ms.services.exceptions.DadoNaoEncontradoException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler(DadoNaoEncontradoException.class)
    public ResponseEntity<StandardError> dadoNaoEncontrado(DadoNaoEncontradoException e, HttpServletRequest request){
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
