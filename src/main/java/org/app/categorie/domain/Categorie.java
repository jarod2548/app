package org.app.categorie.domain;

import org.app.Account.infrastructure.UserDBO;
import org.app.categorie.repository.CategorieDBO;

import java.util.UUID;

public class Categorie {

    private UUID id;
    private boolean isBelangrijk;
    private String naam;

    public Categorie(boolean isBelangrijk, String naam) {
        this.isBelangrijk = isBelangrijk;
        this.naam = naam;
    }

    public Categorie(CategorieDBO dbo) {
        this.id = dbo.getId();
        this.isBelangrijk = dbo.isBelangrijk();
        this.naam = dbo.getNaam();
    }

    public CategorieDBO naarDBO(UserDBO userDBO) {
        return new CategorieDBO(isBelangrijk, naam, userDBO);
    }

    public UUID getId() {
        return id;
    }

    public boolean isBelangrijk() {
        return isBelangrijk;
    }

    public String getNaam() {
        return naam;
    }
}