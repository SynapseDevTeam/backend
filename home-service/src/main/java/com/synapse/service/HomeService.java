package com.synapse.service;

import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.synapse.client.CatalogServiceClient;
import com.synapse.model.Home;
import com.synapse.model.UserDevice;
import com.synapse.repository.HomeRepository;
import com.synapse.repository.UserDeviceRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class HomeService {
    @Inject
    HomeRepository homeRepo;

    @Inject
    UserDeviceRepository userDeviceRepo;

    @Inject
    @RestClient
    CatalogServiceClient catalogClient;

    @Transactional
    public Home createHome(Home home, UUID ownerId){
        home.setOwnerId(ownerId);
        homeRepo.persist(home);
        return home;
    }

    public List<Home> getMyHomes(UUID ownerId){
        return homeRepo.findByOwnerId(ownerId);
    }

    @Transactional
    public UserDevice addDeviceToHome(UUID homeId, UserDevice device, UUID ownerId){
        
        Home home = homeRepo.findByIdOptional(homeId)
                .orElseThrow(() -> new NotFoundException("Esa casa no existe"));

        if (!home.getOwnerId().equals(ownerId)) {
            throw new SecurityException("No es tu casa");
        }

        var product = catalogClient.getProductById(device.getCatalogId().toString());
        if (product == null) throw new NotFoundException("Producto no existe");

        device.setHome(home);
        userDeviceRepo.persist(device);

        return device;
    }
}
