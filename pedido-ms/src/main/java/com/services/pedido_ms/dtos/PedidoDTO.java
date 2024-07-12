package com.services.pedido_ms.dtos;

import com.services.pedido_ms.entities.Usuario;
import com.services.pedido_ms.entities.enuns.PedidoStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoDTO(Long id, LocalDateTime criadoEm, BigDecimal total, PedidoStatus status, Long usuarioId, List<PedidoItemDTO> itens) {
}
