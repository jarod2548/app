package org.app.Account.service;

import org.app.Account.domain.User;
import org.app.Account.infrastructure.UserDBO;
import org.app.Account.infrastructure.UserRepository;
import org.app.config.Exceptions.InvalidCredentialsException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {
    private final UserRepository repository;

    public AuthorizationService(UserRepository Repository){
        repository = Repository;
    }

    public User Login(User model){
        UserDBO result = repository.findByUsername(model.getUsername())
                .orElseThrow(() -> new InvalidCredentialsException("Ongeldige gebruikersnaam"));
        if (!result.getWachtwoord().equals(model.getWachtwoord())) {
            throw new InvalidCredentialsException("Ongeldige gebruikersnaam of wachtwoord");
        }
        return new User(result);
    }
}
