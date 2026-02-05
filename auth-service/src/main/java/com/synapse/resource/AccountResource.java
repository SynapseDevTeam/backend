package com.synapse.resource;

import java.util.Map;
import java.util.UUID;

import org.eclipse.microprofile.jwt.JsonWebToken;

import com.synapse.dto.CambioDeContrasenia;
import com.synapse.dto.LoginRequest;
import com.synapse.dto.RegisterRequest;
import com.synapse.model.Account;
import com.synapse.services.AccountService;

import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccountResource {

    @Inject
    AccountService accServ;

    @Inject
    JsonWebToken jwt; //Auto inyecta el Token que venga con cada peticion

    @POST
    @Path("/register")
    public Response register(@Valid RegisterRequest req){
        Account accToCreate = accServ.register(req);
        return Response.status(Response.Status.CREATED).entity(accToCreate).build();
    }

    @POST
    @Path("/login")
    public Response login(@Valid LoginRequest req){
        String tok = accServ.logIn(req.getEmail(), req.getPassword());

        return Response.ok(Map.of("token", tok)).build();
    }

    @PATCH
    @Path("/change-password")
    @Authenticated
    public Response changePassword(@Valid CambioDeContrasenia data){
         String idTok = jwt.getSubject();
        accServ.cambiarContrasenia(UUID.fromString(idTok), data.oldPassword, data.newPassword);
        return Response.noContent().build();
    }
}