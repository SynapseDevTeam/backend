package com.synapse.resource;

import java.util.List;
import java.util.UUID;

import com.synapse.model.Electrodomestico;
import com.synapse.repository.ElectrodomesticoRepository;
import com.synapse.repository.ManualRepository;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") UUID id) {
        return electRepo.findByIdOptional(id)
                .map(el -> Response.ok(el).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Path("/{id}/manuals")
    @Transactional
    public Response getManualsByDevice(@PathParam("id") UUID id) {
        return electRepo.findByIdOptional(id)
                .map(el -> Response.ok(el.getManuales()).build()) // Usamos el Getter que hizo Lombok
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Path("/search")
    public List<Electrodomestico> searchByMarca(@QueryParam("marca") String marca) {
        return electRepo.list("marca", marca);
    }
        
}
