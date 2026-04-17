package org.app.Account.api;

import org.app.Account.domain.User;
import org.app.config.UserPrincipal;

public class LoginResponseDTO {
    private String naam;
    private String role;

    public LoginResponseDTO(){}

    public LoginResponseDTO(User user){
        naam = user.getUsername();
        role = user.getRole();
    }

    public LoginResponseDTO(UserPrincipal user) {
        this.naam = user.getUsername();
        this.role = user.getRole();
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getNaam() {
        return naam;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
