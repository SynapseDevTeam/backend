package com.synapse.repository;

import java.util.Optional;

import com.synapse.model.Account;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AccountRepository implements PanacheRepository<Account>{
    public Optional<Account> findByEmail(String email) {
        return find("email", email).firstResultOptional();
    }
}
