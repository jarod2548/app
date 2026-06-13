package org.app.budget.service;

import org.app.Account.infrastructure.UserDBO;
import org.app.Account.infrastructure.UserRepository;
import org.app.Account.service.AuthorizationService;
import org.app.budget.domain.Budget;
import org.app.budget.domain.BudgetMelding;
import org.app.budget.repository.BudgetDBO;
import org.app.budget.repository.BudgetRepository;
import org.app.config.Exceptions.EntityNotFoundException;
import org.app.events.BudgetMeldingEvent;
import org.app.transaction.domain.Transactie;
import org.app.transaction.repository.TransactieDBO;
import org.app.transaction.service.TransactieService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final AuthorizationService userService;
    private final TransactieService transactieService;
    private final ApplicationEventPublisher eventPublisher;

    public BudgetService(BudgetRepository Repository,
                         AuthorizationService userService,
                         TransactieService transactieService,
                         ApplicationEventPublisher eventPublisher){
        budgetRepository = Repository;
        this.userService = userService;
        this.transactieService = transactieService;
        this.eventPublisher = eventPublisher;
    }

    public Budget maakBudget(Budget model, UUID userID){
        UserDBO user = userService.leesUserDBO(userID);
        BudgetDBO dbo = model.naarDBO(user);
        BudgetDBO saved = budgetRepository.save(dbo);
        return new Budget(saved);
    }

    public List<Budget> leesBudgeten(UUID userID){
        return budgetRepository.findByUser_Id(userID)
                .stream()
                .map(Budget::new)
                .toList();
    }

    public void controleerBudgets(UUID transactieID){
        TransactieDBO transactie = transactieService.leesTransactieDBO(transactieID);
        UUID userId = transactie.getUser().getId();
        List<BudgetDBO> dbos = budgetRepository
                .findBudgetsVanTransactie(userId,
                        transactie.getCreatieDatum());
        List<Budget> budgeten = dbos.stream().map(Budget::new).toList();
        for (Budget budget : budgeten){
            BudgetMelding melding = controleerBudget(budget);
            if(melding != null){
                eventPublisher.publishEvent(
                        new BudgetMeldingEvent(userId,melding)
                );
            }
        }
    }

    private BudgetMelding controleerBudget(Budget budget){
        BudgetMelding melding = null;
        BigDecimal totaalUitgave = transactieService
                .leesTotaalVanTransactiesBijBudget(budget.getUserId(),budget);
        BigDecimal uitgaveVerschil = totaalUitgave.subtract(budget.getAantal());
        if(uitgaveVerschil.compareTo(BigDecimal.ZERO) > 0){
            melding = new BudgetMelding(budget.getNaam(),
                    budget.getId(),
                    uitgaveVerschil);
        }
        return melding;
    }

    public BigDecimal berekenTransactiesUitgaveAantal(List<Transactie> transacties){
        return  transacties.stream()
                .map(Transactie::getAantal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Budget leesBudget(UUID budgetID){
        return budgetRepository.findById(budgetID)
                .map(Budget::new)
                .orElseThrow(() -> new EntityNotFoundException("Budget bestaat niet"));
    }

    public BudgetDBO leesBudgetDBO(UUID budgetID){
        return budgetRepository.findById(budgetID)
                .orElseThrow(() -> new EntityNotFoundException("Budget bestaat niet"));
    }
}
