package com.synapse.resource;

import java.util.UUID;

import org.eclipse.microprofile.jwt.JsonWebToken;

import com.synapse.dto.UserSubscriptionStatusDTO;
import com.synapse.model.Subscription;
import com.synapse.model.UserProfile;
import com.synapse.repository.UserProfileRepository;
import com.synapse.service.SubscriptionService;

import io.quarkus.security.Authenticated;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/subscriptions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
@ApplicationScoped
public class SubscriptionResource {
    @Inject
    SubscriptionService subscriptionService;

    @Inject
    UserProfileRepository userRepo;

    @Inject
    JsonWebToken jwt;

    @GET
    @Path("/status/{userId}")
    public Response getSubcriptionStatus(@PathParam("userId") UUID userId){
        if (!isOwner(userId)) {
            return Response.status(Response.Status.FORBIDDEN)
                    .entity("No intentes espiar el plan de tus colegas, locotrón")
                    .build();
        }

        return subscriptionService.findActiveSubscription(userId)
                .map(sub -> Response.ok(mapToStatusDTO(sub)).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).entity("No tienes suscripción activa, gordo").build());
    }

    @POST
    @Path("/change-plan")
    public Response changePlan(@QueryParam("planName") String planName) {
        // Aquí sacamos el ID directamente del token para que sea imposible de hackear
        UUID userId = UUID.fromString(jwt.getSubject());
        
        UserProfile profile = userRepo.findById(userId)
        .orElseThrow(() -> new NotFoundException("Usuario no encontrado en el sistema."));

        subscriptionService.createNewSubscription(profile, planName);
        return Response.ok().entity("Plan actualizado. Ahora eres un Giga-Chad " + planName).build();
    }

    private boolean isOwner(UUID userId) {
        return userId.toString().equals(jwt.getSubject());
    }

    private UserSubscriptionStatusDTO mapToStatusDTO(Subscription sub) {
        return UserSubscriptionStatusDTO.builder()
                .planName(sub.getPlan().getName())
                .status(sub.getStatus().name())
                .expiresAt(sub.getEndDate() != null ? sub.getEndDate().toString() : "NEVER")
                .build();
    }
}
