package com.synapse.resource;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.eclipse.microprofile.jwt.JsonWebToken;

import com.synapse.dto.UserProfileDTO;
import com.synapse.model.UserProfile;
import com.synapse.service.UserProfileService;

import io.quarkus.security.Authenticated;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestForm;

@Path("/profiles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
@ApplicationScoped
public class ProfileResource {

    @Inject
    UserProfileService profileService;

    @Inject
    JsonWebToken jwt;

    @GET
    @Path("/{id}")
    public Response getProfile(@PathParam("id") UUID id){
        return profileService.getProfile(id)
                .map(profile -> Response.ok(mapToDTO(profile)).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @PATCH
    @Path("/{id}")
    public Response updateProfile(@PathParam("id") UUID id, UserProfileDTO dto) {

        if (!isOwner(id)) {
            return Response.status(Response.Status.FORBIDDEN)
                    .entity("No puedes cambiar nada a tu usuario")
                    .build();
        }

        profileService.updateProfile(id, dto.getFullName(), dto.getTelephone(), dto.getAddress());
        return Response.ok().build();
    }

    private boolean isOwner(UUID idFromPath) {
        String userIdFromToken = jwt.getSubject(); 
        return idFromPath.toString().equals(userIdFromToken);
    }

    private UserProfileDTO mapToDTO(UserProfile p) {
        return UserProfileDTO.builder()
                .id(p.getId())
                .fullName(p.getFullName())
                .telephone(p.getTelephone())
                .address(p.getAddress())
                .planName(p.getSubscription() != null ? p.getSubscription().getPlan().getName() : "NONE")
                .build();
    }

    @POST
    @Path("/photo")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response changeProfilePhoto(@RestForm FileUpload file){

        UUID userId = UUID.fromString(jwt.getSubject());

        try {
            String path = profileService.saveProfilePhoto(file, userId);
            return Response.ok(path).build();
        } catch (IOException e) {
            return Response.status(500).entity("Error al guardar la foto").build();
        }
    }

    @GET
    @Path("/photo")
    public Response getProfilePhoto() {
        UUID userId = UUID.fromString(jwt.getSubject());
        File imageFile = profileService.getPhoto(userId);

        if (imageFile == null || !imageFile.exists()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        String contentType = imageFile.getName().endsWith(".png") ? "image/png" : "image/jpeg";

        return Response.ok(imageFile)
                .header("Content-Type", contentType) 
                .header("Content-Disposition", "inline; filename=\"" + imageFile.getName() + "\"")
                .build();
    }
}