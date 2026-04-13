package org.app.budget.service;

import org.app.Account.infrastructure.UserDBO;
import org.app.Account.infrastructure.UserRepository;
import org.app.budget.domain.Budget;
import org.app.budget.repository.BudgetDBO;
import org.app.budget.repository.BudgetRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BudgetService {

    private final BudgetRepository repository;
    private final UserRepository userReposxitory;

    public BudgetService(BudgetRepository Repository, UserRepository userReposxitory){
        repository = Repository;
        this.userReposxitory = userReposxitory;
    }

    public Budget maakBudget(Budget model, UUID userID){
        UserDBO user = userReposxitory.getReferenceById(userID);
        BudgetDBO dbo = model.naarDBO(user);
        BudgetDBO saved = repository.save(dbo);
        return new Budget(saved);
    }
}
