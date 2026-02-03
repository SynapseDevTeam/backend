package com.synapse.repository;

import java.util.Optional;
import java.util.UUID;

import com.synapse.model.UserProfile;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserProfileRepository implements PanacheRepository<UserProfile>{
    public Optional<UserProfile> findById(UUID id){
        return find("id", id).firstResultOptional();
    }

}
