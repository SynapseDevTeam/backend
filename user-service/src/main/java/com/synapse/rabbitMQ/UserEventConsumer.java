package com.synapse.rabbitMQ;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.synapse.dto.UserCreatedEvent;
import com.synapse.service.UserProfileService;

import io.smallrye.common.annotation.Blocking;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserEventConsumer {

    @Inject 
    UserProfileService profileService;

    @Incoming("user-in")
    @Blocking
    @Transactional
    public void onUserCreated(JsonObject json) {
        try {
            UserCreatedEvent event = json.mapTo(UserCreatedEvent.class);
            profileService.createNewUser(event.getUserId());
        } catch (Exception e) {
            throw new RuntimeException("Error al procesar el alta del usuario.");
        }
    }
}