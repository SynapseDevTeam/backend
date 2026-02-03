package com.synapse.repository;

import java.util.Optional;
import java.util.UUID;

import com.synapse.model.Subscription;
import com.synapse.model.SubscriptionStatus;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SubscriptionRepository implements PanacheRepository<Subscription> {
    
    public Optional<Subscription> findActiveByUser(UUID userId){
        return find("user.id = ?1 and status = ?2", userId, SubscriptionStatus.ACTIVE).firstResultOptional();
    }
}
