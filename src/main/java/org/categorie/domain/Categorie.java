package org.categorie.domain;

import org.categorie.repository.CategorieDBO;

public class Categorie {

    private String naam;
    private boolean isBelangrijk;

    public Categorie(String Naam, boolean IsBelangrijk)
    {
        naam = Naam;
        isBelangrijk = IsBelangrijk;
    }

    public Categorie(CategorieDBO dbo)
    {

    }

    public CategorieDBO converteerNaarDBO()
    {
        CategorieDBO dbo = new CategorieDBO(naam, isBelangrijk);

        return dbo;
    }

    public String getNaam() {
        return naam;
    }
    public Boolean getIsBelangrijk(){
        return isBelangrijk;
    }
}
