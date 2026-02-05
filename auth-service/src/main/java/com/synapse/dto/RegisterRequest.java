package com.synapse.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {

    @NotBlank(message = "El email es obligatorio.")
    @Email(message = "Pon un email de verdad, no me seas NPC")
    private String email;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres. ¡Seguridad ante todo!")
    private String password;

    @NotBlank(message = "El username es obligatorio")
    private String username;
}
