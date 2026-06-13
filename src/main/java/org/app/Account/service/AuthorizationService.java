package org.app.Account.service;

import org.app.Account.domain.User;
import org.app.Account.infrastructure.UserDBO;
import org.app.Account.infrastructure.UserRepository;
import org.app.config.EncryptionService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthorizationService {
    private final UserRepository repository;
    private final EncryptionService encryptionService;

    public AuthorizationService(UserRepository Repository, EncryptionService encryptionService){
        repository = Repository;
        this.encryptionService = encryptionService;
    }

    public User Login(User model){
        UserDBO result = repository.findByUsername(model.getUsername())
                .orElseThrow(() -> new BadCredentialsException("Ongeldige gebruikersnaam"));
        if (!encryptionService.matchPassword(model.getWachtwoord(), result.getWachtwoord())) {
            throw new BadCredentialsException("Ongeldige gebruikersnaam of wachtwoord");
        }
        return new User(result);
    }

    public UserDBO leesUserDBO(UUID userID){
        return repository.findById(userID)
                .orElseThrow(() -> new IllegalArgumentException("Gebruiker niet gevonden"));
    }
}
