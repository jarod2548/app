package org.app.budget.service;

import org.app.budget.domain.Budget;
import org.app.budget.domain.BudgetOverview;
import org.app.budgetIndeling.domain.BudgetIndeling;
import org.app.budgetIndeling.domain.BudgetIndelingOverview;
import org.app.budgetIndeling.service.BudgetIndelingService;
import org.app.transaction.domain.Transactie;
import org.app.transaction.service.TransactieService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BudgetOverviewService {

    private final BudgetService budgetService;
    private final BudgetIndelingService budgetIndelingService;
    private final TransactieService transactieService;
    public BudgetOverviewService(BudgetService budgetService, BudgetIndelingService budgetIndelingService, TransactieService transactieService){
        this.budgetService = budgetService;
        this.budgetIndelingService = budgetIndelingService;
        this.transactieService = transactieService;
    }

    public BudgetOverview leesBudgetOverview(UUID budgetId, UUID userID){
        Budget budget = budgetService.leesBudget(budgetId);
        List<BudgetIndeling> indelingen = budgetIndelingService.leesBudgetIndeling(budgetId);
        List<Transactie> transacties = transactieService.leesTransactiesTussenTijden(userID, budget);

        BigDecimal totaalUitgave = berekenTransactiesUitgaveAantal(transacties);
        List<BudgetIndelingOverview> updatedIndelingen =  berekenGegevensVoorIndelingen(indelingen, transacties, totaalUitgave);

        return new BudgetOverview(budget, updatedIndelingen, totaalUitgave);
    }


    private List<BudgetIndelingOverview> berekenGegevensVoorIndelingen(List<BudgetIndeling> indelingen,
                                               List<Transactie> transacties,
                                               BigDecimal totaalUitgave){

        Set<UUID> matchedCategories = indelingen.stream()
                .map(BudgetIndeling::getCategorieID)
                .collect(Collectors.toSet());

        BigDecimal overigeUitgave = berekenTransactiesUitgaveAantal(transacties.stream()
                .filter(t -> !matchedCategories.contains(t.getCategorieID()))
                .toList());
        Map<UUID, List<Transactie>> transactiesPerCategorie =
                transacties.stream()
                        .filter(t -> t.getCategorieID() != null)
                        .collect(Collectors.groupingBy(Transactie::getCategorieID));

        List<BudgetIndelingOverview> ingedeeldeBudgetIndelingen = new ArrayList<>(
                indelingen.stream()
                .map(indeling -> deelBudgetIndelingIn(indeling,
                        transactiesPerCategorie,
                        totaalUitgave))
                .toList());
                ingedeeldeBudgetIndelingen.add
                        (maakOverigeBudgetIndeling(overigeUitgave, totaalUitgave)
                );

        return ingedeeldeBudgetIndelingen;

    }

    private BudgetIndelingOverview deelBudgetIndelingIn(BudgetIndeling budgetIndeling,
                                                Map<UUID, List<Transactie>> transactiesPerCategorie,
                                                BigDecimal totaalUitgave){
        List<Transactie> transacties =
                transactiesPerCategorie.getOrDefault(budgetIndeling.getCategorieID(), List.of());

        BigDecimal uitgave = berekenTransactiesUitgaveAantal(transacties);

        BigDecimal percentage = totaalUitgave.compareTo(BigDecimal.ZERO) == 0
                ? BigDecimal.ZERO
                : uitgave.divide(totaalUitgave, 2, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100));;

        return new BudgetIndelingOverview(
                budgetIndeling.getCategorieNaam(),
                uitgave,
                percentage,
                budgetIndeling.getId()
        );
    }

    private BigDecimal berekenTransactiesUitgaveAantal(List<Transactie> transacties){
        return  transacties.stream()
                .map(Transactie::getAantal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BudgetIndelingOverview maakOverigeBudgetIndeling(BigDecimal overigeUitgave, BigDecimal totaalUitgave){
        BigDecimal percentage = totaalUitgave.compareTo(BigDecimal.ZERO) == 0
                ? BigDecimal.ZERO
                : overigeUitgave.divide(totaalUitgave, 2, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100));
        return new BudgetIndelingOverview(
                "Overige",
                overigeUitgave,
                percentage,
                null
        );
    }

}
