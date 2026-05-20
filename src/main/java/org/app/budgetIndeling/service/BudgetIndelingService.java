package org.app.budgetIndeling.service;

import org.app.budget.repository.BudgetDBO;
import org.app.budget.service.BudgetService;
import org.app.budgetIndeling.domain.BudgetIndeling;
import org.app.budgetIndeling.repository.BudgetIndelingDBO;
import org.app.budgetIndeling.repository.BudgetIndelingRepository;
import org.app.categorie.repository.CategorieDBO;
import org.app.categorie.service.CategorieService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BudgetIndelingService {

    private final BudgetIndelingRepository repository;
    private final CategorieService categorieService;
    private final BudgetService budgetService;

    public BudgetIndelingService(BudgetIndelingRepository repository, CategorieService categorieService, BudgetService budgetService) {
        this.repository = repository;
        this.categorieService = categorieService;
        this.budgetService = budgetService;
    }

    public BudgetIndeling maakBudgetIndeling(BudgetIndeling model) {

        CategorieDBO categorieDBO = categorieService.leesCategorieDBO(model.getCategorieID());
        BudgetDBO budgetDBO = budgetService.leesBudgetDBO(model.getBudgetID());
        BudgetIndelingDBO dbo = model.naarDBO(categorieDBO, budgetDBO);
        BudgetIndelingDBO saved = repository.save(dbo);
        return new BudgetIndeling(saved);
    }

    public List<BudgetIndeling> leesBudgetIndeling(UUID userID){
        return repository.findByBudget_User_Id(userID)
                .stream()
                .map(BudgetIndeling::new)
                .toList();
    }
}
