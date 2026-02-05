package com.synapse.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotBlank(message = "El email es necesario.")
    @Email(message = "Formato de email inválido")
    private String email;

    @NotBlank(message = "La contraseña es necesaria.")
    private String password;
}