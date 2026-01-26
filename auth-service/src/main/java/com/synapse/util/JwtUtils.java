package com.synapse.util;

import com.synapse.model.Account;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JwtUtils {
    public String generateToken(Account account){

        return Jwt.issuer("https://synapse-auth.com")
                .upn(account.getEmail())
                .claim("accountId", account.getId())
                .claim("username", account.getUsername())
                .expiresIn(2592000)
                .sign();
    }
}
