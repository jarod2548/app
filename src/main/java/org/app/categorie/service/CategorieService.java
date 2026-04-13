package org.app.categorie.service;

import org.app.Account.infrastructure.UserDBO;
import org.app.Account.infrastructure.UserRepository;
import org.app.categorie.domain.Categorie;
import org.app.categorie.repository.CategorieDBO;
import org.app.categorie.repository.CategorieRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategorieService {

    private final CategorieRepository repository;
    private final UserRepository userReposxitory;

    public CategorieService(CategorieRepository repository,
                            UserRepository userReposxitory) {
        this.repository = repository;
        this.userReposxitory = userReposxitory;
    }

    public Categorie maakCategorie(Categorie model, UUID userId) {
        UserDBO userDBO = userReposxitory.getReferenceById(userId);
        CategorieDBO dbo = model.naarDBO(userDBO);
        CategorieDBO saved = repository.save(dbo);
        return new Categorie(saved);
    }
}
