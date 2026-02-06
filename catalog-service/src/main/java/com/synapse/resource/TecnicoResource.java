package com.synapse.resource;

import com.synapse.service.TecnicoService;

import io.quarkus.security.Authenticated;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/tecnicos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class TecnicoResource {
    
    @Inject
    TecnicoService tecServ;

    @GET
    @Authenticated
    public Response getTecnicos(){
        return Response.ok(tecServ.getAll()).build();
    }
}
