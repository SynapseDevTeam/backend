package com.synapse.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.synapse.client.CatalogServiceClient;
import com.synapse.model.Home;
import com.synapse.model.UserDevice;
import com.synapse.repository.HomeRepository;
import com.synapse.repository.UserDeviceRepository;

import io.quarkus.security.ForbiddenException;
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
    
    @Inject
    JsonWebToken jwt;

    @Transactional
    public Home createHome(String name, UUID ownerId){
        Home home = Home.builder()
                .name(name)
                .ownerId(ownerId)
                .build();
        
        homeRepo.persist(home);
        return home;
    }

    public List<Home> getMyHomes(UUID ownerId){
        return homeRepo.findByOwnerId(ownerId);
    }

    @Transactional
    public void createDefaultHome(UUID ownerId, String homeName) {
        Home defaultHome = Home.builder()
            .name(homeName)
            .ownerId(ownerId)
            .build();
        
        homeRepo.persist(defaultHome);
    }

    @Transactional
    public List<UserDevice> getDevicesByHome(UUID homeId, UUID ownerId){
        Home home = homeRepo.findByIdOptional(homeId)
        .orElseThrow(() -> new NotFoundException("Esa casa no existe."));

        if (!home.getOwnerId().equals(ownerId)) {
            throw new ForbiddenException("Esa casa no es tuya.");
        }

        return home.getDevices();
    }

    @Transactional
    public void deleteHome(UUID homeId, UUID ownerId) {
        Home home = homeRepo.findByIdOptional(homeId)
        .orElseThrow(() -> new NotFoundException("Esa casa no existe."));

        if (!home.getOwnerId().equals(ownerId)) {
            throw new ForbiddenException("No puedes borrar lo que no te pertenece.");
        }

        homeRepo.delete(home);
    }

    public Home getHomeById(UUID homeId, UUID ownerId) {
        Home home = homeRepo.findByIdOptional(homeId)
        .orElseThrow(() -> new NotFoundException("Casa no encontrada, gordo."));

        if (!home.getOwnerId().equals(ownerId)) throw new ForbiddenException("No es tu búnker.");

        return home;
    }

    @Transactional
    public Home updateHomeName(UUID homeId, String newName, UUID ownerId) {
        Home home = homeRepo.findByIdOptional(homeId)
        .orElseThrow(() -> new NotFoundException("Casa no encontrada."));

        if (!home.getOwnerId().equals(ownerId)) throw new ForbiddenException("No es tu casa.");

        home.setName(newName);

        return home;
    }

    @Transactional
    public void removeDeviceFromHome(UUID homeId, UUID deviceId, UUID ownerId) {
        Home home = homeRepo.findByIdOptional(homeId)
        .orElseThrow(() -> new NotFoundException("Casa no encontrada."));

        if (!home.getOwnerId().equals(ownerId)) throw new ForbiddenException();

        boolean removed = home.getDevices().removeIf(d -> d.getId().equals(deviceId));

        if (!removed) throw new NotFoundException("Ese aparato no está en esta casa, loco.");
    }

    @Transactional
    public UserDevice transferDevice(UUID deviceId, UUID targetHomeId, UUID ownerId) {
        
        UserDevice device = userDeviceRepo.findByIdOptional(deviceId)
            .orElseThrow(() -> new NotFoundException("El dispositivo no existe, gordo."));


        if (!device.getHome().getOwnerId().equals(ownerId)) {
            throw new ForbiddenException("Ese cacharro no es tuyo.");
        }

        Home targetHome = homeRepo.findByIdOptional(targetHomeId)
            .orElseThrow(() -> new NotFoundException("La casa destino no existe."));

        if (!targetHome.getOwnerId().equals(ownerId)) {
            throw new ForbiddenException("No puedes mover cosas a búnkeres ajenos.");
        }

        device.setHome(targetHome);

        return device;
    }

    @Transactional
    public UserDevice addDeviceToHome(UUID homeId, String customName, UUID catalogId, UUID ownerId) {
        Home home = homeRepo.findByIdOptional(homeId)
                .orElseThrow(() -> new NotFoundException("Esa casa no existe, gordo"));

        if (!home.getOwnerId().equals(ownerId)) {
            throw new ForbiddenException("Esa casa no es tuya, no me seas listeras");
        }

        UserDevice device = new UserDevice();
        device.setCatalogId(catalogId);
        device.setCustomName(customName);
        device.setHome(home);

        userDeviceRepo.persist(device);
        return device;
    }
}