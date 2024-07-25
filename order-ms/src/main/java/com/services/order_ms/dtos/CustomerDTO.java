package com.services.order_ms.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CustomerDTO(
        Long id,
        @NotBlank(message = "Name can't be empty") @Length(min = 3, max = 30, message = "name must be between 3 and 30 characters")
        String name,
        @NotBlank(message = "Cpf can't be empty") @Length(min = 14, max = 14, message = "cpf must be 14 characters long, including dots.")
        String cpf,
        @NotBlank(message = "Email can't be empty") @Email
        String email) {
}
