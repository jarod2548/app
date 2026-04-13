package org.app.Account.service;

import org.app.config.Exceptions.UserAlreadyExistsException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.app.Account.domain.User;
import org.app.Account.infrastructure.UserRepository;

@Service
public class RegisterService {

    private final UserRepository repository;

    public RegisterService(UserRepository Repository){
        repository = Repository;
    }
    public void maakUser(User model){
        try{
            repository.save(model.naarDBO());
        }
        catch (DataIntegrityViolationException ex){
            Throwable root = ex.getRootCause();

            if (root instanceof org.hibernate.exception.ConstraintViolationException cve) {
                String constraint = cve.getConstraintName();

                if ("uk_user_username".equals(constraint)) {
                    throw new UserAlreadyExistsException(UserAlreadyExistsException.UserErrorField.USERNAME);
                }
                if ("uk_user_email".equals(constraint)) {
                    throw new UserAlreadyExistsException(UserAlreadyExistsException.UserErrorField.EMAIL);
                }
            }

            throw ex;
        }

    }
}
