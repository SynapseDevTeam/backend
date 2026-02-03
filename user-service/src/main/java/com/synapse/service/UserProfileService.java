package com.synapse.service;

import java.util.Optional;
import java.util.UUID;

import com.synapse.model.UserProfile;
import com.synapse.repository.PlanRepository;
import com.synapse.repository.UserProfileRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class UserProfileService{
    @Inject 
    SubscriptionService subscriptionService;

    @Inject 
    PlanRepository planRepo;

    @Inject 
    UserProfileRepository userRepo;

    @Transactional
    public void createNewUser(UUID userId){
        UserProfile profile = UserProfile.builder()
                .id(userId)
                .build();
        userRepo.persist(profile);

        subscriptionService.createNewSubscription(profile, "FREE");
    }

    @Transactional
    public void updateProfile(UUID id, String fullName, String telephone, String address){
        UserProfile user = userRepo.findById(id).orElseThrow(() -> new NotFoundException("Usuario no encontrado."));
          
        if (fullName != null && !fullName.isBlank())
             user.setFullName(fullName);
        if(telephone != null && !telephone.isBlank())
            user.setTelephone(telephone);
        if(address != null && !address.isBlank())
            user.setAddress(address);

        userRepo.persist(user);
    }

    public Optional<UserProfile> getProfile(UUID id){
        return userRepo.findById(id);
    }
}
