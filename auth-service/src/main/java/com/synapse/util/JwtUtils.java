package com.synapse.util;

import com.synapse.model.Account;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JwtUtils {
    public String generateToken(Account account){

        return Jwt.issuer("https://synapse-auth.com")
                .subject(account.getId().toString())
                .upn(account.getEmail())
                .claim("username", account.getUsername())
                .groups("USER") 
                .expiresIn(2592000)
                .sign();
    }
}
