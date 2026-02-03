package com.synapse.repository;

import com.synapse.model.Plan;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlanRepository implements PanacheRepository<Plan>{
    public Plan findByName(String name){
        return find("name", name.toUpperCase()).firstResult();
    }
}
