package org.app.categorie.service;

import org.app.Account.infrastructure.UserDBO;
import org.app.Account.service.AuthorizationService;
import org.app.categorie.domain.Categorie;
import org.app.categorie.repository.CategorieDBO;
import org.app.categorie.repository.CategorieRepository;
import org.app.config.Exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategorieService {

    private final CategorieRepository repository;
    private final AuthorizationService authorizationService;

    public CategorieService(CategorieRepository repository, AuthorizationService authorizationService) {
        this.repository = repository;
        this.authorizationService = authorizationService;
    }

    public void maakCategorie(Categorie model, UUID userId) {
        UserDBO userDBO = authorizationService.leesUserDBO(userId);
        CategorieDBO dbo = model.naarDBO(userDBO);
        CategorieDBO saved = repository.save(dbo);
    }

    public List<Categorie> leesCategories(UUID userID){
        List<CategorieDBO> result = repository.findByUser_Id(userID);
        return  result.stream().map(Categorie::new).toList();
    }


    public CategorieDBO leesCategorieDBO(UUID categorieID){
        return  repository.findById(categorieID)
                .orElseThrow(() -> new EntityNotFoundException("Categorie bestaat niet"));
    }
}
