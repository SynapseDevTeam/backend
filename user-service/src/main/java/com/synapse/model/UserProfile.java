package com.synapse.model;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_profiles")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
public class UserProfile {

    @Id
    @Column(name = "id")
    private UUID id; //Relacion logica con PK de account que da el auth service

    @Column(name = "fullName", length = 100)
    private String fullName;

    @Column(name = "telephone", length = 20)
    private String telephone;

    @Column(name = "address", length = 255)
    private String address;
    
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Subscription subscription;

    public String photUrl;
}
