package com.synapse.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
public class Account{ 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(min = 3, max = 20, message="El username debe tener entre 3 y 20 caracteres")
    private String username;

    @Column(unique = true, nullable = false)
    @NotBlank(message="El email es obligatorio")
    @Email(message="El formato del email no es válido")
    private String email;

    @Column(nullable = false)
    @NotBlank(message="La contraseña no puede estar vacía")
    @Size(min = 8, message="La contraseña debe tener al menos 8 caracteres")
    @Pattern(
        regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$",
        message = "La contraseña debe tener al menos 8 caracteres, una mayúscula, una minúscula y un número"
    )
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Account(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
