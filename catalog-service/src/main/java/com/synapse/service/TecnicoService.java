package com.synapse.service;

import java.util.List;

import com.synapse.model.Tecnico;
import com.synapse.repository.TecnicoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class TecnicoService {

    @Inject
    TecnicoRepository tecRepo;

    public List<Tecnico> getAll() {
        return tecRepo.listAll();
    }
    
}
