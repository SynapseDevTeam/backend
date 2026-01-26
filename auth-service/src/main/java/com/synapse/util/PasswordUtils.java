package com.synapse.util;

import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PasswordUtils {
    

    public String hashPassword(String contrasenia){
        if(contrasenia == null || contrasenia.isEmpty()){
            throw new IllegalArgumentException("Contrasenia vacia");
        }
        return BcryptUtil.bcryptHash(contrasenia);
    }

    public boolean verificarPassword(String contraseniaPlana, String contraseniaHashed){
        return BcryptUtil.matches(contraseniaPlana, contraseniaHashed);
    }
}
