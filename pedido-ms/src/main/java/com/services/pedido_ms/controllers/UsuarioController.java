package com.services.pedido_ms.controllers;

import com.services.pedido_ms.dtos.PedidoDTO;
import com.services.pedido_ms.dtos.UsuarioDTO;
import com.services.pedido_ms.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;


    @PostMapping("/cadastrarUsuario")
    public ResponseEntity<UsuarioDTO> cadastrarUsuario(@RequestBody UsuarioDTO dto){

        UsuarioDTO newUsuario = service.cadastrarUsuario(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newUsuario.id()).toUri();

        return ResponseEntity.created(uri).body(newUsuario);
    }
}
