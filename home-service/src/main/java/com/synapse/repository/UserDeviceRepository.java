package com.synapse.repository;


import java.util.Optional;
import java.util.UUID;

import com.synapse.model.UserDevice;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserDeviceRepository implements PanacheRepository<UserDevice>{

    public Optional<UserDevice> findByIdOptional(UUID deviceId) {
        return find("id", deviceId).firstResultOptional();
    }

}
