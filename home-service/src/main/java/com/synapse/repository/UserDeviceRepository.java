package com.synapse.repository;


import com.synapse.model.UserDevice;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserDeviceRepository implements PanacheRepository<UserDevice>{
    
}
