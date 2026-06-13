package org.app.transaction.service;

import org.app.Account.infrastructure.UserDBO;
import org.app.Account.service.AuthorizationService;
import org.app.budget.domain.Budget;
import org.app.events.TransactieAangemaaktEvent;
import org.app.categorie.repository.CategorieDBO;
import org.app.categorie.service.CategorieService;
import org.app.config.Exceptions.EntityNotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;
import org.app.transaction.repository.TransactieDBO;
import org.app.transaction.domain.Transactie;
import org.app.transaction.repository.TransactieRepository;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class TransactieService {

    private  final TransactieRepository transactieRepository;
    private final AuthorizationService authorizationService;
    private final CategorieService categorieService;
    private final ApplicationEventPublisher eventPublisher;


    public TransactieService(TransactieRepository transactieRepository,
                             AuthorizationService authorizationService,
                             CategorieService categorieService,
                             ApplicationEventPublisher eventPublisher) {
        this.transactieRepository = transactieRepository;
        this.authorizationService = authorizationService;
        this.categorieService = categorieService;
        this.eventPublisher = eventPublisher;
    }


    @Transactional
    public void slaTransactieOp(Transactie model, UUID userID)
    {
            UserDBO userDBO = authorizationService.leesUserDBO(userID);
            CategorieDBO categorieDBO = null;
            if(model.getCategorieID() != null){
                categorieDBO = categorieService.leesCategorieDBO(model.getCategorieID());
            }
            TransactieDBO dbo = model.naarDBO(userDBO, categorieDBO);
            TransactieDBO saved = transactieRepository.save(dbo);
            eventPublisher.publishEvent(
                new TransactieAangemaaktEvent(saved.getId())
            );
    }

    public BigDecimal leesTotaalVanTransactiesBijBudget(UUID userId, Budget budget){
        return transactieRepository.totaalTransactiesTussenPeriodes(userId,
                budget.getBeginDatum(),
                budget.getEindDatum());
    }

    public TransactieDBO leesTransactieDBO(UUID transactieID){
        return transactieRepository.findById(transactieID)
                .orElseThrow(() -> new EntityNotFoundException("Transactie bestaat niet"));
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
