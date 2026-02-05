package com.synapse.service;

import com.synapse.model.Electrodomestico;
import com.synapse.model.Manual;
import com.synapse.repository.ElectrodomesticoRepository;
import com.synapse.repository.ManualRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class ManualService {
    @Inject
    ManualRepository manRepo;

    @Inject
    ElectrodomesticoRepository elecRepo;

    @ConfigProperty(name = "upload.directory")
    String uploadDir;

    @Transactional
    public Manual save(FileUpload file, String catalogId) throws IOException{
        Electrodomestico e = elecRepo.findById(UUID.fromString(catalogId));

        if (e == null) 
            throw new NotFoundException("Ese electrodoméstico no existe");


        String fileName = UUID.randomUUID().toString() + "_" + file.fileName();
        Path destPath = Paths.get(uploadDir, fileName);
        Files.createDirectories(destPath.getParent());
        Files.copy(file.uploadedFile(), destPath, StandardCopyOption.REPLACE_EXISTING);

        Manual manual = new Manual();
        manual.setElectrodomestico(e);
        manual.setFileUrl(destPath.toString());
        manual.setCustom("USER");

        manRepo.persist(manual);

        return manual;
    }

    public Path getManual(String manualId){
        Manual manual = manRepo.findById(UUID.fromString(manualId));
        if (manual == null) return null;
        return Paths.get(manual.getFileUrl());
    }

}
