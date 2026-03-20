package org.categorie.api;

import org.categorie.domain.Categorie;

public class CategorieDTO
{
    private String naam;
    private boolean isBelangrijk;
    public CategorieDTO(){};

    public CategorieDTO(Categorie model)
    {
        naam = model.getNaam();
        isBelangrijk = model.getIsBelangrijk();
    }
    public Categorie converteerNaarModel()
    {
        Categorie model = new Categorie(naam, isBelangrijk);
        return model;
    }
}
