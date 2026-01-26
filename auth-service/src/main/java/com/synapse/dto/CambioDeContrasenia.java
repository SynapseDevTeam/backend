package com.synapse.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CambioDeContrasenia {
    @NotBlank
    public String oldPassword;

    @NotBlank
    @Pattern(
        regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$",
        message = "La nueva contraseña debe tener al menos 8 caracteres, una mayúscula, una minúscula y un número"
    )
    public String newPassword;
}
