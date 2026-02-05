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
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
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
        Home created = homeServ.createHome(request.getName(), ownerId);
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
        
        UserDevice created = homeServ.addDeviceToHome(
            homeId, 
            request.getCustomName(), 
            request.getCatalogProductId(), 
            ownerId
        );
        
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @GET
    @Path("/{homeId}")
    public Response getHomeDetails(@PathParam("homeId") UUID homeId) {
    UUID ownerId = UUID.fromString(jwt.getSubject());
    return Response.ok(homeServ.getHomeById(homeId, ownerId)).build();
}

    @GET
    @Path("/{homeId}/devices")
    public Response getHomeDevices(@PathParam("homeId") UUID homeId){
        UUID ownerId = UUID.fromString(jwt.getSubject());
        return Response.ok(homeServ.getDevicesByHome(homeId, ownerId)).build();
    }

    @DELETE
    @Path("/{homeId}")
    public Response deleteHome(@PathParam("homeId") UUID homeId){
        UUID ownerId = UUID.fromString(jwt.getSubject());
    
        homeServ.deleteHome(homeId, ownerId);
    
        return Response.noContent().build();
    }

    @PATCH
    @Path("/{homeId}")
    public Response updateHomeName(@PathParam("homeId") UUID homeId, HomeRequest request) {
        UUID ownerId = UUID.fromString(jwt.getSubject());
        Home updated = homeServ.updateHomeName(homeId, request.getName(), ownerId);
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{homeId}/devices/{deviceId}")
    public Response removeDevice(@PathParam("homeId") UUID homeId, @PathParam("deviceId") UUID deviceId) {
        UUID ownerId = UUID.fromString(jwt.getSubject());
        homeServ.removeDeviceFromHome(homeId, deviceId, ownerId);
        return Response.noContent().build();
    }

    @PATCH
    @Path("/devices/{deviceId}/transfer/{targetHomeId}")
    public Response transferDevice(@PathParam("deviceId") UUID deviceId, @PathParam("targetHomeId") UUID targetHomeId) {
        UUID ownerId = UUID.fromString(jwt.getSubject());
        UserDevice moved = homeServ.transferDevice(deviceId, targetHomeId, ownerId);
        return Response.ok(moved).build();
    }
}