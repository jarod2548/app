package org.app.transaction.service;

import org.app.Account.infrastructure.UserDBO;
import org.app.Account.infrastructure.UserRepository;
import org.app.Account.service.AuthorizationService;
import org.app.budget.domain.Budget;
import org.app.categorie.repository.CategorieDBO;
import org.app.categorie.service.CategorieService;
import org.springframework.transaction.annotation.Transactional;
import org.app.transaction.repository.TransactieDBO;
import org.app.transaction.domain.Transactie;
import org.app.transaction.repository.TransactieRepository;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TransactieService {

    private  final TransactieRepository transactieRepository;
    private final AuthorizationService authorizationService;
    private final CategorieService categorieService;

    public TransactieService(TransactieRepository transactieRepository, AuthorizationService authorizationService, CategorieService categorieService) {
        this.transactieRepository = transactieRepository;
        this.authorizationService = authorizationService;
        this.categorieService = categorieService;
    }


    @Transactional
    public Transactie slaTransactieOp(Transactie model, UUID userID)
    {
            UserDBO userDBO = authorizationService.leesUserDBO(userID);
            CategorieDBO categorieDBO = null;
            if(model.getCategorieID() != null){
                categorieDBO = categorieService.leesCategorieDBO(model.getCategorieID());
            }
            TransactieDBO dbo = model.naarDBO(userDBO, categorieDBO);
            TransactieDBO saved = transactieRepository.save(dbo);
            return new Transactie(saved);
    }

    public List<Transactie> leesTransacties(UUID userID)
    {
        return transactieRepository.findByUser_Id(userID)
                .stream()
                .map(Transactie::new)
                .toList();

    }

    public List<Transactie> leesTransactiesTussenTijden(UUID userID, Budget budget){
        return  transactieRepository.findByUser_IdAndCreatieDatumBetween(userID,
                budget.getBeginDatum(),
                budget.getEindDatum())
                .stream()
                .map(Transactie::new)
                .toList();
    }


}
