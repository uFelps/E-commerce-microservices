package com.services.notificacao_ms.dtos;

import java.math.BigDecimal;

public record PedidoNotificacaoDTO(Long pedidoId, BigDecimal total, Long usuarioId, String email, String mensagem) {
}
