package org.app.Account.api;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.app.Account.domain.User;

public class RegistreerDTO {
    @NotBlank(message = "Naam is verplicht")
    private String username;
    @NotBlank(message = "Wachtwoord is verplicht")
    @Size(min = 6, message = "Wachtwoord moet minimaal 6 karakters bevatten")
    private String wachtwoord;
    @NotBlank(message = "Email is verplicht")
    @Email(message = "Ongeldig email adres")
    private String email;


    public RegistreerDTO(){};

    public User naarUser(){
        return new User(username,wachtwoord, "USER", email);
    }

    public User naarAdmin(){
        return new User(username,wachtwoord, "ADMIN", email);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
