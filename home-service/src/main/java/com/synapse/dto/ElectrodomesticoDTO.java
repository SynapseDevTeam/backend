package com.synapse.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class ElectrodomesticoDTO {
    private UUID id;
    private String nombre;
    private String marca;
}
