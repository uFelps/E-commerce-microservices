package com.services.order_ms.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record OrderItemDTO(
        @Valid
        ItemDTO item,
        @NotNull(message = "Quantity can't be null") @Positive(message = "Quantity can't be negative or zero")
        Integer quantity) {
}
