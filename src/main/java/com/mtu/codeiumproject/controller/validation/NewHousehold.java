package com.mtu.codeiumproject.controller.validation;

import com.mtu.codeiumproject.entity.Pet;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record NewHousehold(
        @NotBlank
        @Size(max = 100)
        String eircode,
        @NotNull
        Integer numberOfOccupants,
        @NotNull
        Integer maxNumberOfOccupants,
        @NotNull
        Boolean isOwnerOccupied,
        @NotNull
        List<Pet> pets
) {
}