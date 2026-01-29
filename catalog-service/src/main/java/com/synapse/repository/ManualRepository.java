package com.synapse.repository;

import java.util.UUID;

import com.synapse.model.Manual;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ManualRepository implements PanacheRepositoryBase<Manual, UUID>{

}
