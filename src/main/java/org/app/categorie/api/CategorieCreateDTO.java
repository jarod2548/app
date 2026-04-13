package org.app.categorie.api;

import jakarta.validation.constraints.NotNull;
import org.app.categorie.domain.Categorie;

public class CategorieCreateDTO {

    private boolean isBelangrijk;
    @NotNull(message = "Naam moet ingevuld zijn")
    private String naam;

    public CategorieCreateDTO() {

    }
    public Categorie naarModel(){
        return  new Categorie(isBelangrijk, naam);
    }

    public void setBelangrijk(boolean belangrijk) {
        isBelangrijk = belangrijk;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }
}
