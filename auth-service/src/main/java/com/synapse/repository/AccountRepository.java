package com.synapse.repository;

import java.util.Optional;
import java.util.UUID;

import com.synapse.model.Account;


import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AccountRepository implements PanacheRepositoryBase<Account, UUID>{
    public Optional<Account> findByEmail(String email) {
        return find("email", email).firstResultOptional();
    }
}
