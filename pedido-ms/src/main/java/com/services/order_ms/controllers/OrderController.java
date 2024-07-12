package com.services.order_ms.controllers;

import com.services.order_ms.dtos.OrderDTO;
import com.services.order_ms.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping
    public ResponseEntity<String> get(){
        return ResponseEntity.ok("Hello World");
    }


    @PostMapping("/createOrder")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO dto){

        OrderDTO newDto = service.createOrder(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDto.id()).toUri();

        return ResponseEntity.created(uri).body(newDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> findOrderById(@PathVariable Long id){
        return ResponseEntity.ok(service.findOrderById(id));
    }
}
