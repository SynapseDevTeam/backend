package com.synapse.resource;

import java.util.List;

import com.synapse.dto.ElectrodomesticoDTO;
import com.synapse.dto.PagedResponseDTO;
import com.synapse.model.Electrodomestico;
import com.synapse.repository.ElectrodomesticoRepository;
import com.synapse.repository.ManualRepository;
import com.synapse.service.ElectrodomesticoService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

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

}
