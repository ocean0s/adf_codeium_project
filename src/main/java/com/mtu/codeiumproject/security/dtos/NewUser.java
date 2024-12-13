package com.mtu.codeiumproject.security.dtos;

import com.mtu.codeiumproject.entity.MyUser;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewUser(
        @NotBlank @Size(min = 5, max = 20) String username,
        @NotBlank @Size(min = 8, max = 20) String password
) {
}

