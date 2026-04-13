package org.app.Account.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.app.Account.domain.User;

public class LoginDTO {
    @NotBlank(message = "Naam is verplicht")
    private String username;
    @NotBlank(message = "Wachtwoord is verplicht")
    @Size(min = 6, message = "Wachtwoord moet minimaal 6 karakters bevatten")
    private String wachtwoord;

    public LoginDTO(){}

    public User naarUser(){
        return new User(username,wachtwoord);
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
}
