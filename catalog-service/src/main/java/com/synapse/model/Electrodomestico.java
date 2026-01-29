package com.synapse.model;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "catalog_electrodomestico")
@Getter @Setter
public class Electrodomestico{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private String categoria;

    @OneToMany(mappedBy = "electrodomestico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Manual> manuales;

    public List<Manual> getManuales() {
        return manuales;
    }
}
