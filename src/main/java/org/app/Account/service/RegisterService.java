package org.app.Account.service;

import org.app.config.EncryptionService;
import org.app.config.Exceptions.UserAlreadyExistsException;
import org.springframework.stereotype.Service;
import org.app.Account.domain.User;
import org.app.Account.infrastructure.UserRepository;

@Service
public class RegisterService {

    private final UserRepository repository;
    private final EncryptionService encryptionService;

    public RegisterService(UserRepository Repository, EncryptionService encryptionService){
        repository = Repository;
        this.encryptionService = encryptionService;
    }
    public void maakUser(User model){
        if(repository.existsByUsername(model.getUsername())){
            throw new UserAlreadyExistsException("Username bestaat al");
        }
        if(repository.existsByEmail(model.getEmail())){
            throw new UserAlreadyExistsException("Email bestaat al");
        }

        String encryptedPassword = encryptionService.hashPassword(model.getWachtwoord());
            repository.save(model.naarDBO(encryptedPassword));
    }
}
