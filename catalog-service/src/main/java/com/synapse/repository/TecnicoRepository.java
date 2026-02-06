package com.synapse.repository;


import com.synapse.model.Tecnico;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TecnicoRepository implements PanacheRepository<Tecnico>{
    
}
