package com.services.pedido_ms.dtos;

import com.services.pedido_ms.entities.Item;

import java.math.BigDecimal;

public record ItemDTO(Long id, String nome, BigDecimal preco) {
}
