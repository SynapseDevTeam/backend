package com.synapse.services;

import java.util.UUID;

import org.eclipse.microprofile.jwt.JsonWebToken;

import com.synapse.dto.UserCreatedEvent;
import com.synapse.model.Account;
import com.synapse.repository.AccountRepository;
import com.synapse.util.JwtUtils;
import com.synapse.util.PasswordUtils;

import io.smallrye.reactive.messaging.annotations.Channel;
import io.smallrye.reactive.messaging.annotations.Emitter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;

@ApplicationScoped
public class AccountService {
    
    @Inject
    AccountRepository accRepo;

    @Inject
    PasswordUtils passUtils;

    @Inject
    JwtUtils jwtUtils;

    @Inject
    JsonWebToken jwt; //Auto inyecta el Token que venga con cada peticion

    @Inject
    @Channel("user-created")
    Emitter<UserCreatedEvent> userEmitter;

    @Transactional
    public Account register(Account acc){
        String eamilLimpio = acc.getEmail().trim().toLowerCase();


        if(accRepo.findByEmail(eamilLimpio).isPresent()) 
            throw new WebApplicationException("Email ya en uso", 409);
        if(accRepo.findByUsername(eamilLimpio).isPresent())
            throw new WebApplicationException("Nombre de usuario ya en uso", 409);


        acc.setPassword(passUtils.hashPassword(acc.getPassword()));
        acc.setEmail(eamilLimpio);

        accRepo.persist(acc);

        userEmitter.send(UserCreatedEvent.builder()
        .userId(acc.getId())
        .build());

        return acc;
    }

    public String logIn(String email, String password){
        String emailLimpio = email.trim().toLowerCase();

        Account acc = accRepo.findByEmail(emailLimpio).orElse(null);

        if(acc != null && passUtils.verificarPassword(password, acc.getPassword()))
            return jwtUtils.generateToken(acc);

        throw new WebApplicationException("Credenciales invalidas", 401);
    }
    
    @Transactional
    public void cambiarContrasenia(UUID id, String old, String next){
        String idTok = jwt.getClaim("accountId");

        if(idTok == null || !id.equals(UUID.fromString(idTok)))
            throw new WebApplicationException("No puedes cambiar la contrasenia de otra persona", 403);

        Account acc = accRepo.findById(id);
        
        if(acc == null || !passUtils.verificarPassword(old, acc.getPassword()))
            throw new WebApplicationException("Contraseña actual incorrecta", 400);

        acc.setPassword(passUtils.hashPassword(next));
    }
}