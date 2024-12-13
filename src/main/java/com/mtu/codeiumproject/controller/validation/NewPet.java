package com.mtu.codeiumproject.controller.validation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NewPet(
        @NotBlank
        String name,
        @NotBlank
        String animalType,
        @NotNull
        Integer age,
        @NotBlank
        String breed
) {
}