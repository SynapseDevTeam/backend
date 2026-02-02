package com.synapse.resource;

import java.util.UUID;

import org.eclipse.microprofile.jwt.JsonWebToken;

import com.synapse.dto.DeviceRequest;
import com.synapse.dto.HomeRequest;
import com.synapse.model.Home;
import com.synapse.model.UserDevice;
import com.synapse.service.HomeService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/home")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed("USER")
public class HomeResource {
    @Inject
    HomeService homeServ;

    @Inject
    JsonWebToken jwt;

    @POST
    public Response createHome(HomeRequest request){
        UUID ownerId = UUID.fromString(jwt.getSubject());
        Home home = new Home();
        home.setName(request.getName());

        Home created = homeServ.createHome(home, ownerId);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @GET
    public Response getMyHomes() {
        UUID ownerId = UUID.fromString(jwt.getSubject());
        return Response.ok(homeServ.getMyHomes(ownerId)).build();
    }

    @POST
    @Path("/{homeId}/devices")
    public Response addDevice(@PathParam("homeId") UUID homeId, DeviceRequest request) {
        UUID ownerId = UUID.fromString(jwt.getSubject());
        
        UserDevice device = new UserDevice();
        device.setCustomName(request.getCustomName());
        device.setCatalogId(request.getCatalogProductId());

        UserDevice created = homeServ.addDeviceToHome(homeId, device, ownerId);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }
}
