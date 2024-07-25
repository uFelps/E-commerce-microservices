package com.services.order_ms.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ItemDTO(
        Long id,
        @NotBlank(message = "Name can't be null")
        String name,
        @NotNull(message = "Price can't be null") @PositiveOrZero(message = "Price can't be negative")
        BigDecimal price) {
}
