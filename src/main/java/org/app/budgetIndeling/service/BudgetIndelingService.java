package org.app.budgetIndeling.service;

import org.app.budgetIndeling.domain.BudgetIndeling;
import org.app.budgetIndeling.repository.BudgetIndelingDBO;
import org.app.budgetIndeling.repository.BudgetIndelingRepository;
import org.springframework.stereotype.Service;

@Service
public class BudgetIndelingService {

    private final BudgetIndelingRepository repository;

    public BudgetIndelingService(BudgetIndelingRepository repository) {
        this.repository = repository;
    }

    public BudgetIndeling maakBudgetIndeling(BudgetIndeling model) {
        BudgetIndelingDBO dbo = model.naarDBO();
        BudgetIndelingDBO saved = repository.save(dbo);
        return new BudgetIndeling(saved);
    }
}
