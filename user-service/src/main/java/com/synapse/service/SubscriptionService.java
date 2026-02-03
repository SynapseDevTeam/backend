package com.synapse.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import com.synapse.model.Plan;
import com.synapse.model.Subscription;
import com.synapse.model.SubscriptionStatus;
import com.synapse.model.UserProfile;
import com.synapse.repository.PlanRepository;
import com.synapse.repository.SubscriptionRepository;
import com.synapse.repository.UserProfileRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class SubscriptionService{
    @Inject 
    SubscriptionRepository subscriptionRepo;

    @Inject 
    PlanRepository planRepo;

    @Inject 
    UserProfileRepository userRepo;

    @Transactional
    public Subscription createNewSubscription(UserProfile profile, String planName){

        Plan newPlan = planRepo.findByName(planName);
        if (newPlan == null) throw new NotFoundException("Ese plan no existe.");

        subscriptionRepo.findActiveByUser(profile.getId()).ifPresent(oldSub -> {
            oldSub.setStatus(SubscriptionStatus.CANCELLED); // O EXPIRED
            oldSub.setEndDate(LocalDateTime.now()); 
            });

        Subscription newSub = Subscription.builder()
            .user(profile)
            .plan(newPlan)
            .status(SubscriptionStatus.ACTIVE)
            .startDate(LocalDateTime.now())
            .endDate(LocalDateTime.now().plusMonths(1))
            .build();
        
        subscriptionRepo.persist(newSub);

        return newSub;
    }

    public Optional<Subscription> findActiveSubscription(UUID userId) {
        return subscriptionRepo.findActiveByUser(userId);
    }
    
}
