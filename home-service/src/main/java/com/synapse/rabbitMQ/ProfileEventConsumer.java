package com.synapse.rabbitMQ;

import java.util.UUID;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import com.synapse.service.HomeService;

import io.vertx.core.json.JsonObject;
import jakarta.inject.Inject;

public class ProfileEventConsumer {

    @Inject
    HomeService homeService;

    @Incoming("profile-in")
    public void onProfileInitialized(JsonObject json){
        UUID ownerId = UUID.fromString(json.getString("userId"));
        String name = json.getString("fullName");

        homeService.createDefaultHome(ownerId, "Casa de " + name);
    }
}
