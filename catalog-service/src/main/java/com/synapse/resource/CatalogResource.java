package com.synapse.resource;


import java.util.UUID;

import com.synapse.dto.ElectrodomesticoDTO;
import com.synapse.dto.PagedResponseDTO;
import com.synapse.repository.ManualRepository;
import com.synapse.service.ElectrodomesticoService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
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
    ElectrodomesticoService electServ;

    @Inject
    ManualRepository manuRepo;

    @GET
    @Path("/search")
    public PagedResponseDTO<ElectrodomesticoDTO> search(
        @QueryParam("marca") String marca,
        @QueryParam("modelo") String modelo,
        @QueryParam("categoria") String categoria,
        @QueryParam("page") @DefaultValue("0") int page,
        @QueryParam("size") @DefaultValue("10") int size,
        @QueryParam("sort") @DefaultValue("marca") String sort
    ){
        return electServ.findAdvanced(marca, modelo, categoria, sort, "asc", page, size);
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") UUID id){
        return electServ.getElecById(id);
    }
}
