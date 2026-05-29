package org.app.budgetIndeling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BudgetIndelingRepository extends JpaRepository<BudgetIndelingDBO, UUID> {
    List<BudgetIndelingDBO> findByBudget_Id(UUID budgetId);
}