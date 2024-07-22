package com.services.order_ms.dtos;

import java.math.BigDecimal;

public record ItemDTO(Long id, String name, BigDecimal price) {
}
