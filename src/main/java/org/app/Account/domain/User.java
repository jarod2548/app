package org.app.Account.domain;

import org.app.Account.infrastructure.UserDBO;

import java.util.UUID;
public class User {
    private UUID id;
    private String username;
    private String wachtwoord;
    private String role;
    private String email;

    public User(String Naam, String Wachtwoord){
        username = Naam;
        wachtwoord = Wachtwoord;
    }
    public User(String Naam, String Role, UUID Id){
        username = Naam;
        role = Role;
        id = Id;
    }
    public User(String Naam, String Wachtwoord, String Role, String Email){
        username = Naam;
        wachtwoord = Wachtwoord;
        role = Role;
        email = Email;
    }

    public UserDBO naarDBO(){
        return new UserDBO(username, wachtwoord, role, email);
    }

    public User(UserDBO dbo){
        username = dbo.getUsername();
        role = dbo.getRole();
        id = dbo.getId();
    }

    public String getUsername() {
        return username;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public UUID getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }
}
