package com.synapse.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tecnicos")
@Getter @Setter
@Builder
public class Tecnico {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    public String name;

    public String especialidad;

    public String contacto;

    public String precioPorHora;
}
