package com.synapse.rabbitMQ;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.synapse.dto.UserCreatedEvent;
import com.synapse.service.UserProfileService;

import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserEventConsumer {

    @Inject UserProfileService profileService;

    @Inject
    ObjectMapper objectMapper;

    @Incoming("user-in")
    @Transactional
    public void onUserCreated(JsonObject json) {
        try {
            UserCreatedEvent event = objectMapper.readValue(json.toString(), UserCreatedEvent.class);
            
            profileService.createNewUser(event.getUserId());
            
        } catch (Exception e) {
            System.err.println("Error mapeando el evento de Rabbit");
            e.printStackTrace();
        }
    }
}