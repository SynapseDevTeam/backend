package com.synapse.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.synapse.model.Home;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HomeRepository implements PanacheRepository<Home>{
    
    public List<Home> findByOwnerId(UUID ownerId){
        return find("ownerId", ownerId).list();
    }

    public Optional<Home> findByIdOptional(UUID homeId){
        return find("id", homeId).firstResultOptional();
    }
}
