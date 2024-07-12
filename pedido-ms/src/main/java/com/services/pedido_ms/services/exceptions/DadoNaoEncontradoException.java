package com.services.pedido_ms.services.exceptions;

public class DadoNaoEncontradoException extends RuntimeException {
    public DadoNaoEncontradoException(String message){
        super(message);
    }
}
