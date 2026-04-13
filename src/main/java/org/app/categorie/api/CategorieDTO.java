package org.app.categorie.api;

import org.app.categorie.domain.Categorie;

import java.util.UUID;

public class CategorieDTO {

    private UUID id;
    private boolean isBelangrijk;
    private String naam;

    public CategorieDTO(Categorie model) {
        this.id = model.getId();
        this.isBelangrijk = model.isBelangrijk();
        this.naam = model.getNaam();
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