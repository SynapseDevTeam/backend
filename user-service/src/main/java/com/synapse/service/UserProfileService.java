package com.synapse.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

import com.synapse.dto.ProfileInitializedEvent;
import com.synapse.model.Plan;
import com.synapse.model.Subscription;
import com.synapse.model.SubscriptionStatus;
import com.synapse.model.UserProfile;
import com.synapse.repository.PlanRepository;
import com.synapse.repository.UserProfileRepository;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import io.smallrye.reactive.messaging.annotations.Channel;
import io.smallrye.reactive.messaging.annotations.Emitter;
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

    @Inject
    @Channel("profile-out")
    Emitter<ProfileInitializedEvent> profileEmiter;

    @Transactional
    public void createNewUser(UUID userId, String name) {
        if (userRepo.findById(userId).isPresent()) {
            return; 
        }

        Plan freePlan = planRepo.findByName("FREE")
                .orElseThrow(() -> new NotFoundException("¡Bofetón! El plan FREE no existe en la DB"));

        UserProfile profile = UserProfile.builder()
                .id(userId)
                .fullName(name)
                .build();

        Subscription sub = Subscription.builder()
                .user(profile)
                .plan(freePlan)
                .status(SubscriptionStatus.ACTIVE)
                .build();

        profile.setSubscription(sub);

        userRepo.persist(profile);

        ProfileInitializedEvent event = new ProfileInitializedEvent(userId, name);
 
        profileEmiter.send(event);
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

    @Transactional
    public String saveProfilePhoto(FileUpload f, UUID userId) throws IOException{
        UserProfile profile = userRepo.findById(userId).orElseThrow(() -> new NotFoundException("Usuario no encontrado."));
        
        String fileName = "profile_" + userId + ".jpg"; 
        Path destPath = Paths.get("/deployments/profilephotos", fileName);

        Files.createDirectories(destPath.getParent());
        Files.copy(f.filePath(), destPath, StandardCopyOption.REPLACE_EXISTING);

        profile.setPhotUrl(destPath.toString());
        userRepo.persist(profile);
        return "/profiles/photo/" + userId;
    }

    public File getPhoto(UUID userId) {
        UserProfile profile = userRepo.findById(userId)
        .orElseThrow(() -> new NotFoundException("Perfil no encontrado."));

        String path = profile.getPhotUrl();

        File imageFile = new File(path);

        return imageFile;
    }
}
