package org.app.config;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncryptionService {
    private final PasswordEncoder encoder = new Argon2PasswordEncoder(16,
            32,
            1,
            1 << 14,
            3);

    public String hashPassword(String password) {
        return encoder.encode(password);
    }

    public boolean matchPassword(String passwordToCheck, String encryptedPassword){
        return encoder.matches(passwordToCheck, encryptedPassword);
    }
}
