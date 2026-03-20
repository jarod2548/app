package org.categorie.service;

import org.categorie.domain.Categorie;
import org.categorie.repository.CategorieDBO;
import org.categorie.repository.CategorieRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategorieService
{
    private final CategorieRepository repository;
    public CategorieService(CategorieRepository CategorieRepository){
        repository = CategorieRepository;
    }

    public boolean maakCategorie(Categorie model)
    {
        boolean success = false;
        CategorieDBO dbo = model.converteerNaarDBO();

        return success;
    }

    public List<Categorie> leesCategorien()
    {
        List<Categorie> categories = new ArrayList<>();

        return categories;
    }
}
