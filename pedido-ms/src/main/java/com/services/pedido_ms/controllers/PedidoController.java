package com.services.pedido_ms.controllers;

import com.services.pedido_ms.dtos.PedidoDTO;
import com.services.pedido_ms.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @GetMapping
    public ResponseEntity<String> get(){
        return ResponseEntity.ok("Hello World");
    }


    @PostMapping("/criarPedido")
    public ResponseEntity<PedidoDTO> criarPedido(@RequestBody PedidoDTO dto){

        PedidoDTO newDto = service.criarPedido(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDto.id()).toUri();

        return ResponseEntity.created(uri).body(newDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> buscarPedidoPorId(@PathVariable Long id){
        return ResponseEntity.ok(service.buscarPedidoPorId(id));
    }
}
