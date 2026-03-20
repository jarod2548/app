package org.categorie.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class CategorieDBO {
    @Id
    private Long id;
    @Column(name = "naam")
    private String naam;
    @Column(name = "isbelangrijk")
    private boolean isBelangrijk;

    protected  CategorieDBO(){}

    public CategorieDBO(String Naam, boolean IsBelangrijk)
    {
        naam = Naam;
        isBelangrijk = IsBelangrijk;
    }

    public String getNaam(){
        return naam;
    }
    public boolean getIsBelangrijk(){
        return isBelangrijk;
    }
}
