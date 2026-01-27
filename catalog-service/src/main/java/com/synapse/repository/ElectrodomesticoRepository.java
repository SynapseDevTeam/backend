package com.synapse.repository;

import java.util.UUID;

import com.synapse.model.Electrodomestico;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ElectrodomesticoRepository implements PanacheRepositoryBase<Electrodomestico, UUID>{
    
}
