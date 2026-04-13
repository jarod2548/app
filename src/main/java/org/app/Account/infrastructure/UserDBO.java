package org.app.Account.infrastructure;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "users")
public class UserDBO {
    @Id
    @UuidGenerator
    @Column( updatable = false, nullable = false)
    private UUID id;
    @Column(name = "Username")
    private String username;
    @Column (name = "Wachtwoord")
    private String wachtwoord;
    @Column (name = "Rol")
    private String role;
    @Column (name = "Email")
    private String email;

    public UserDBO() {}

    public UserDBO(String Naam, String Wachtwoord, String Role, String Email){
        this.username = Naam;
        this.wachtwoord = Wachtwoord;
        role = Role;
        email = Email;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
