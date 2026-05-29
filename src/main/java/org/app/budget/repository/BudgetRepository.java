package org.app.budget.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BudgetRepository extends JpaRepository<BudgetDBO, UUID> {
    List<BudgetDBO> findByUser_Id(UUID userID);
}
