package com.synapse.resource;

import java.util.List;

import com.synapse.model.Electrodomestico;
import com.synapse.repository.ElectrodomesticoRepository;
import com.synapse.repository.ManualRepository;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/catalog")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CatalogResource {

    @Inject
    ElectrodomesticoRepository electRepo;

    @Inject
    ManualRepository manuRepo;

    @GET
    public List<Electrodomestico> getAll(){
        return electRepo.listAll();
    }
}
